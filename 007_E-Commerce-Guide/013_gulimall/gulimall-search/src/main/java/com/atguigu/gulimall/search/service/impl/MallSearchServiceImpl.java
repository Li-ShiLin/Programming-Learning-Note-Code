package com.atguigu.gulimall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.search.config.ElasticSearchConfig;
import com.atguigu.gulimall.search.constant.EsConstant;
import com.atguigu.gulimall.search.feign.ProductFeignService;
import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.AttrResponseVo;
import com.atguigu.gulimall.search.vo.BrandVo;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ProductFeignService productFeignService;


    //去es进行检索
    @Override
    public SearchResult search(SearchParam param) {
        //1、动态构建出查询需要的DSL语句
        SearchResult result = null;
        //1、准备检索请求
        SearchRequest searchRequest = buildSearchRequrest(param);
        try {
            //2、执行检索请求
            SearchResponse response = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            //3、分析响应数据并封装成我们需要的格式
            result = buildSearchResult(response, param);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 准备检索请求
     * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
     */
    private SearchRequest buildSearchRequrest(SearchParam param) {
        // SearchSourceBuilder是用来构建DSL语句的
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        /**
         * 查询条件: 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存）
         */
        // 1、构建Bool-query
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 1.1 must - 模糊匹配
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        // 1.2 bool - filter - 按照三级分类id查询
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        // 1.2 bool - filter - 按照品牌id查询
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            boolQuery.filter(QueryBuilders.termQuery("brandId", param.getBrandId()));
        }

        // 1.3 bool - filter - 按照是否有库存进行查询
        if (param.getHasStock() != null) {
            boolQuery.filter(QueryBuilders.termQuery("hasStock", param.getHasStock() == 1));
        }

        // 1.4 bool - filter - 按照所有指定的属性进行查询
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            // attrs=1_5寸:8寸&attrs=2_16G:8G
            for (String attrStr : param.getAttrs()) {
                BoolQueryBuilder nestedBoolQuery = QueryBuilders.boolQuery();
                // attrs=1_5寸:8寸
                String[] s = attrStr.split("_");
                String attrId = s[0]; // 检索的属性id
                String[] attrValues = s[1].split(":"); // 检索的属性值
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrValue", attrValues));
                // 每一个都必须生成一个nested查询
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }
        }

        // 1.5 bool - filter - 按照价格区间进行查询
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            //1_500/_500/500_
            /**
             * "range": {
             *             "skuPrice": {
             *               "gte": 0,
             *               "lte": 6000
             *             }
             *           }
             */
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] arrs = param.getSkuPrice().split("_");
            if (arrs.length == 2) {
                // 区间
                rangeQuery.gte(arrs[0]).lte(arrs[1]);
            } else if (arrs.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(arrs[0]);
                }
                if (param.getSkuPrice().endsWith("_")) {
                    rangeQuery.gte(arrs[0]);
                }
            }
            boolQuery.filter(rangeQuery);
        }
        // 把以前的所有条件都拿来进行封装
        sourceBuilder.query(boolQuery);

        /**
         * 排序，分页，高亮
         */
        // 2.1 排序
        if (!StringUtils.isEmpty(param.getSort())) {
            String sort = param.getSort();
            // sort=hotScore_asc/desc
            String[] s = sort.split("_");
            SortOrder order = s[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC;
            sourceBuilder.sort(s[0], order);
        }
        // 2.2 分页
        //  pageSize=5 pageNum:1  from:0  size:5  [0,1,2,3,4]
        //  pageNum:2  from:5   size:5
        //  from = (pageNum-1)*size
        sourceBuilder.from((param.getPageNum() - 1) * EsConstant.PRODUCT_PAGESIZE);
        sourceBuilder.size(EsConstant.PRODUCT_PAGESIZE);

        // 2.3 高亮（对查询到的检索关键字进行高亮）
        if (!StringUtils.isEmpty(param.getKeyword())) {
            HighlightBuilder builder = new HighlightBuilder();
            builder.field("skuTitle");
            builder.preTags("<b style='color:red'>");
            builder.postTags("</b>");
            sourceBuilder.highlighter(builder);
        }

        /**
         * 聚合分析
         */
        //1、品牌聚合
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg");
        brand_agg.field("brandId").size(50);
        // 品牌聚合的子聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        //TODO 1、聚合brand
        sourceBuilder.aggregation(brand_agg);
        // 2、分类聚合 catalog_agg
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catalog_agg").field("catalogId").size(20);
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        //TODO 2、聚合catalog
        sourceBuilder.aggregation(catalog_agg);
        // 3、属性聚合 attr_agg
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrs");

        // 聚合出当前所有的attrId
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        // 聚合分析出当前attr_id对应的名字
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        // 聚合分析出当前attr_id对应的所有可能的属性值attrValue
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        attr_agg.subAggregation(attr_id_agg);
        //TODO 3、聚合attr
        sourceBuilder.aggregation(attr_agg);

        // 将上面构建的DSL语句转成字符串进行分析，获取DSL语句
        String str = sourceBuilder.toString();
        System.out.println("构建的DSL语句：" + str);


        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }


    /**
     * 构建结果数据
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        SearchResult result = new SearchResult();
        SearchHits hits = response.getHits();
        // 1、返回的所有查询到的商品信息
        List<SkuEsModel> esModels = new ArrayList<>();
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                SkuEsModel esModel = JSON.parseObject(sourceAsString, SkuEsModel.class);
                if (!StringUtils.isEmpty(param.getKeyword())) {
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    String string = skuTitle.getFragments()[0].string();
                    esModel.setSkuTitle(string);
                }
                esModels.add(esModel);
            }
            ;
        }
        result.setProducts(esModels);

        // 2、当前所有商品涉及到的所有属性信息
        List<SearchResult.AttrVo> attrVos = new ArrayList<>();
        ParsedNested attr_agg = response.getAggregations().get("attr_agg");
        ParsedLongTerms attr_id_agg = attr_agg.getAggregations().get("attr_id_agg");
        for (Terms.Bucket bucket : attr_id_agg.getBuckets()) {
            SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
            //1、得到属性的id
            long attrId = bucket.getKeyAsNumber().longValue();
            //2、得到属性的名字
            String attrName = ((ParsedStringTerms) bucket.getAggregations().get("attr_name_agg")).getBuckets().get(0).getKeyAsString();

            //3、得到属性的所有值
            List<String> attrValues = ((ParsedStringTerms) bucket.getAggregations().get("attr_value_agg")).getBuckets().stream().map(item -> {
                String keyAsString = ((Terms.Bucket) item).getKeyAsString();
                return keyAsString;
            }).collect(Collectors.toList());

            attrVo.setAttrId(attrId);
            attrVo.setAttrName(attrName);
            attrVo.setAttrValue(attrValues);

            attrVos.add(attrVo);
        }
        result.setAttrs(attrVos);

        // 3、当前所有商品涉及到的所有品牌信息
        List<SearchResult.BrandVo> brandVos = new ArrayList<>();
        ParsedLongTerms brand_agg = response.getAggregations().get("brand_agg");
        for (Terms.Bucket bucket : brand_agg.getBuckets()) {
            SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
            //1、得到品牌的id
            long brandId = bucket.getKeyAsNumber().longValue();
            //2、得到品牌的名
            String brandName = ((ParsedStringTerms) bucket.getAggregations().get("brand_name_agg")).getBuckets().get(0).getKeyAsString();
            //3、得到品牌的图片
            String brandImg = ((ParsedStringTerms) bucket.getAggregations().get("brand_img_agg")).getBuckets().get(0).getKeyAsString();
            brandVo.setBrandId(brandId);
            brandVo.setBrandName(brandName);
            brandVo.setBrandImg(brandImg);
            brandVos.add(brandVo);
        }
        result.setBrands(brandVos);


        // 4、当前所有商品涉及到的所有分类信息
        ParsedLongTerms catalog_agg = response.getAggregations().get("catalog_agg");
        List<SearchResult.CatalogVo> catalogVos = new ArrayList<>();
        List<? extends Terms.Bucket> buckets = catalog_agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
            //得到分类id
            String keyAsString = bucket.getKeyAsString();
            catalogVo.setCatalogId(Long.parseLong(keyAsString));

            //得到分类名
            ParsedStringTerms catalog_name_agg = bucket.getAggregations().get("catalog_name_agg");
            String catalog_name = catalog_name_agg.getBuckets().get(0).getKeyAsString();
            catalogVo.setCatalogName(catalog_name);
            catalogVos.add(catalogVo);
        }
        result.setCatalogs(catalogVos);

        // 5.1、分页信息-页码
        result.setPageNum(param.getPageNum());
        // 5.2、分页信息-总记录数
        long total = hits.getTotalHits().value;
        result.setTotal(total);
        // 5.3、分页信息-总页码数(计算得到) 11/2 = 5 ... 1
        int totalPages = (int) total % EsConstant.PRODUCT_PAGESIZE == 0 ? (int) total / EsConstant.PRODUCT_PAGESIZE : ((int) total / EsConstant.PRODUCT_PAGESIZE + 1);
        result.setTotalPages(totalPages);

        List<Integer> pageNavs = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNavs.add(i);
        }
        result.setPageNavs(pageNavs);

        //6、构建面包屑导航功能：属性
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            List<SearchResult.NavVo> collect = param.getAttrs().stream().map(attr -> {
                //1、分析每个attrs传过来的查询参数值。
                SearchResult.NavVo navVo = new SearchResult.NavVo();
                // attrs=2_5存:6寸
                String[] s = attr.split("_");
                navVo.setNavValue(s[1]);
                // 根据属性id从商品服务查询对应的属性名称
                R r = productFeignService.attrInfo(Long.parseLong(s[0]));
                result.getAttrIds().add(Long.parseLong(s[0]));
                if (r.getCode() == 0) {
                    AttrResponseVo data = r.getData("attr", new TypeReference<AttrResponseVo>() {
                    });
                    navVo.setNavName(data.getAttrName());
                } else {
                    //  如果调用失败，可以设置一个默认值
                    navVo.setNavName(s[0]);
                }
//
                //2、取消了这个面包屑以后，我们要跳转到那个地方.将请求地址的url里面的当前置空
                //拿到所有的查询条件，去掉当前。
                //attrs=  15_海思（Hisilicon）
                String replace = replaceQueryString(param, attr, "attrs");
                navVo.setLink("http://search.gulimall.com/list.html" + ("".equals(replace) ? "" : "?" + replace));

                return navVo;
            }).collect(Collectors.toList());
            result.setNavs(collect);
        }

        // 7、构建面包屑导航功能：品牌
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            List<SearchResult.NavVo> navs = result.getNavs();
            SearchResult.NavVo navVo = new SearchResult.NavVo();

            navVo.setNavName("品牌");
            // 远程查询所有品牌
            R r = productFeignService.brandsInfo(param.getBrandId());
            if (r.getCode() == 0) {
                List<BrandVo> brand = r.getData("brand", new TypeReference<List<BrandVo>>() {
                });
                StringBuffer buffer = new StringBuffer();
                String replace = "";
                for (BrandVo brandVo : brand) {
                    buffer.append(brandVo.getBrandName() + ";");
                    replace = replaceQueryString(param, brandVo.getBrandId() + "", "brandId");
                }
                navVo.setNavValue(buffer.toString());
                navVo.setLink("http://search.gulimall.com/list.html" + ("".equals(replace) ? "" : "?" + replace));
            }

            navs.add(navVo);
        }

        //TODO 构建分类面包屑导航功能（注意：分类不需要导航取消）

        System.out.println("查询结果封装result:" + result);
        return result;
    }

    /**
     * 取消了这个面包屑以后，我们要跳转到那个地方.将请求地址的url里面的当前置空
     */
    private String replaceQueryString(SearchParam param, String value, String key) {
        String encode = null;
        try {
            encode = URLEncoder.encode(value, "UTF-8");
            encode = encode.replace("+", "%20");//浏览器对空格编码和java不一样（）
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!param.get_queryString().contains("&")) {  // 如果只有一个查询参数（?号后紧跟参数，不包含& ）
            return param.get_queryString().replace(key + "=" + encode, "");
        }
        return param.get_queryString().replace("&" + key + "=" + encode, "");
    }

}

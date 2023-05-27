package com.atguigu.gulimail.product.service.impl;

import com.atguigu.gulimail.product.dao.CategoryDao;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryBrandRelationService;
import com.atguigu.gulimail.product.service.CategoryService;
import com.atguigu.gulimail.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<CategoryEntity> page = this.page(
//                new Query<CategoryEntity>().getPage(params),
//                new QueryWrapper<CategoryEntity>()
//        );
//
//        return new PageUtils(page);
//    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1、查出所有分类 (查询条件nul表示查询所有数据)
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2、组装成父子的树形结构
        // 2.1 找到所有的一级分类
        List<CategoryEntity> levelOneMenus = entities
                .stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)  // 找到所有的一级分类
                .map((memu) -> {
                    // 查找当前分类的子分类
                    memu.setChildren(getChildrens(memu, entities));
                    return memu;
                }).sorted((menu1, menu2) -> {
                    // 注意要防止空指针异常
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());

        return levelOneMenus;
    }


    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> childrens = all
                .stream()
                .filter(categoryEntity -> {
                    return categoryEntity.getParentCid() == root.getCatId();
                })
                .map(categoryEntity -> {
                    // 1、找到子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                })
                .sorted((menu1, menu2) -> {
                    // 2、菜单的排序
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                }).collect(Collectors.toList());
        return childrens;
    }

    /**
     * 找到catelogId的完整路径
     *
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();

        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }


    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        // 1.收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }


    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 检查当前删除的菜单是否被别的地方引用
        // 逻辑删除
        baseMapper.deleteBatchIds(asList);
    }


    /**
     * 级联更新所有关联的数据
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

        //同时修改缓存中的数据
        //redis.del("catalogJSON");等待下次主动查询进行更新
    }


    /**
     * 获取一级分类列表
     */
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        // 1.查出所有分类1级分类
        List<CategoryEntity> level1Categorys = getLevel1Categorys();


        // 2.封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));

            // 3.封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    // 4、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", l2.getCatId()));
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            // 封装成指定格式
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(),l3.getCatId().toString(),l3.getName());

                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);

                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));

        return parent_cid;
    }

}
package com.atguigu.gulimail.product.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimail.product.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimail.product.service.AttrAttrgroupRelationService;
import com.atguigu.gulimail.product.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(List<AttrGroupRelationVo> vos) {
        // 要将前端传来的List<AttrGroupRelationVo> 类型转成 List<AttrAttrgroupRelationEntity>才能调用mybatisPlus自带的saveBatch
        List<AttrAttrgroupRelationEntity> collect = vos.stream().map((item) -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}
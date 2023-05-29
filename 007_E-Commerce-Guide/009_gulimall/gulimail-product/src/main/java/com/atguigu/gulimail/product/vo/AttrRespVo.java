package com.atguigu.gulimail.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo {
    /**
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */
    // 所属分类
    private String catelogName;

    // 所属分组
    private String groupName;

    private Long[] catelogPath;
}

package com.action.shardingsphere.ss04broadcasttable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Udict 实体类
 * 对应 t_udict 表（公共表：广播表）
 */
@Data
@TableName(value = "t_udict")
public class Udict {
    /**
     * 字典ID，使用雪花算法生成
     */
    private Long dictid;

    /**
     * 状态
     */
    private String ustatus;

    /**
     * 值
     */
    private String uvalue;
}

package org.example.cabin.bms.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.common.core.base.BaseEntity;

/**
 * @author ComeTomorrow
 * @since 2024/4/25
 */
@Data
@TableName(value = "column")
public class Column extends BaseEntity {

    @TableField(value = "name")
    private String name;   // 分类专栏名称

    @TableField(value = "blurb")
    private String blurb;   // 分类专栏简介

    @TableField(value = "cover_address")
    private String coverAddress;    // 封面地址

}

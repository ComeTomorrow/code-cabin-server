package org.example.cabin.bms.model.entity;

import lombok.Data;
import org.example.common.core.base.BaseEntity;

/**
 * @author ComeTomorrow
 * @since 2024/4/25
 */
@Data
public class Column extends BaseEntity {

    private String name;   // 分类专栏名称

    private String blurb;   // 分类专栏简介

    private String coverAddress;    // 封面地址

}

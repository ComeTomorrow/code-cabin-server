package org.example.cabin.bms.model.form;

import lombok.Data;

@Data
public class ColumnForm {

    private Long id;

    private String name;   // 分类专栏名称

    private String blurb;   // 分类专栏简介

    private String coverAddress;    // 封面地址

}

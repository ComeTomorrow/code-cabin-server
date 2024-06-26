package org.example.cabin.bms.model.bo;

import lombok.Data;

@Data
public class ArticleBO {

    private String title;    // 文章标题

    private Long userId;  // 会员id

    private String subtitle;    // 副标题

    private String summary;    // 文章摘要

    private String coverAddress;   // 封面地址

    private String contentAddress;  // 内容地址

    private Integer type;    // 文章类型

    private String originalLink;    // 原始链接

    private Integer visibleRange;   // 可见范围

    private Integer status;     // 状态

    private Long hits;      // 点击量
}

package org.example.cabin.bms.model.vo;

import lombok.Data;

/**
 * 会员视图层对象
 *
 * @author ComeTomorrow
 * @since 2024/4/25
 */
//@Schema(description = "会员视图层对象")
@Data
public class ArticleVO {

    private Long id;

    private String title;    // 文章标题

    private Long userId;  // 会员id

    private String subtitle;    // 副标题

    private String summary;    // 文章摘要

    private String coverAddress;   // 封面地址

    private String content;  // 内容

    private String markdownContent;  // markdown内容

    private Integer type;    // 文章类型

    private String originalLink;    // 原始链接

    private Integer readType;   // 阅读范围

    private Integer status;     // 状态

    private Long hits;      // 点击量

    private String source;  // 来源

    private Integer authorizedStatus;    // 审核状态

}

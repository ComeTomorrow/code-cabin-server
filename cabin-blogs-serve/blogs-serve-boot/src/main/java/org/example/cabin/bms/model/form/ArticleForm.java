package org.example.cabin.bms.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

//@Schema(description = "地址表单对象")
@Data
public class ArticleForm {

    private Long id;

    @NotBlank(message = "文章标题不能为空")
    private String title;    // 文章标题

    private Long userId;  // 会员id

    private String subtitle;    // 副标题

    private String summary;    // 文章摘要

    private String coverAddress;   // 封面地址

    @NotBlank(message = "文章内容不能为空")
    private String content;  // 内容

    @NotBlank(message = "文章markdown不能为空")
    private String markdownContent;  // markdown内容

    @NotNull(message = "", groups = {})
    private Integer type;    // 文章类型

    private String originalLink;    // 原始链接

    private Integer readType;   // 阅读范围

    private Integer status;     // 状态

    private Long hits;      // 点击量

    private String source;  // 来源

    private Integer authorizedStatus;    // 审核状态

    private List<Long> tagIds;     // 标签id

    private List<Long> columnIds;  // 栏目id

}

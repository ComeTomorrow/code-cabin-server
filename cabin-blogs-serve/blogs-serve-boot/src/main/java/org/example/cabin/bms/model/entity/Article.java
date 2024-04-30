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
@TableName(value = "article")
public class Article extends BaseEntity {

    @TableField(value = "title")
    private String title;    // 文章标题

    @TableField(value = "user_id")
    private Long userId;  // 会员id

    @TableField(value = "subtitle")
    private String subtitle;    // 副标题

    @TableField(value = "summary")
    private String summary;    // 文章摘要

    @TableField(value = "cover_address")
    private String coverAddress;   // 封面地址

    @TableField(value = "content")
    private String content;  // 内容

    @TableField(value = "markdown_content")
    private String markdownContent;  // markdown内容

    @TableField(value = "type")
    private Integer type;    // 文章类型

    @TableField(value = "original_link")
    private String originalLink;    // 原始链接

    @TableField(value = "read_type")
    private Integer readType;   // 阅读范围

    @TableField(value = "status")
    private Integer status;     // 状态

    @TableField(value = "hits")
    private Long hits;      // 点击量

    @TableField(value = "source")
    private String source;  // 来源

    @TableField(value = "authorized_status")
    private Integer authorizedStatus;    // 审核状态

}

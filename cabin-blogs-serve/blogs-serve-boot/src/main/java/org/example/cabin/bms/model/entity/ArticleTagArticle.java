package org.example.cabin.bms.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.common.core.base.BaseEntity;

@Data
@Accessors(chain = true)
@TableName(value = "relation_article_tag_article")
public class ArticleTagArticle extends BaseEntity {

    @TableField(value = "article_tag_id")
    private Long articleTagId;

    @TableField(value = "article_id")
    private Long articleId;
}

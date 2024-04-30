package org.example.cabin.bms.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.common.core.base.BaseEntity;

@Data
@Accessors(chain = true)
@TableName(value = "relation_column_article")
public class ColumnArticle extends BaseEntity {

    @TableField(value = "column_id")
    private Long columnId;

    @TableField(value = "article_id")
    private Long articleId;

}

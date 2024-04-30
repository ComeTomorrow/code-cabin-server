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
@TableName(value = "comment")
public class Comment extends BaseEntity {

    @TableField(value = "user_id")
    private Long userId;    // 会员id

    @TableField(value = "article_id")
    private Long articleId;   // 文章id

    @TableField(value = "content_address")
    private String contentAddress;   // 评论地址

    @TableField(value = "cover_address")
    private String coverAddress;    // 封面地址

    @TableField(value = "parent_comment_id")
    private Long parentCommentId;   // 被回复的评论

}
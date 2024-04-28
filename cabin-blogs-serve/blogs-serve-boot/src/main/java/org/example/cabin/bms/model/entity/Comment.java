package org.example.cabin.bms.model.entity;

import lombok.Data;
import org.example.common.core.base.BaseEntity;

/**
 * @author ComeTomorrow
 * @since 2024/4/25
 */
@Data
public class Comment extends BaseEntity {

    private Long userId;    // 会员id

    private Long articleId;   // 文章id

    private String contentAddress;   // 评论地址

    private String coverAddress;    // 封面地址

    private Long parentCommentId;   // 被回复的评论

}
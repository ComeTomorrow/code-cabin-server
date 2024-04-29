package org.example.cabin.bms.model.entity;

import lombok.Data;
import org.example.common.core.base.BaseEntity;

/**
 * 草稿
 *
 * @author ComeTomorrow
 * @since 2024/4/25
 */
@Data
public class Draft extends BaseEntity {

    private Long userId;

    private String title;

    private String content;

}
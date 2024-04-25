package org.example.cabin.bms.model.entity;

import lombok.Data;
import org.example.common.core.base.BaseEntity;

@Data
public class ArticleTags extends BaseEntity {

    private String name;

    private Long parentId;
}

package org.example.cabin.bms.model.query;

import lombok.Data;
import org.example.common.core.base.BasePageQuery;

@Data
public class ContentQuery extends BasePageQuery {

    private Long userId;
}
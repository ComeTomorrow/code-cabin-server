package org.example.cabin.bms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.cabin.bms.model.entity.Column;
import org.example.cabin.bms.model.form.ColumnForm;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.service.BlogsColumnService;
import org.springframework.stereotype.Service;

@Service
public class BlogsColumnServiceImpl implements BlogsColumnService {

    @Override
    public IPage<Column> getColumnsPaginateByUser(ContentQuery contentQuery) {
        return null;
    }

    @Override
    public int addColumn(ColumnForm columnForm) {
        return 0;
    }
}

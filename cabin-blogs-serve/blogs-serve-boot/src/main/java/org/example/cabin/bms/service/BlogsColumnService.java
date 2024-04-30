package org.example.cabin.bms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.cabin.bms.model.entity.Column;
import org.example.cabin.bms.model.form.ColumnForm;
import org.example.cabin.bms.model.query.ContentQuery;

public interface BlogsColumnService {

    /**
     * 根据用户id获得栏目列表，并且将其分页
     * @param contentQuery
     * @return
     */
    IPage<Column> getColumnsPaginateByUser(ContentQuery contentQuery);

    /**
     * 新增栏目
     * @param columnForm
     * @return
     */
    int addColumn(ColumnForm columnForm);
}

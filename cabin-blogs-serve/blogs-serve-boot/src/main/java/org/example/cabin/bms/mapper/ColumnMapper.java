package org.example.cabin.bms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.cabin.bms.model.entity.Column;

@Mapper
public interface ColumnMapper extends BaseMapper<Column> {
}

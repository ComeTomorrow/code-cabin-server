package org.example.common.core.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础分页请求对象
 *
 * @author ComeTomorrow
 * @since 2024/3/29
 */
@Data
//@Schema
public class BasePageQuery<T> implements Serializable {

//    @Schema(description = "页码", example = "1")
    private int pageNum = 1;

//    @Schema(description = "每页记录数", example = "10")
    private int pageSize = 10;

    public IPage startPage(){
        return new Page<>(pageNum , pageSize);
    }
}

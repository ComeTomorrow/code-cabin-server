package org.example.cabin.bms.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.cabin.bms.model.entity.Column;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.model.vo.ColumnVO;
import org.example.cabin.bms.service.BlogsColumnService;
import org.example.common.core.result.PageResult;
import org.example.common.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-api/v1/specialColumn")
public class ColumnManagementController {

    @Autowired
    private BlogsColumnService columnService;

    @GetMapping("/column/page")
    public PageResult<ColumnVO> getArticlesPage(ContentQuery query) {
        query.setUserId(SecurityUtils.getUserId());
        IPage<Column> bos = columnService.getColumnsPaginateByUser(query);
        IPage<ColumnVO> vos = new Page<>();
        for (Column column : bos.getRecords()){
            ColumnVO vo = new ColumnVO();
            BeanUtil.copyProperties(column, vo);
            vos.getRecords().add(vo);
        }
        return PageResult.success(vos);
    }

}

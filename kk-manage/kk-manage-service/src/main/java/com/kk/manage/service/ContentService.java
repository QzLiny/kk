package com.kk.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kk.common.bean.EasyUIResult;
import com.kk.manage.mapper.ContentMapper;
import com.kk.manage.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ContentService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/5 16:52
 */
@Service
public class ContentService extends BaseService<Content> {

    @Autowired
    private ContentMapper contentMapper;

    public EasyUIResult queryList(Long categoryId, Integer page, Integer rows) {

        //设置分页参数
        PageHelper.startPage(page,rows);

        List<Content> contents = this.contentMapper.queryList(categoryId);
        PageInfo<Content> pageInfo = new PageInfo<Content>(contents);
        return new EasyUIResult(pageInfo.getTotal() , pageInfo.getList());
    }
}

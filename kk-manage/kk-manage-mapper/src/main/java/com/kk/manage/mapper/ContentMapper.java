package com.kk.manage.mapper;

import com.github.abel533.mapper.Mapper;
import com.kk.manage.pojo.Content;

import java.util.List;

/**
 * @ClassName ContentCategoryMapper
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/5 16:50
 */
public interface ContentMapper extends Mapper<Content> {
    List<Content> queryList(Long categoryId);
}

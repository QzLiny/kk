package com.kk.manage.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kk.manage.mapper.ItemParamMapper;
import com.kk.manage.pojo.ItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ItemParamService
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/4 20:17
 */
@Service
public class ItemParamService extends BaseService<ItemParam> {

    @Autowired
    private ItemParamMapper itemParamMapper;

    public PageInfo<ItemParam> queryPageList(Integer page, Integer rows) {
        Example example = new Example(ItemParam.class);
        example.setOrderByClause("updated DESC");

        PageHelper.startPage(page,rows);

        List<ItemParam> list = this.itemParamMapper.selectByExample(example);
        return new PageInfo<ItemParam>(list);
    }
}

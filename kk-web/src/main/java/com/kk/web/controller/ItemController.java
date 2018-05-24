package com.kk.web.controller;

import com.kk.manage.pojo.ItemDesc;
import com.kk.web.bean.Item;
import com.kk.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ItemController
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/6 16:25
 */
@RequestMapping("item")
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "{itemId}" , method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("itemId") Long itemId){
        ModelAndView modelAndView = new ModelAndView("item");
        //商品的基本数据
        Item item = this.itemService.queryItemById(itemId);
        modelAndView.addObject("item" , item);
        //商品的描述数据
        ItemDesc itemDesc = this.itemService.queryItemDescByItemId(itemId);
        modelAndView.addObject("itemDesc",itemDesc);
        //规格参数数据
        String itemParam = this.itemService.queryItemParamItemByItemId(itemId);
        modelAndView.addObject("itemParam",itemParam);
        return modelAndView;
    }
}

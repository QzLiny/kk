package com.kk.manage.controller;

import com.kk.manage.pojo.ItemParamItem;
import com.kk.manage.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ItemParamItemController 表示规格参数的视图控制器
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/4 20:21
 */
@RequestMapping("item/param/item")
@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    /**
     * 根据商品ID查询商品规格参数数据
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}" , method = RequestMethod.GET)
    public ResponseEntity<ItemParamItem> queryByItemById(@PathVariable("itemId")Long itemId){
        try {
            ItemParamItem record = new ItemParamItem();
            record.setItemId(itemId);
            ItemParamItem itemParamItem = this.itemParamItemService.queryOne(record);

            if (null == itemParamItem){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}

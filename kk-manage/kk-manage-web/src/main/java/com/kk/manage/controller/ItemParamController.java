package com.kk.manage.controller;

import com.github.pagehelper.PageInfo;
import com.kk.common.bean.EasyUIResult;
import com.kk.manage.pojo.ItemParam;
import com.kk.manage.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ItemParamController 表示规格参数模板的视图控制器
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/4 20:21
 */
@RequestMapping("item/param")
@Controller
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据类目ID查找规格参数模板
     * @param itemCatId
     * @return
     */
    @RequestMapping(value = "{itemCatId}" , method = RequestMethod.GET)
    public ResponseEntity<ItemParam> queryByItemCatId(@PathVariable("itemCatId") Long itemCatId){

        try {
            ItemParam recond = new ItemParam();
            recond.setItemCatId(itemCatId);
            ItemParam itemParam = this.itemParamService.queryOne(recond);

            if (null == itemParam ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }

    /**
     * 新增规格参数模板
     * @param itemParam
     * @param itemCatId
     * @return
     */
    @RequestMapping(value = "{itemCatId}" , method = RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(ItemParam itemParam , @PathVariable("itemCatId") Long itemCatId){

        try {
            itemParam.setItemCatId(itemCatId);

            this.itemParamService.save(itemParam);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 查询规格参数模板
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemParamList(
            @RequestParam(value = "page" , defaultValue = "1") Integer page,
            @RequestParam(value = "rows" , defaultValue = "30") Integer rows){
        try {
            PageInfo<ItemParam> pageInfo = this.itemParamService.queryPageList(page , rows );
            EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal() , pageInfo.getList());
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //出错500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

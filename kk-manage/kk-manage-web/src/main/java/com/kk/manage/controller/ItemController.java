package com.kk.manage.controller;

import com.github.pagehelper.PageInfo;
import com.kk.common.bean.EasyUIResult;
import com.kk.manage.pojo.Item;
import com.kk.manage.service.ItemDescService;
import com.kk.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ItemController 表示商品的视图控制器
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/2 16:19
 */
@RequestMapping("item")
@Controller
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;

    /**
     * 新增商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item , @RequestParam("desc") String desc , @RequestParam("itemParams")String itemParams){
        try {
            if (LOGGER.isInfoEnabled()){
                LOGGER.info("新增商品，item = {} , desc = {}" , item , desc);
            }
            if(StringUtils.isEmpty(item.getTitle())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            //保存商品的基本数据
            this.itemService.saveItem(item , desc , itemParams);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增商品成功，itemId = {} ", item.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增商品失败！tittle = " + item.getTitle() + ", cid = " + item.getCid() , e);
        }
        //出错500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemList(
            @RequestParam(value = "page" , defaultValue = "1") Integer page,
            @RequestParam(value = "rows" , defaultValue = "30") Integer rows){
        try {
            PageInfo<Item> pageInfo = this.itemService.queryPageList(page , rows );
            EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal() , pageInfo.getList());
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //出错500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 修改商品信息
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(Item item , @RequestParam("desc") String desc, @RequestParam("itemParams")String itemParams){
        try {
            if (LOGGER.isInfoEnabled()){
                LOGGER.info("修改商品，item = {} , desc = {}" , item , desc);
            }
            if(StringUtils.isEmpty(item.getTitle())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            //保存商品的基本数据
            this.itemService.updateItem(item , desc , itemParams);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("修改商品成功，itemId = {} ", item.getId());
            }
            //成功204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("修改商品失败！tittle = " + item.getTitle() + ", cid = " + item.getCid() , e);
        }
        //出错500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 根据商品ID查询商品数据
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}" , method = RequestMethod.GET)
    public ResponseEntity<Item> queryById(@PathVariable("itemId") Long itemId){
        try {
            Item item = this.itemService.queryById(itemId);
            if (null == item){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        //出错500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

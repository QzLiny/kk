package com.kk.manage.controller;

import com.kk.manage.pojo.ItemCat;
import com.kk.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName ItemCatController 商品类目视图控制器
 * @Author Administrator
 * @Param 
 * @Return 
 * @Throws
 * @Date 2018/5/1 12:56 
 */
@RequestMapping("item/cat")
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 查询商品类目
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        try {
            //List<ItemCat> list = this.itemCatService.queryItemCatListByParentId(0L);
            ItemCat record = new ItemCat();
            record.setParentId(parentId);
            List<ItemCat> list = this.itemCatService.queryListByWhere(record);

            if(null == list || list.isEmpty()){
                //资源不存在
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

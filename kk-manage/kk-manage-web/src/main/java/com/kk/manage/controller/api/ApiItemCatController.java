package com.kk.manage.controller.api;

import com.kk.common.bean.ItemCatResult;
import com.kk.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @ClassName ApiItemCatController 表示对外提供查询商品类目数据的api接口
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/5 11:25
 */
@RequestMapping("api/item/cat")
@Controller
public class ApiItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 对外提供接口查询商品类目数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ItemCatResult> queryItemCat(){
        try {
            ItemCatResult itemCatResult = this.itemCatService.queryAllToTree();
            return ResponseEntity.ok(itemCatResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

package com.kk.manage.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @ClassName ItemDesc 表示商品描述实体类
 * @Author Administrator
 * @Param 
 * @Return 
 * @Throws
 * @Date 2018/5/2 16:07 
 */
@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo{
    /**
     * 对应tb_item中的id
     */
    @Id
    private Long itemId;
    
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
    
    

}

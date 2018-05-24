package com.kk.cart.service;

import com.kk.cart.bean.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.common.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName ItemService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/12 14:21
 */
@Service
public class ItemService {

    @Autowired
    private ApiService apiService;

    @Value("${KK_MANAGE_URL}")
    private String KK_MANAGE_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Item queryItemById(Long itemId){
        try {
            String url = KK_MANAGE_URL + "/rest/item" + itemId;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isEmpty(jsonData)){
                return null;
            }
            return MAPPER.readValue(jsonData,Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

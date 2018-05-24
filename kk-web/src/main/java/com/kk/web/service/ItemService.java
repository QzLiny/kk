package com.kk.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.kk.common.service.ApiService;
import com.kk.common.service.RedisService;
import com.kk.manage.pojo.ItemDesc;
import com.kk.web.bean.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @ClassName ItemService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/6 16:30
 */
@Service
public class ItemService {
    @Autowired
    private ApiService apiService;

    @Value("${KK_MANAGE_URL}")
    private String KK_MANAGE_URL;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String REDIS_KEY = "kk_web_item_detail_";

    private static final Integer REIDS_TIME = 60 * 60 * 24;
    /**
     * 查询商品的基本信息
     * @param itemId
     * @return
     */
    public Item queryItemById(Long itemId) {
        //先从缓存中命中
        String key = REDIS_KEY + itemId;
        try {
            String cacheData = this.redisService.get(key);
            if (StringUtils.isNotEmpty(cacheData)){
                //命中
                return MAPPER.readValue(cacheData , Item.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String url = KK_MANAGE_URL + "/rest/item/" + itemId;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isEmpty(jsonData)){
                return null;
            }
            try {
                //将结果集写入到缓存
                this.redisService.set(key,jsonData,REIDS_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return MAPPER.readValue(jsonData , Item.class);
            } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询商品的描述信息
     * @param itemId
     * @return
     */
    public ItemDesc queryItemDescByItemId(Long itemId) {
        try {
            String url = KK_MANAGE_URL + "/rest/item/desc/" + itemId;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isEmpty(jsonData)){
                return null;
            }
            return MAPPER.readValue(jsonData , ItemDesc.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String queryItemParamItemByItemId(Long itemId) {
        try {
            String url = KK_MANAGE_URL + "/rest/item/param/item/" + itemId;
            String jsonData = this.apiService.doGet(url);
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            String paramData = jsonNode.get("paramData").asText();
            ArrayNode arrayNode = (ArrayNode) MAPPER.readTree(paramData);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<table cellpadding=\"0\" cellpacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\"><tbody>");
            for (JsonNode node : arrayNode){
                stringBuilder.append("<tr><th class=\"tdTitle\" colspan=\"2\">"+node.get("group").asText()+"</th></tr>");
                ArrayNode params = (ArrayNode) node.get("params");
                for (JsonNode param : params){
                    stringBuilder.append("<tr><td class=\"tdTitle\">" + param.get("k").asText() + "</td><td>" + param.get("v").asText() + "</td></tr>");
                }
            }
            stringBuilder.append("<tbody></table>");
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

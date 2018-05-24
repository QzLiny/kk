package com.kk.web.mq.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.common.service.RedisService;
import com.kk.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ItemMQHandler
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/12 13:40
 */
public class ItemMQHandler {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RedisService redisService;

    public void execute(String msg){
        try {
            JsonNode jsonNode = MAPPER.readTree(msg);
            Long itemId = jsonNode.get("itemId").asLong();
            String key = ItemService.REDIS_KEY + itemId;
            this.redisService.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

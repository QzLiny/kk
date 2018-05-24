package com.kk.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.common.bean.EasyUIResult;
import com.kk.common.service.ApiService;
import com.kk.manage.pojo.Content;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/6 0:32
 */
@Service
public class IndexService {

    @Autowired
    private ApiService apiService;

    @Value("${KK_MANAGE_URL}")
    private String KK_MANAGE_URL;

    @Value("${INDEX_AD1_URL}")
    private String INDEX_AD1_URL;

    @Value("${INDEX_AD2_URL}")
    private String INDEX_AD2_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 大广告
     * @return
     */
    public String queryIndexD1() {
        try {
            String url = KK_MANAGE_URL + INDEX_AD1_URL;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isEmpty(jsonData)){
                return null;
            }

            EasyUIResult easyUIResult = EasyUIResult.formatToList(jsonData, Content.class);

            List<Map<String , Object>> result = new ArrayList<Map<String , Object>>();

            for (Content content : (List<Content>)easyUIResult.getRows()){
                Map<String , Object> map = new LinkedHashMap<String, Object>();
                map.put("srcB", content.getPic());
                map.put("height", 240);
                map.put("alt", content.getTitle());
                map.put("width", 670);
                map.put("src", content.getPic());
                map.put("widthB", 550);
                map.put("href", content.getUrl());
                map.put("heightB", 240);

                result.add(map);
            }
            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 右侧小广告
     * @return
     */
    public String queryIndexD2() {
        try {
            String url = KK_MANAGE_URL + INDEX_AD2_URL;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isEmpty(jsonData)){
                return null;
            }
            //解析json，生成前端所需要的json数据
            EasyUIResult easyUIResult = EasyUIResult.formatToList(jsonData, Content.class);

            List<Map<String , Object>> result = new ArrayList<Map<String , Object>>();

            for (Content content : (List<Content>)easyUIResult.getRows()){
                Map<String , Object> map = new LinkedHashMap<String, Object>();
                map.put("width", 310);
                map.put("height", 70);
                map.put("src", content.getPic());
                map.put("href", content.getUrl());
                map.put("alt", content.getTitle());
                map.put("widthB", 210);
                map.put("heightB", 70);
                map.put("srcB", content.getPic());

                result.add(map);
            }
            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

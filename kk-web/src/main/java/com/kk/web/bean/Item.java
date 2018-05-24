package com.kk.web.bean;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName Item
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/6 16:54
 */
public class Item extends com.kk.manage.pojo.Item {

    public String[] getImages(){
        return StringUtils.split(super.getImage(),',');
    }

}

package com.kk.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName PageController 表示页面控制视图控制器
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/4/30 13:48
 */
@RequestMapping("page")
@Controller
public class PageController {
    @RequestMapping(value = "{pageName}",method = RequestMethod.GET)
    public String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }
}

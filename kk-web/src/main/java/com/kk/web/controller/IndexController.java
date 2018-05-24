package com.kk.web.controller;

import com.kk.web.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @ClassName IndexController
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/5 10:37
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "index" , method = RequestMethod.GET)
    public ModelAndView index(){

        ModelAndView modelAndView = new ModelAndView("index");
        //大广告位数据
        String indexAD1 = this.indexService.queryIndexD1();
        modelAndView.addObject("indexAd1" , indexAD1);

        //右上角小广告
        String indexAD2 = this.indexService.queryIndexD2();
        modelAndView.addObject("indexAd2" , indexAD2);

        return modelAndView;
    }
}

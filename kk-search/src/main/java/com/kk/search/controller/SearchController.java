package com.kk.search.controller;

import com.kk.search.bean.SearchResult;
import com.kk.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName SearchController
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/11 17:07
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("q")String keyWords,@RequestParam(value = "page",defaultValue = "1") Integer page){
        ModelAndView modelAndView = new ModelAndView("search");
        try {
            keyWords = new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");

            SearchResult searchResult = this.searchService.search(keyWords,page);

            modelAndView.addObject("query",keyWords);
            modelAndView.addObject("itemList",searchResult.getData());
            modelAndView.addObject("page",page);
            //总页数
            int total = searchResult.getTotal().intValue();
            int pages = total % SearchService.ROWS == 0 ? total/SearchService.ROWS : SearchService.ROWS + 1;
            modelAndView.addObject("pages",pages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;

    }
}

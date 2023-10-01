package com.atguigu.gulimall.search.controller;

import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/6/22 19:20
 */
@Controller
public class SearchController {
    @Autowired
    private MallSearchService mallSearchService;

    /**
     * param: 自动将页面提交过来的所有请求查询参数封装成指定的对象
     * model : 因为所有查到的数据是要返回给页面，所以参数中加一个Model
     */
    @GetMapping({"/", "/list.html"})
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {
        param.set_queryString(request.getQueryString());
        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);
        // 将检索到的结果传到页面进行渲染
        model.addAttribute("result", result);
        return "list";
    }
}

package com.atguigu.gulimall.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/6/22 19:20
 */
@Controller
public class SearchController {


    @GetMapping("/list.html")
    public String listPage(){
        return "list";
    }

}

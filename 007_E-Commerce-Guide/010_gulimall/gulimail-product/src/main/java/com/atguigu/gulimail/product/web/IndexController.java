package com.atguigu.gulimail.product.web;

import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryService;
import com.atguigu.gulimail.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 2:23
 */

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "index.html"})
    public String indexPage(Model model) {
        // 1.查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();


        // 2.将查出的数据放到视图中，之后就可以在index.html 文件中取出这些数据对页面进行渲染
        model.addAttribute("categorys", categoryEntities);

        // 视图解析器进行拼串：默认前缀classpath:/templates/  默认后缀.html
        // classpath:/templates/ +返回值+  .html
        return "index";
    }


    /**
     * 渲染二级、三级分类数据
     * /index/catalog.json
     */
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }

    /**
     * 性能压测简单服务
     */
    @ResponseBody
    @GetMapping("/helloSimple")
    public String hello() {
        return "性能压测简单服务";
    }
}

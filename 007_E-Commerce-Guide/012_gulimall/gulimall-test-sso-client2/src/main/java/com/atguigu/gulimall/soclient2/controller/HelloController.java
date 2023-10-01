package com.atguigu.gulimall.soclient2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {


    @Value("${sso.server.url}")
    String ssoServerUrl;


    /**
     * 无需登录就可访问
     */
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/boss")
    public String employees(Model model, HttpSession session,
                            // 只要去ssoserver登录成功跳回来就会带上token
                            @RequestParam(value = "token", required = false) String token) {  // 利用token,感知这次是在ssoserver登录成功跳回来的
        // 判断session中是否有当前会话的用户信息，如果有就是已经登录，否则没有登录

        if (!StringUtils.isEmpty(token)) {  // 只要去ssoserver登录成功跳回来就会带上token
            // 去ssoserver服务器获取token对应的用户信息
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://ssoserver.com:8080/userInfo?token=" + token, String.class);
            String body = forEntity.getBody();
            session.setAttribute("loginUser", body);
        }
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) { // 由于没登录，就要命令浏览器重定向到新位置
            // 没登录,跳转到登录服务器进行登录
            // 在请求路径上加上参数redirect_url表示当前请求的路径，在认证中心登录成功后还要跳转回当前请求路径
            return "redirect:" + ssoServerUrl + "?redirect_url=http://client2.com:8082/boss";
        } else {
            List<String> emps = new ArrayList<>();
            emps.add("张三");
            emps.add("李四");
            model.addAttribute("emps", emps);
            return "list";
        }

    }
}

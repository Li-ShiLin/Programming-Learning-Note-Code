package com.atguigu.gulimall.soserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login.html")
    public String loginPage(@RequestParam("redirect_url") String url, Model model,
                            @CookieValue(value = "sso_token",required = false) String ssoToken) {
        // 如果server.com下的cookie中存在ssoToken，则说明之前有人已经在其他受信系统中登录过
        if (!StringUtils.isEmpty(ssoToken)){
            return "redirect:" + url + "?token=" + ssoToken;
        }
        model.addAttribute("url", url);
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("url") String url,
                          HttpServletResponse response) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            // 把登录成功的用户保存到redis,实现用户信息的共享，跳转到之前页面后判断是否登陆成功
            // 如果redis中存在用户信息则不进行登录跳转，不做此判断的话即便已经在认证中心做了登录，也会一直跳到认证中心的登录页
            String uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, username);
            // 登录成功后进行跳转，跳回到之前的页面：从哪里跳到登录页，就跳回到哪里

            Cookie ssoToken = new Cookie("sso_token", uuid);
            response.addCookie(ssoToken);
            return "redirect:" + url + "?token=" + uuid;
        }
        // 登录失败则停留在登录页继续登录
        return "login";
    }


    @ResponseBody
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("token") String token){
        String s = redisTemplate.opsForValue().get(token);
        return s;
    }
}

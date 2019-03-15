package com.chenxin.controller;

import com.chenxin.httpClient.HttpClient4Utils;
import com.chenxin.repostory.CookieUtils;
import com.chenxin.repostory.RedisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private CookieUtils cookieUtils;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        StringBuilder stringBuilder = new StringBuilder();
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("password");
        //加密
        String userToken = DigestUtils.sha256Hex(stringBuilder.append(userName).append(passWord).toString());
        //存储在redis
        redisUtils.set(userName, userToken);
        //存储在cookie
        Cookie cookie = new Cookie("loginedUser", userName);
        response.addCookie(cookie);
        cookie = new Cookie("userToken", userToken);
        response.addCookie(cookie);
        //跳转到上一个页面
        String url = request.getRequestURI();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return "111";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

}

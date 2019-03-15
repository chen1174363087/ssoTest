package com.chenxin.interceptor;

import com.chenxin.service.LoginService;
import com.chenxin.repostory.CookieUtils;
import com.chenxin.serviceImpl.LoginServiceImpl;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingleLoginInterceptor implements HandlerInterceptor {

    private CookieUtils cookieUtils = new CookieUtils();

    private LoginService loginService = new LoginServiceImpl();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //从Cookie中获取登录信息
        boolean isLogined = false;
        try {
            isLogined = loginService.isLogined(cookieUtils.getUserToken(httpServletRequest, httpServletResponse));
        } catch (Exception e) {
            e.printStackTrace();
            //登陆
            if (!isLogined) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/toLogin");
            }
        }
        return isLogined;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

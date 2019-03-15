package com.chenxin.repostory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CookieUtils {
    public Map<String, String> getUserToken(HttpServletRequest servletRequest, HttpServletResponse response){
        Map<String, String> userNameAndToken = new HashMap<>();
        Cookie cookies[] = servletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginedUser")) {
                userNameAndToken.put("loginedUser", cookie.getValue());
            }

            if (cookie.getName().equals("userToken")) {
                userNameAndToken.put("userToken", cookie.getValue());
            }
        }
        return userNameAndToken;
    }
}

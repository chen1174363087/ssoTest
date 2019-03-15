package com.chenxin.serviceImpl;

import com.chenxin.service.LoginService;
import com.chenxin.repostory.RedisUtils;

import javax.annotation.Resource;
import java.util.Map;

public class LoginServiceImpl implements LoginService {
    private RedisUtils redisUtils = new RedisUtils();

    @Override
    public boolean isLogined(Map<String, String> tokon) throws Exception{
        //获取用户名
        String loginedUser = tokon.get("loginedUser");
        String userToken = tokon.get("userToken");
        //查看redis是否存储key->userName , value->token
        String getedToken = redisUtils.get(loginedUser);
        if(getedToken != null || !("".equals(getedToken))){
            if(userToken.equals(getedToken)){
                return true;
            }
            return false;
        }
        return false;
    }
}

package com.chenxin.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LoginService {

    boolean isLogined(Map<String,String> token) throws Exception;
}

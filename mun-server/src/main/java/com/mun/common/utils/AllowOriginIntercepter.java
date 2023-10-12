package com.mun.common.utils;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/17
 */

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class AllowOriginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin); // 只设置请求该请求的域名，Access-Control-Allow-Credentials只允许一个
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, uuid, sCode");

        {   //安全性验证拦截
            String method = request.getMethod();
            String sCode = request.getHeader("sCode");
            if("OPTIONS".equals(method)){
                return true;
            }
            String decryptStr = RSADecryptUtils.decrypt(sCode);
            Date paramDate = DateUtils.toDate(decryptStr, "yyyyMMddHHmmss");
            Instant paramDateInstant = paramDate.toInstant();

            // 获取另一个时间点的 Instant，这里用当前时间演示
            Instant endInstant = Instant.now();
            Duration duration = Duration.between(endInstant, paramDateInstant);
            System.out.println("method: " + method + " decrypt: " + decryptStr + " duration: " + duration.getSeconds());
            long durationSeconds = duration.getSeconds();
            if(durationSeconds < -1 || durationSeconds > 2){
                return false;
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}

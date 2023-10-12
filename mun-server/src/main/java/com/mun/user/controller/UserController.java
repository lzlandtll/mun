package com.mun.user.controller;

import com.google.protobuf.Api;
import com.mun.common.domain.vo.response.ApiResult;
import com.mun.common.exception.BusinessErrorEnum;
import com.mun.common.utils.AssertUtils;
import com.mun.common.utils.CaptchaGenerator;
import com.mun.common.utils.MailUtil;
import com.mun.common.utils.RedisUtils;
import com.mun.user.domain.entity.User;
import com.mun.user.domain.vo.request.Captcha;
import com.mun.user.domain.vo.request.UserReq;
import com.mun.user.domain.vo.response.UserResp;
import com.mun.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/17
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final String REGISTRY_VERIFICATION_PREFIX = "registry_verification_";
    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaGenerator captchaGenerator;
    @Autowired
    private MailUtil mailUtil;


    @GetMapping("/getUserInfo")
    public String getUserInfo(){
        return "userinfo";
    }

    @GetMapping("/accountLogin")
    public ApiResult<UserResp> accountLogin(@Param("user") UserReq userReq){
        UserResp userResp = userService.accountLogin(userReq);
        return ApiResult.success(userResp);
    }


    @GetMapping(value = "registryVisitor")
    public ApiResult<String>  registryVisitor() throws IOException {
        return ApiResult.success(UUID.randomUUID().toString());
    }


    @GetMapping(value = "getCapCode")
    public ApiResult<Map>  getCapCode(HttpServletRequest request) throws IOException {
        String uuid = request.getHeader("uuid");
        Map imageVerCode = captchaGenerator.getImageToByteArray();
        String code = (String) imageVerCode.get("text");

        Captcha captcha = new Captcha();
        captcha.setCode(code);
        captcha.setScene("auth_registry");
        String key = REGISTRY_VERIFICATION_PREFIX + uuid;
        RedisUtils.del(key);
        RedisUtils.set(key, captcha, Duration.ofHours(1).toMillis()/1000);
        System.out.println("key: " + key);
        return ApiResult.success(imageVerCode);
    }



    @GetMapping(value = "getMailCode")
    public ApiResult<Map>  getMailCode(HttpServletRequest request, @Param("code") String code) throws IOException {

        String uuid =  request.getHeader("uuid");

        System.out.println("getMailCode.uuid:" + uuid);
        String key = REGISTRY_VERIFICATION_PREFIX + uuid;
        Captcha captcha = RedisUtils.get(key, Captcha.class);
        AssertUtils.isNotEmpty(captcha, "图形验证码已经过期");
        AssertUtils.equal(captcha.getCode(), code, "图形验证码错误");
        //发送邮箱或者手机验证码
        mailUtil.sendMail("moxuan387@gmail.com", "注册验证码", "你的验证码为: " + captchaGenerator.randomText());
        System.out.println("captcha1:" + captcha);
        return ApiResult.success();
    }

}

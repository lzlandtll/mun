package com.mun.common.utils;

import com.mun.common.domain.vo.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/10/10
 */
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    public void sendMail(String toEmail, String subject, String context) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人邮件地址(上面获取到的，也可以直接填写,string类型)
        message.setFrom(username);
        //要发送的qq邮箱(收件人地址)
        message.setTo(toEmail);
        //邮件主题
        message.setSubject(subject);
        //邮件正文
        message.setText(context);
        javaMailSender.send(message);
    }
}

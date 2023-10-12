package com.mun.common.utils;

import cn.hutool.core.codec.Base64;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class CaptchaGenerator {
    private DefaultKaptcha dk = new DefaultKaptcha();

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        dk = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border", "yes");
        properties.put("kaptcha.border.color","105,179,90");
        properties.put("kaptcha.textproducer.font.color","blue");
        properties.put("kaptcha.image.width","125");
        properties.put("kaptcha.image.height","45");
        properties.put("kaptcha.textproducer.font.size","45");
        properties.put("kaptcha.session.key","code");
        properties.put("kaptcha.textproducer.char.length","4");
        properties.put("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        Config config = new Config(properties );
        dk.setConfig(config);
        return dk;
    }

    public Map getImageToByteArray() throws IOException {
        // 1.创建随机文本
        String capText = dk.createText();

        // 将文本生成图片buffer
        BufferedImage bi = dk.createImage(capText);

        // 3.将图片转换成字节并且返回结果
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(bi,"png",os);
        Map resultMap = new HashMap();
        resultMap.put("text", capText);
        resultMap.put("img", Base64.encode(os.toByteArray()));
        return resultMap;
    }

    public String randomText(){
        return dk.createText();
    }



}


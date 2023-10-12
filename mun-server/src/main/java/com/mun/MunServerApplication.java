package com.mun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan({"com.mun.**.mapper"})
@ServletComponentScan
public class MunServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MunServerApplication.class, args);
    }

}

package com.zzr.confidant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.zzr.confidant.mapper")
@EnableCaching
public class ConfidantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfidantApplication.class, args);
    }



}

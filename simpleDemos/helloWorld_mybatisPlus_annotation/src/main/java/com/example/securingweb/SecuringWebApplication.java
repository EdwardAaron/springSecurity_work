package com.example.securingweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//也可放置到配置类上
@EnableGlobalMethodSecurity(
        securedEnabled = true,//启用@Secured
        prePostEnabled = true)
@MapperScan("com.example.securingweb.mapper")//扫描mapper
public class SecuringWebApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(SecuringWebApplication.class, args);
    }

}

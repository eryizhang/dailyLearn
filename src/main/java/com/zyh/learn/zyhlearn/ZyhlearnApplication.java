package com.zyh.learn.zyhlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableCaching
@EnableWebMvc
public class ZyhlearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyhlearnApplication.class, args);
        System.out.println("ZyhlearnApplication");
    }

}

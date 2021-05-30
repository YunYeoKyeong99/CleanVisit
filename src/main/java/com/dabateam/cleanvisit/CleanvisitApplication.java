package com.dabateam.cleanvisit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dabateam.cleanvisit.mapper")
public class CleanvisitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanvisitApplication.class, args);
    }

}

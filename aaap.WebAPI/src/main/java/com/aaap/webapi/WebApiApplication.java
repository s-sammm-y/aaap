package com.aaap.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.aaap")
@EnableScheduling
public class WebApiApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApiApplication.class, args);
    }
}

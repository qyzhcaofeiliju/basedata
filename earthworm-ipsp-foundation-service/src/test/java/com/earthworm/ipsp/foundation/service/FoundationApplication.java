package com.earthworm.ipsp.foundation.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.earthworm")
public class FoundationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoundationApplication.class,args);
    }
}

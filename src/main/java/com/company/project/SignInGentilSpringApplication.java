package com.company.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.company.project")
public class SignInGentilSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignInGentilSpringApplication.class, args);
    }
}

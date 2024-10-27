package com.task.signingentilspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.task.signingentilspring.repository")
@EntityScan(basePackages = "com.task.signingentilspring.model")
public class SignInGentilSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignInGentilSpringApplication.class, args);
    }
}

package com.zkz.email;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@SpringBootApplication
public class ApplicationEmail {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEmail.class, args);
    }

    @Bean
    @Order(999)
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("===>>> Let's inspect the beans provided by Spring Boot <<<===");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
//                 System.out.println(beanName);
            }
            System.out.println("===>>> Let's inspect the beans provided by Spring Boot <<<===");
        };
    }

}

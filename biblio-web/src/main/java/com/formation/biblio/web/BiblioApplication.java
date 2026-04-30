package com.formation.biblio.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.formation.biblio")
@EntityScan(basePackages = "com.formation.biblio.domain")
@EnableJpaRepositories(basePackages = "com.formation.biblio.service")
public class BiblioApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiblioApplication.class, args);
    }
}
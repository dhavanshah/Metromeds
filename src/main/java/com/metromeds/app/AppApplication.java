package com.metromeds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages ={"com.metromeds.app.repository"})
@EntityScan(basePackages ={ "com.metromeds.app.models"})
@EnableTransactionManagement
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppApplication.class, args);
    }

}
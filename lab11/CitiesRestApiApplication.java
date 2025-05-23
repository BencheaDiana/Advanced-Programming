package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CitiesRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitiesRestApiApplication.class, args);
    }
}
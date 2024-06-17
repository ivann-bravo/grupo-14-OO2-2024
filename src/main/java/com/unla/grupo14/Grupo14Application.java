package com.unla.grupo14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Grupo14Application {

    public static void main(String[] args) {
        SpringApplication.run(Grupo14Application.class, args);
    }
}
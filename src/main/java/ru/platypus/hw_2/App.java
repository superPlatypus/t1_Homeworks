package ru.platypus.hw_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.platypus.hw_2")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
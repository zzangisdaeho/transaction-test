package com.example.test_transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TestTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTransactionalApplication.class, args);
    }

}

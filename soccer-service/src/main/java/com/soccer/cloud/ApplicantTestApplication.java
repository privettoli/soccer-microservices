package com.soccer.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public interface ApplicantTestApplication {
    static void main(String... args) {
        SpringApplication.run(ApplicantTestApplication.class, args);
    }
}

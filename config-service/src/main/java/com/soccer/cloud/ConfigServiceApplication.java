package com.soccer.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public interface ConfigServiceApplication {
    static void main(String... args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}

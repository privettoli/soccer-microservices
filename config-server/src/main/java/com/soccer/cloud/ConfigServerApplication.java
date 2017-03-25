package com.soccer.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public interface ConfigServerApplication {
	static void main(String... args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}

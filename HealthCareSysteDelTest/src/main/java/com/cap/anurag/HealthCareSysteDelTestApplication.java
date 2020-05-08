package com.cap.anurag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication

public class HealthCareSysteDelTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareSysteDelTestApplication.class, args);
	}

}

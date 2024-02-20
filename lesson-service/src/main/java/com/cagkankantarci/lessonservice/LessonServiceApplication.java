package com.cagkankantarci.lessonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LessonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LessonServiceApplication.class, args);
	}

}

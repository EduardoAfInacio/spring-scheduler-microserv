package com.eduardoinacio.spring_scheduler_microserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringSchedulerMicroservApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSchedulerMicroservApplication.class, args);
	}

}

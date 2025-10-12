package com.octalsystems.votehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VotehubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotehubApiApplication.class, args);
	}

}

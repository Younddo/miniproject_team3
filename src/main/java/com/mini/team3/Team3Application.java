package com.mini.team3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Team3Application {

	public static void main(String[] args) {
		SpringApplication.run(Team3Application.class, args);
	}

}

package com.devteam.marketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketingApplication.class, args);
	}

}

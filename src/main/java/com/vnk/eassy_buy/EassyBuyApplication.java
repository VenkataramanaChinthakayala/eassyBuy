package com.vnk.eassy_buy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EassyBuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EassyBuyApplication.class, args);
	}

}

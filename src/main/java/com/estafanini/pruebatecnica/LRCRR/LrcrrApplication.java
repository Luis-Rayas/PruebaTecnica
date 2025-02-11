package com.estafanini.pruebatecnica.LRCRR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LrcrrApplication {

	public static void main(String[] args) {
		SpringApplication.run(LrcrrApplication.class, args);
	}

}

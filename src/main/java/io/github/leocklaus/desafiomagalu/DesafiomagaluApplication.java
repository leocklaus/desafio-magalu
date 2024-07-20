package io.github.leocklaus.desafiomagalu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DesafiomagaluApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiomagaluApplication.class, args);
	}

}

package com.mugja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MugjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MugjaApplication.class, args);
	}

}

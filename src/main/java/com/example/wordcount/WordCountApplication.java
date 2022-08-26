package com.example.wordcount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class WordCountApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordCountApplication.class, args);
		System.out.print("Application started");
	}

}

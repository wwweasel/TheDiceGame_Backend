package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TheDiceGameBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheDiceGameBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// The following line allows ALL domain
				registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE","OPTIONS");//.allowedOrigins("http://localhost:8080");

			}
		};
	}
}

package com.jewel.onlineelectoralsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OnlineElectoralSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineElectoralSystemApplication.class, args);
	}

	//http://localhost:5173 to 8080
	//Cross origin request (COR) not allow by default
	//Allow all request only from http://localhost:3000
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:5173/");
			}
		};
	}

}

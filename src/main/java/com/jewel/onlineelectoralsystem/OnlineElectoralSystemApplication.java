package com.jewel.onlineelectoralsystem;

import com.jewel.onlineelectoralsystem.service.RefreshTokenInterceptor;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH","OPTIONS")
						.allowCredentials(true)  //// Allow cookies for cross-origin requests
						.allowedHeaders("Content-Type", "Authorization")
						.allowedOrigins("http://localhost:5173/");
			}

		};
	}

//	@Autowired
//	private RefreshTokenInterceptor refreshTokenInterceptor;
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		System.out.println("interceptor calling.....");
//		registry.addInterceptor(refreshTokenInterceptor)
//				.addPathPatterns("/api/v1/secure/**","/api/v1/admin-user/**","/api/v1/cast/**") ;// Apply interceptor to protected routes
//
//	}

}



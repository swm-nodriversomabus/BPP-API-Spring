package com.example.api;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableRedisHttpSession
@EnableJpaAuditing
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	// cors 설정
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry){
				registry.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true) // 자격증명 허용
						.maxAge(3600); // 하영 시간
			}
		};
	}
}
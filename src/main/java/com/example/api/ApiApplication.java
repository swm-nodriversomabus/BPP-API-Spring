package com.example.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "BPP API Document", version = "1.0", description = "BPP API Document Swagger"))
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
package com.example.api.auth.config;

import com.example.api.auth.filter.JwtAuthFilter;
import com.example.api.auth.filter.JwtExceptionFilter;
import com.example.api.auth.handler.MyAuthenticationFailureHandler;
import com.example.api.auth.handler.MyAuthenticationSuccessHandler;
import com.example.api.auth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.example.api.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final MyAuthenticationFailureHandler oAuth2LoginFailureHandler;
    private final MyAuthenticationSuccessHandler oAUth2LoginSuccessHandler;
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtExceptionFilter jwtExceptionFIlter;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository;
    private static final String[] PERMIT_URL_ARRAY = {
            // swagger
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(authorizeRequests -> // 인증 설정
                authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN") // 이 URL로 오는 요청들은 admin 권한만 접근 가능
                        .requestMatchers("/auth/**").permitAll() // auth로 오는 애들은 일단 인증 없이 가능
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                        .requestMatchers("/aws/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                        .anyRequest().authenticated()); // 그 외는 전부 인증 필요

        httpSecurity.oauth2Login(oauth2 -> { // oauth2 로그인 설정 시작
            oauth2.authorizationEndpoint(authorizationEndpointConfig ->
                    authorizationEndpointConfig
                            .baseUri("/oauth2/authorization")
                            .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository)
            );
            oauth2.redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig.baseUri("/*/oauth2/code/*"));
            oauth2.userInfoEndpoint( // oauth2 로그인 시 사용자 정보를 가져오는 엔드포인트와 사용자 서비스 설정
                    userInfoEndpointConfig ->
                            userInfoEndpointConfig.userService(customOAuth2UserService));

            oauth2.failureHandler(oAuth2LoginFailureHandler);//핸들러
            oauth2.successHandler(oAUth2LoginSuccessHandler);
        });
//        httpSecurity.logout(logout -> logout.logoutSuccessUrl("/"));

        return httpSecurity
                .addFilterBefore(jwtAuthFilter, OAuth2LoginAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFIlter, JwtAuthFilter.class) // jwt AuthFilter 앞에 추가
                .build();
    }

//    @Bean
//    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
//        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

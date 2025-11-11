package com.example.umc9th.global.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public static CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 Origin List
        ArrayList<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("http://localhost:8080");
        configuration.setAllowedOrigins(allowedOrigins);

        // 허용할 HTTP METHOD
        ArrayList<String> allowedHttpMethods = new ArrayList<>();
        allowedHttpMethods.add("GET");
        allowedHttpMethods.add("POST");
        allowedHttpMethods.add("PATCH");
        allowedHttpMethods.add("DELETE");
        allowedHttpMethods.add("PUT");
        configuration.setAllowedMethods(allowedHttpMethods);

        // 하용할 요청 헤더 - 모든 헤더 허용
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        //자격 증명(쿠키, 인증정보 등)을 포함할 수 있는지 여부
        configuration.setAllowCredentials(true);

        // 1시간 동안 preflight 결과 캐싱, 필수는 아님
        // 하는이유 - 새로고침 같은거하면 계속 preflight보냄
        configuration.setMaxAge(3600L);

        // URL 패턴 등록, "/**" - 모든 경로에 대해 위의 CORS 정책 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        // CORS 설정 소스를 반환
        return source;
    }
}

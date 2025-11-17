package com.example.umc9th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    // 아래 3개는 Swagger에 대한 URL
    private String[] allowUrl = {
        "/auth/sign-up",
//        "/swagger-ui/**",
//        "/swagger-resources/**",
//        "/v3/api-docs/**",
    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 어떤 URL에 Security를 걸 것인지 permitAll을 허용, hasRole은 특정 role이 있어야 허용, authenticated는 인증 필요
            .authorizeHttpRequests(request -> request
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated()
            )
            // CSRF 비활성화
            .csrf(AbstractHttpConfigurer::disable)
            // Http Basic 인증 방식 비활성화
            .httpBasic(AbstractHttpConfigurer::disable)
            // CORS 적용
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // formLogin 설정
            .formLogin(formLogin -> formLogin
                // Form login에서 사용하는 SecurityContextRepository 설정
                .securityContextRepository(securityContextRepository())
                // 로그인 성공 시 URL, 보통은 SuccessfulHandler를 많이 사용하지만 간단하게 보기 위해 이 방식 사용
                .defaultSuccessUrl("/swagger-ui/index.html")
            )
            // 세션 관리 방식 설정, IF_REQUIRED는 필요 시에만 세션을 생성
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            // SecurityContext에서 사용할 SecurityContextRepository 설정
            .securityContext(context -> context
                .securityContextRepository(securityContextRepository())
            )
        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:8080"
        ));

        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        configuration.setMaxAge(3600L);

        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", "Content-Type"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
        // AuthenticationProvider에서 사용할 passwordEncoder 설정
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
        // SecurityContextRepository 빈 등록
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}
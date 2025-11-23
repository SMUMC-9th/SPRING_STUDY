package com.example.umc9th.global.security.config;

import com.example.umc9th.global.auth.CustomDetailService;
import com.example.umc9th.global.auth.CustomAccessDeniedHandler;
import com.example.umc9th.global.auth.CustomEntryPoint;
import com.example.umc9th.global.security.jwt.JwtFilter;
import com.example.umc9th.global.security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomDetailService customDetailService;
    private final CustomEntryPoint customEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final ObjectMapper objectMapper;

    private final String[] allowUrl = {
            "/auth/sign-up",
            "/auth/login",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(allowUrl).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
//                .formLogin(formLogin -> formLogin
//                        .securityContextRepository(securityContextRepository())
//                        .defaultSuccessUrl("/swagger-ui/index.html")
//                )
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                )
//                .securityContext(context -> context
//                        .securityContextRepository(securityContextRepository())
//                )
        ;

        return http.build();
    }

    @Bean
    Filter jwtFilter() {
        return new JwtFilter(jwtUtil, customDetailService, objectMapper);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}

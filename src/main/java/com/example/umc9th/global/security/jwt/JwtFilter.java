package com.example.umc9th.global.security.jwt;

import com.example.umc9th.global.apiPayload.ErrorResponseUtil;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.auth.CustomDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomDetailService customDetailService;
    private final ObjectMapper objectMapper;

    private static final String[] EXCLUDE_URLS = {
            "/oauth2/authorization/kakao",
            "/auth/callback/kakao",
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        for (String exclude : EXCLUDE_URLS) {
            if (path.startsWith(exclude)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        try {
            // 헤더에서 토큰 추출
            String token = request.getHeader("Authorization");

            // 토큰이 없거나 유효하지 않으면 바로 다음 필터로
            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            token = token.substring(7);


            // 토큰 만료 및 서명 검증
            if(!jwtUtil.isValid(token)) {
                ErrorResponseUtil.sendError(response, GeneralErrorCode.INVALID_TOKEN, objectMapper);
                return;
            }

            // 토큰에서 username 꺼내기
            String username = jwtUtil.getUsername(token);
            if (username == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // UserDetails 불러오기
            UserDetails userDetails = customDetailService.loadUserByUsername(username);

            // Authentication 객체 만들기
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            // SecurityContextHolder에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            ErrorResponseUtil.sendError(response, GeneralErrorCode.UNAUTHORIZED_401, objectMapper);
        }
    }
}

package com.example.umc9th.global.jwt;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.auth.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            //헤더에서 토큰 추출
            String header = request.getHeader("Authorization");
            String token = null;
            if(header != null && header.startsWith("Bearer ")){
                token = header.substring(7);
            }
            //토큰 검증
            if(token != null) {
                if(jwtUtil.isValid(token)){
                    //토큰에서 사용자 정보 가져오기
                    String username = jwtUtil.getUsername(token);
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                    //SecurityContextHolder에 인증 정보 넣기
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(request, response);
        }
        catch (Exception e){
            // 예외 처리
            BaseErrorCode code = GeneralErrorCode.FORBIDDEN_403;
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(code.getStatus().value());

            ApiResponse<Object> errorResponse = ApiResponse.onFailure(code, null);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }
}

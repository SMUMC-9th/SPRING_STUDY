package com.example.umc9th.global.auth;

public interface UserDetatilsService {
    UserDetails loadUserByUsername(String username);
}

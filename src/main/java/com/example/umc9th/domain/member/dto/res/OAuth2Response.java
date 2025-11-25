package com.example.umc9th.domain.member.dto.res;

public interface OAuth2Response {

    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}

package com.example.umc9th.domain.member.dto.res;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;


    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> account = (Map<String, Object>) attribute.get("kakao_account");
        if (account == null) {
            return null;
        }
        return account.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> account = (Map<String, Object>) attribute.get("kakao_account");
        if (account == null) {
            return null;
        }
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (profile == null) {
            return null;
        }

        return profile.get("nickname").toString();
    }
}

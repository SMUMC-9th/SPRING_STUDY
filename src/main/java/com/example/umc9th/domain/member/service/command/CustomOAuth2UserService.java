package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.res.CustomOAuth2User;
import com.example.umc9th.domain.member.dto.res.KakaoResponse;
import com.example.umc9th.domain.member.dto.res.OAuth2Response;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.ROLE;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest); // 부모 클래스의 메서드를 통해 유저 정보를 받아옴
        log.info("getAttributes : {}", oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuthResponse = null;
        if(registrationId.equals("kakao")){
            oAuthResponse = new KakaoResponse(oAuth2User.getAttributes());
        }
        else{
            // 다른 소셜 로그인 시 확장 가능
            throw new OAuth2AuthenticationException(MemberErrorCode.OAUTH_USER_INFO_FAIL.getCode());
        }
        String email = oAuthResponse.getEmail();
        Member existData = memberRepository.findByEmail(email).orElse(null);
        ROLE role = ROLE.USER;

        if(existData == null){
            Member member = Member.builder()
                    .username(oAuthResponse.getName())
                    .email(email)
                    .password(null)
                    .role(role)
                    .build();
            memberRepository.save(member);
        }
        return new CustomOAuth2User(oAuthResponse, role);
    }
}

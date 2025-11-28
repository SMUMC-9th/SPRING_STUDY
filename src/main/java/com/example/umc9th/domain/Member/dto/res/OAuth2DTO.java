package com.example.umc9th.domain.Member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

public class OAuth2DTO {

    @Getter
    @Builder
    public static class OAuth2TokenDTO {
        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("expires_in")
        private Long expiresIn;

        @JsonProperty("refresh_expires_in")
        private Long refreshExpiresIn;

        private String scope;
    }

    @Getter
    public static class KakaoProfile {
        private Long id;
        private String connectedAt;
        private Properties properties;

        @JsonProperty("kakao_account")
        private KakaoAccount kakaoAccount;

        @Getter
        public class Properties {
            private String nickname;

            @JsonProperty("profile_image")
            private String profileImage;

            @JsonProperty("thumbnail_image")
            private String thumbnailImage;
        }

        @Getter
        public class KakaoAccount {
            private String email;

            @JsonProperty("is_email_verified")
            private Boolean is_emailVerified;

            @JsonProperty("email_needs_agreement")
            private Boolean emailNeedsAgreement;

            @JsonProperty("has_email")
            private Boolean hasEmail;

            @JsonProperty("profile_nickname_needs_agreement")
            private Boolean profileNicknameNeedsAgreement;

            @JsonProperty("profile_image_needs_agreement")
            private Boolean profileImageNeedsAgreement;

            @JsonProperty("email_needs_argument")
            private Boolean emailNeedsArgument;

            @JsonProperty("is_email_valid")
            private Boolean isEmailValid;

            private Profile profile;

            @Getter
            public class Profile {
                private String nickname;

                @JsonProperty("thumbnail_image_url")
                private String thumbnailImageUrl;

                @JsonProperty("profile_image_url")
                private String profileImageUrl;

                @JsonProperty("is_default_nickname")
                private Boolean isDefaultNickname;

                @JsonProperty("is_default_image")
                private Boolean isDefaultImage;
            }
        }
    }
}

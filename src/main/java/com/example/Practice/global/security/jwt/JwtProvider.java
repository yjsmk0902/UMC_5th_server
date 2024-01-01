package com.example.Practice.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Practice.domain.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Getter
@Component
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERID_CLAIM = "email";
    private static final String BEARER = "Bearer ";

    private final MemberRepository memberRepository;

    public String createAccessToken(String email) {
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(new Date().getTime() + accessTokenExpirationPeriod))
                .withClaim(USERID_CLAIM, email)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String createRefreshToken() {
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(new Date().getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));
    }

    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(accessHeader, accessToken);
        log.info("AccessToken 재발행 : {}", accessToken);
    }

    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(accessHeader, accessToken);
        response.setHeader(refreshHeader, refreshToken);
        log.info("Access, Refresh Header 설정 완료");
    }

    public Optional<String> extractToken(HttpServletRequest request, String header) {
        return Optional.ofNullable(request.getHeader(header))
                .filter(token -> token.startsWith(BEARER))
                .map(token -> token.replace(BEARER, ""));
    }

    public Optional<String> extractClaim(String accessToken, String claim) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(accessToken)
                    .getClaim(claim)
                    .asString());
        } catch (Exception e) {
            log.error("검증 되지 않은 AccessToken");
            return Optional.empty();
        }
    }

    public void updateRefreshToken(String email, String refreshToken) {
        memberRepository.findByEmail(email)
                .ifPresentOrElse(
                        member -> member.updateRefreshToken(refreshToken),   //TODO: refreshToken 필드 구현 필요
                        () -> {
                            throw new RuntimeException(); //TODO: Exception 구현 필요
                        }
                );
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("토큰 검증 실패 {}", e.getMessage());
            return false;
        }
    }

    public Long getExpiration(String accessToken) {
        Date expiration = JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken)
                .getExpiresAt();
        return expiration.getTime() - new Date().getTime();
    }

    public String getMemberIdToToken(String accessToken) {
        return JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken)
                .getClaim(USERID_CLAIM).asString();
    }
}

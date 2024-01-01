package com.example.Practice.global.security.filter;

import com.example.Practice.domain.dto.AuthInfoDTO;
import com.example.Practice.domain.entity.Member;
import com.example.Practice.domain.repository.MemberRepository;
import com.example.Practice.global.security.jwt.JwtProvider;
import com.example.Practice.global.util.PasswordUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String NO_CHECK_URL = "/api/auth/login";
    private static final String USERID_CLAIM = "email";

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtProvider.extractToken(request, refreshHeader)
                .filter(jwtProvider::isTokenValid)
                .orElse(null);

        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }
        checkAccessTokenAndAuthentication(request, response, filterChain);
    }

    private void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        memberRepository.findByRefreshToken(refreshToken)
                .ifPresent(member -> {
                    String reIssueRefreshToken = reIssueRefreshToken(member);
                    jwtProvider.sendAccessAndRefreshToken(
                            response,
                            jwtProvider.createAccessToken(member.getEmail()),
                            reIssueRefreshToken
                    );
                });
    }

    private String reIssueRefreshToken(Member member) {
        String reIssuedRefreshToken = jwtProvider.createRefreshToken();
        member.updateRefreshToken(reIssuedRefreshToken);
        memberRepository.saveAndFlush(member);
        return reIssuedRefreshToken;
    }

    private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        jwtProvider.extractToken(request, accessHeader)
                .filter(jwtProvider::isTokenValid)
                .ifPresent(accessToken -> {
                    validBlackToken(accessToken);
                    jwtProvider.extractClaim(accessToken, USERID_CLAIM)
                            .ifPresent(email -> memberRepository.findByEmail(email)
                                    .ifPresent(this::saveAuthentication));
                });
        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(Member member) {
        String password = member.getPassword();
        if (password == null) password = PasswordUtil.generateRandomPassword();
        UserDetails userDetails = AuthInfoDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .username(member.getUsername())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                authoritiesMapper.mapAuthorities(userDetails.getAuthorities())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void validBlackToken(String accessToken) {
        String blackToken = redisTemplate.opsForValue().get(accessToken);
        if (StringUtils.hasText(blackToken)) {
            throw new RuntimeException(); //TODO: Exception 구현 필요
        }
    }
}

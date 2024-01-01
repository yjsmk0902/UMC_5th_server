package com.example.Practice.application.member.command;

import com.example.Practice.application.member.query.MemberBaseService;
import com.example.Practice.domain.dto.AuthInfoDTO;
import com.example.Practice.domain.dto.Member.MemberRequest;
import com.example.Practice.domain.dto.Member.MemberResponse;
import com.example.Practice.domain.entity.Member;
import com.example.Practice.domain.repository.MemberRepository;
import com.example.Practice.global.mapstruct.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberBaseService memberBaseService;
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberBaseService.findMemberByEmail(email);

        return AuthInfoDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .username(member.getUsername())
                .build();
    }

    @Override
    public MemberResponse.SignUpResultDTO signUp(MemberRequest.SignUpDTO request) {
        memberBaseService.validExistsMember(request);

        Member member = memberMapper.toEntity(request);
        memberRepository.save(member);
        return MemberResponse.SignUpResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

package com.example.Practice.application.member.command;

import com.example.Practice.domain.dto.Member.MemberRequest;
import com.example.Practice.domain.dto.Member.MemberResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberCommandService extends UserDetailsService {

    MemberResponse.SignUpResultDTO signUp(MemberRequest.SignUpDTO request);

}

package com.example.Practice.presentation;

import com.example.Practice.application.member.command.MemberCommandService;
import com.example.Practice.domain.dto.Member.MemberRequest;
import com.example.Practice.domain.dto.Member.MemberResponse;
import com.example.Practice.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponse.SignUpResultDTO> signUpMember(
            @RequestBody MemberRequest.SignUpDTO request
    ) {
        MemberResponse.SignUpResultDTO resultDTO = memberCommandService.signUp(request);
        return ApiResponse.onSuccess(resultDTO);
    }

}

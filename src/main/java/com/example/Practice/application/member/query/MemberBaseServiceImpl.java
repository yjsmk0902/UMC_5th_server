package com.example.Practice.application.member.query;

import com.example.Practice.domain.dto.Member.MemberRequest;
import com.example.Practice.domain.entity.Member;
import com.example.Practice.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberBaseServiceImpl implements MemberBaseService{

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);    //TODO: Exception 구현 필요
    }

    @Override
    public Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);    //TODO: Exception 구현 필요
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);    //TODO: Exception 구현 필요
    }

    @Override
    public void validExistsMember(MemberRequest.SignUpDTO request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new RuntimeException();   //TODO: Exception 구현 필요
        }
        if (memberRepository.existsByUsername(request.username())) {
            throw new RuntimeException();   //TODO: Exception 구현 필요
        }
    }
}

package com.example.Practice.application.member.query;

import com.example.Practice.domain.dto.Member.MemberRequest;
import com.example.Practice.domain.entity.Member;

public interface MemberBaseService {

    Member findMemberById(Long memberId);

    Member findMemberByUsername(String username);

    Member findMemberByEmail(String email);

    void validExistsMember(MemberRequest.SignUpDTO request);
}

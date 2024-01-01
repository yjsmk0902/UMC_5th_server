package com.example.Practice.domain.dto.Member;

import com.example.Practice.domain.common.Address;
import com.example.Practice.domain.enums.Gender;
import com.example.Practice.domain.enums.SocialType;

public class MemberRequest {

    public record SignUpDTO(
            String email,
            String password,
            String username,
            Gender gender,
            Integer age,
            Address address,
            String addressDetailed,
            SocialType socialType
    ) {
    }
}

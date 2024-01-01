package com.example.Practice.global.mapstruct.member;

import com.example.Practice.domain.dto.Member.MemberRequest;
import com.example.Practice.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    @Mapping(target = "email")
    @Mapping(target = "password")
    @Mapping(target = "username")
    @Mapping(target = "gender")
    @Mapping(target = "age")
    @Mapping(target = "address")
    @Mapping(target = "addressDetailed")
    @Mapping(target = "socialType")
    Member toEntity(MemberRequest.SignUpDTO request);

}

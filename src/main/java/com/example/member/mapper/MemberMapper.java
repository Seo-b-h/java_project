package com.example.member.mapper;

import com.example.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void register(Member member) throws Exception;
}

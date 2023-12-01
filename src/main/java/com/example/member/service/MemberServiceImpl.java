package com.example.member.service;

import com.example.member.mapper.MemberMapper;
import com.example.member.model.Member;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    public void register(Member member) throws Exception {
        memberMapper.register(member);
    }
}

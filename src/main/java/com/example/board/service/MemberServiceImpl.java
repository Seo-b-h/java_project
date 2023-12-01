package com.example.board.service;

import com.example.board.mapper.MemberMapper;
import com.example.board.model.Member;
import com.example.board.service.MemberService;
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

    @Override
    public Member login(Member member) throws Exception {
        return memberMapper.login(member);
    }

    @Override
    public void memberUpdate(Member member) throws Exception {
        memberMapper.memberUpdate(member);
    }
}

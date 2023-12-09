/** MemberServiceImpl.java */
/*
 * Programmed by 서보혁
 * 회원 관리에 대한 서비스의 구현을 해놓은 클래스이다.
 * Date : 2023.12.01.
 * Last Update : 2023.12.01.
 * Major update content : Source code 최초 작성 by 서보혁
 */
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

    @Override
    public void memberDelete(Member member) throws Exception {
        memberMapper.memberDelete(member);
    }

    @Override
    public int userChk(Member member)throws Exception {
        return memberMapper.userChk(member);
    }

    @Override
    public  int idChk(Member member) throws Exception {
        return memberMapper.idChk(member);
    }
}

package com.example.board.service;

import com.example.board.model.Member;

public interface MemberService {
    public void register(Member member) throws Exception;

    public Member login(Member member) throws  Exception;

    public void memberUpdate(Member member) throws Exception;

    public void memberDelete(Member member) throws Exception;
}

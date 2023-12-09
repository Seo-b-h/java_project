/** MemberService.java */
/*
 * Programmed by 서보혁
 * 회원 관리에 대한 서비스의 역할에 대해 정의한 클래스이다.
 * Date : 2023.12.01.
 * Last Update : 2023.12.01.
 * Major update content : Source code 최초 작성 by 서보혁
 */
package com.example.board.service;

import com.example.board.model.Member;

public interface MemberService {
    public void register(Member member) throws Exception;

    public Member login(Member member) throws  Exception;

    public void memberUpdate(Member member) throws Exception;

    public void memberDelete(Member member) throws Exception;

    public int userChk(Member member) throws Exception;

    public int idChk(Member member) throws Exception;
}

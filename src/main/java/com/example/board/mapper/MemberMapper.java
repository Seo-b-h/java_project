/** MemberMapper.java */
/*
 * Programmed by 서보혁
 * DB에 접근해야 하는 메소드들 중 회원 정보와 관련된 메소드들을 생성해놓은 소스 파일이다.
 * Date : 2023.12.01.
 * Last Update : 2023.12.01.
 * Major update content : Source code 최초 작성 by 서보혁
 */
package com.example.board.mapper;

import com.example.board.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void register(Member member) throws Exception;

    public Member login(Member member) throws Exception;

    public void memberUpdate(Member member) throws Exception;

    public void memberDelete(Member member) throws Exception;

    public int userChk(Member member) throws Exception;

    public int idChk(Member member) throws Exception;
}

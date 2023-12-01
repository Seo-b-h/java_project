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
}

package com.example.board.mapper;

import com.example.board.model.Board;
import com.example.board.model.Criteria;
import com.example.board.model.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //ComponentScan 대상, 데이터 접근 계층(DAO)
public interface BoardMapper {
    //DB에 접근해야 하는 메소드들을 나열(DAO와 동일한 역할을 함)
    //BoardMapper.xml의 id를 메소드명으로 해야함 !!

    public void write(Board board) throws Exception; //게시판 글쓰기

    public List<Board> listPage(SearchCriteria scri) throws Exception; //게시물 목록 조회

    public int listCount(SearchCriteria scri) throws Exception; //게시물 총 개수

    public Board read(int boardNumber) throws  Exception; //게시물 조회

    public void update(Board board) throws Exception; //게시물 수정

    public void delete(int bno) throws Exception; //게시물 삭제
}

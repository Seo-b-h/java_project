/** BoardMapper.java */
/*
 * Programmed by 최민규
 * DB에 접근해야 하는 메소드들 중 글과 관련된 메소드들을 생성해놓은 소스 파일이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 * Last Update : 2023.12.04.
 * Major update content : 게시물 조회 시 조회수 증가 함수 추가 by 서보혁
 * Last Update : 2023.12.15.
 * Major update content : 파일 업로드, 조회 함수 추가 by 서보혁
 * Last Update : 2023.12.16.
 * Major update content : 파일 번호 조회 함수 추가 by 서보혁
 */
package com.example.board.mapper;

import com.example.board.model.Board;
import com.example.board.model.Criteria;
import com.example.board.model.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    public void boardHit(int bno) throws Exception; // 게시물 조회수 증가

    public void insertFile(Map<String, Object> map) throws Exception; // 파일 업로드

    public List<Map<String, Object>> selectFileList(int BoardNumber) throws Exception; // 파일 조회

    public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception; // 파일 번호 조회
}

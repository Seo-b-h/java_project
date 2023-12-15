/** BoardService.java */
/*
 * Programmed by 최민규
 * 글 작성에 관한 서비스의 역할에 대해 정의한 클래스이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 * Last Update : 2023.12.15.
 * Major update content : write 함수 파일 업로드, 조회 기능 추가 by 서보혁
 * Last Update : 2023.12.16.
 * Major update content : 파일 번호 조회 기능 추가 by 서보혁
 */
package com.example.board.service;

import com.example.board.model.Board;
import com.example.board.model.Criteria;
import com.example.board.model.SearchCriteria;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

public interface BoardService {
    //비즈니스 계층으로 역할과 구현을 분리한다 !! -> DIP(interface 사용으로)
    //interface -> 역할을 명시
    //모든 동작은 테스트 코드로 확인할 것 !! -> TDD 방식
    public void write(Board board, MultipartHttpServletRequest mpRequest) throws Exception;

    public List<Board> listPage(SearchCriteria scri) throws Exception;

    public int listCount(SearchCriteria scri) throws Exception;

    public Board read(int boardNumber) throws Exception;

    public void update(Board board) throws Exception;

    public void delete(int bno) throws Exception;

    public List<Map<String, Object>> selectFileList(int BoardNumber) throws Exception;

    public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

}

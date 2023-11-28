package com.example.board.service;

import com.example.board.model.Board;
import com.example.board.model.Criteria;
import com.example.board.model.SearchCriteria;

import java.util.List;

public interface BoardService {
    //비즈니스 계층으로 역할과 구현을 분리한다 !! -> DIP(interface 사용으로)
    //interface -> 역할을 명시
    //모든 동작은 테스트 코드로 확인할 것 !! -> TDD 방식
    public void write(Board board) throws Exception;

    public List<Board> listPage(SearchCriteria scri) throws Exception;

    public int listCount(SearchCriteria scri) throws Exception;

    public Board read(int boardNumber) throws Exception;

    public void update(Board board) throws Exception;

    public void delete(int bno) throws Exception;
}

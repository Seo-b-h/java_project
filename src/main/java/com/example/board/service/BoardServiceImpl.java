package com.example.board.service;

import com.example.board.controller.BoardController;
import com.example.board.mapper.BoardMapper;
import com.example.board.model.Board;
import com.example.board.model.Criteria;
import com.example.board.model.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    //필수사항은 아니지만 Service에 구현된 메소드명과 mapper에 구현된 메소드명을 동일하게 할 것

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardMapper boardMapper;

    @Override
    public void write(Board board) throws Exception
    {
        boardMapper.write(board);
    }

    @Override
    public List<Board> listPage(SearchCriteria scri) throws Exception
    {
        List<Board> boardList = boardMapper.listPage(scri); //Ctrl + Alt + V : 리턴 변수 생성

        return boardList;
    }

    public int listCount(SearchCriteria scri) throws Exception
    {
        int result = boardMapper.listCount(scri);

        return result;
    }

    @Override
    public Board read(int boardNumber) throws Exception
    {
        Board readBoard = boardMapper.read(boardNumber);

        return readBoard;
    }

    @Override
    public void update(Board board) throws Exception
    {
        boardMapper.update(board);
    }

    @Override
    public void delete(int bno) throws Exception
    {
        boardMapper.delete(bno);
    }

}

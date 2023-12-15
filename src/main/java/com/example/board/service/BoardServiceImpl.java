/**
 * BoardServiceImpl.java
 */
/*
 * Programmed by 최민규
 * 글 작성에 관한 서비스의 구현을 해놓은 클래스이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 * * Last Update : 2023.12.04.
 * Major update content : 글 조회 시 조회수 증가 기능 추가 by 서보혁
 * Last Update : 2023.12.15.
 * Major update content : write 함수 파일 업로드, 조회 기능 추가 by 서보혁
 * Last Update : 2023.12.16.
 * Major update content : 파일 번호 조회, 파일 수정, 삭제 기능 추가 by 서보혁
 */
package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.model.Board;
import com.example.board.model.SearchCriteria;
import com.example.board.util.FileUtils;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    //필수사항은 아니지만 Service에 구현된 메소드명과 mapper에 구현된 메소드명을 동일하게 할 것

    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

    private final BoardMapper boardMapper;

    @Resource(name = "fileUtils")
    private final FileUtils fileUtils;

    @Override
    public void write(Board board, MultipartHttpServletRequest mpRequest) throws Exception {
        boardMapper.write(board);
        logger.info("boardNumber : {}", board.getBoardNumber());
        List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(board, mpRequest);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            boardMapper.insertFile(list.get(i));
        }
    }

    @Override
    public List<Board> listPage(SearchCriteria scri) throws Exception {
        List<Board> boardList = boardMapper.listPage(scri); //Ctrl + Alt + V : 리턴 변수 생성

        return boardList;
    }

    public int listCount(SearchCriteria scri) throws Exception {
        int result = boardMapper.listCount(scri);

        return result;
    }

    @Override
    public Board read(int boardNumber) throws Exception {
        boardMapper.boardHit(boardNumber);
        Board readBoard = boardMapper.read(boardNumber);

        return readBoard;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void update(Board board, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
        boardMapper.update(board);
        //logger.info("updateboardNumber : {}", board.getBoardNumber());
        List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(board, files, fileNames, mpRequest);
        for(Map<String, Object> tempMap : list) {
            //logger.info("tempMap : {}", tempMap);
            if(tempMap.get("IS_NEW").equals("Y")) boardMapper.insertFile(tempMap);
            else boardMapper.updateFile(tempMap);
        }
    }

    @Override
    public void delete(int bno) throws Exception {
        boardMapper.delete(bno);
    }

    @Override
    public List<Map<String, Object>> selectFileList(int BoardNumber) throws Exception {
        return boardMapper.selectFileList(BoardNumber);
    }

    @Override
    public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
        return boardMapper.selectFileInfo(map);
    }


}

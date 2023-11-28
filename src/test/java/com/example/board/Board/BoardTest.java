package com.example.board.Board;

import com.example.board.mapper.BoardMapper;
import com.example.board.model.Board;
import com.example.board.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//내장 데이터베이스가 아닌 실데이터베이스(여기서는 MySQL)와 연결 시 필요한 어노테이션
public class BoardTest {

    @Autowired //주입방식
    private BoardMapper boardMapper;

    @Autowired
    private BoardService boardService;

    private static final Logger logger = LoggerFactory.getLogger(BoardTest.class);

    @Test
    @DisplayName("게시판 글쓰기 테스트") //통과 (UI완료)
    public void 게시판_글쓰기() throws Exception
    {
        //given : 이러한 환경이 주어졌을 때
        Board board = new Board("제목", "내용", "작성자", "2023-11-25");

        //when : 이런 동작을 하면
        boardService.write(board);

        //then : 이런 결과가 주어진다
    }

    @Test
    @DisplayName("게시판 목록 조회 테스트") //통과 (UI완료)
    public void 게시판_목록조회() throws Exception
    {
        //given

        //when
        //List<Board> boardList = boardService.list();

        //then
        //주의 : 게시물 번호가 내림차순으로 list에 저장됨
        //Assertions.assertThat(boardList.get(1).getTitle()).isEqualTo("제목");
    }

    @Test
    @DisplayName("게시물 조회 테스트") //통과 (UI완료)
    public void 게시물_조회() throws Exception
    {
        //given
        int selectNumber = 1;

        //when
        Board read = boardService.read(selectNumber);

        //then
        Assertions.assertThat(read.getTitle()).isEqualTo("제목입니다");
    }

    @Test
    @DisplayName("게시판 수정 테스트") //통과
    public void 게시판_수정() throws Exception
    {
        //given
        Board updateBoard = new Board(1, "제목", "내용", "작성자", "2023-11-24");

        //when
        boardService.update(updateBoard);

        //then
        Assertions.assertThat(boardService.read(1).getTitle()).isEqualTo("제목");
        Assertions.assertThat(boardService.read(1).getContent()).isEqualTo("내용");
    }

    @Test
    @DisplayName("게시물 삭제 테스트") //통과
    public void 게시물_삭제() throws Exception
    {
        //given
        int deleteNumber = 2;

        //when
        boardService.delete(2);

        //then
        //Assertions.assertThat(boardService.list().size()).isEqualTo(1);
    }


}

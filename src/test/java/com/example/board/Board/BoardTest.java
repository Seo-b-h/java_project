package com.example.board.Board;

import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.CommentMapper;
import com.example.board.model.Board;
import com.example.board.model.Comment;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(BoardTest.class);

    @Test
    @DisplayName("게시판 글쓰기 테스트") //통과 (UI완료)
    public void 게시판_글쓰기() throws Exception
    {
        //given : 이러한 환경이 주어졌을 때
        Board board = new Board("제목", "내용", "작성자", "2023-11-25");
        //when : 이런 동작을 하면
        boardService.write(board, null);

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
    @DisplayName("게시판 수정 테스트") //통과 (UI완료)
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
    @DisplayName("게시물 삭제 테스트") //통과 (UI완료)
    public void 게시물_삭제() throws Exception
    {
        //given
        int deleteNumber = 2;

        //when
        boardService.delete(2);

        //then
        //Assertions.assertThat(boardService.list().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시물 댓글 작성") //통과 (UI완료)
    public void 게시물_댓글_작성() throws Exception
    {
        //given
        Comment comment = new Comment(295, 318, "newComment", "newWriter", LocalDate.now().toString());
        //comment 객체를 만들었을 때

        //when
        commentService.writeComment(comment);
        //comment를 작성하면

        //then
        List<Comment> commentList = commentService.readComment(295);
        //commentService.readComment는 BoardNumber을 select하지 않기 때문에
        //commentNumber의 값 = BoardNumber의 값과 같은 현상이 발생한다
        Assertions.assertThat(commentList.get(0).getWriter()).isEqualTo(comment.getWriter());
        //테스트코드에서는 댓글 작성이 잘 되는지, 게시물 번호에 맞는 댓글을 가져오는지를 확인하기 위해서이므로
        //writer의 값을 isEqualTo()의 비교로 확인을 하였다

        //295번 게시물의 첫번째 댓글이 readComment로 읽어온 댓글과 동일하다는 것을 알 수 있다
    }

    @Test
    @DisplayName("댓글 수정") //통과
    public void 댓글_수정() throws Exception
    {
        //given
        Comment comment = new Comment(1, 1, "안녕~", "최민규", LocalDate.now().toString());

        //when
        commentService.updateComment(comment);
        List<Comment> commentList = commentService.readComment(1);

        //then
        Assertions.assertThat(commentList.get(0).getContent()).isEqualTo("안녕~");
    }

    @Test
    @DisplayName("댓글 삭제") //통과
    public void 댓글_삭제() throws Exception
    {
        //given
        Comment comment = new Comment(299, 307, "안녕~", "newWriter", LocalDate.now().toString());

        //when
        List<Comment> commentList = commentService.readComment(299);
        int size = commentList.size();
        commentService.deleteComment(comment);

        //then
        List<Comment> commentList_cmp = commentService.readComment(299);
        int size_cmp = commentList_cmp.size();
        Assertions.assertThat(size_cmp).isEqualTo(size - 1);
    }

}

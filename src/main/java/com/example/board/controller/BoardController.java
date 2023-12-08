package com.example.board.controller;

import com.example.board.model.*;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.service.CommentServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Member;
import java.util.List;

@Controller //@Controller + @ResponseBody = @RestController
//AJAX를 사용할 때 @ResponseBody가 필요함
@RequiredArgsConstructor //final로 생성된 변수에 대해 생성자 주입
@RequestMapping("/board/*")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    //로그를 info 레벨로 출력

    private final BoardService boardService; //게시판 서비스

    private final CommentService commentService; //댓글 서비스

    //model.addAttribute() : Controller에서 생성된 데이터를 Model 객체를 통해 View로 전달
    @GetMapping(value = "/writeView")
    public String writeView() throws Exception
    {
        //게시글 작성 화면
        logger.info("writeView");

        return "board/writeView";
    }

    @PostMapping(value = "/write")
    public String write(Board board) throws Exception
    {
        //게시글 작성
        logger.info("write");
        boardService.write(board);

        return "redirect:/board/list";
    }

    @GetMapping(value = "/list")
    public String list(HttpSession session, Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception
    {
        //게시물 목록 화면
        logger.info("list");
        session.setMaxInactiveInterval(36000);
        model.addAttribute("list", boardService.listPage(scri));

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(scri);
        pageMaker.setTotalCount(boardService.listCount(scri));

        model.addAttribute("pageMaker", pageMaker);

        return "board/list"; //View를 반환 ex) list라는 html을 반환
    }

    @GetMapping(value = "/readView")
    public String read(Board board, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception
    {
        //게시물 내용 화면
        logger.info("read");

        model.addAttribute("read", boardService.read(board.getBoardNumber()));
        model.addAttribute("scri", scri);

        List<Comment> commentList = commentService.readComment(board.getBoardNumber());
        model.addAttribute("commentList", commentList);

        return "board/readView";
    }

    @GetMapping(value = "/updateView")
    public String updateView(Board board, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception
    {
        //게시물 수정 화면
        logger.info("updateView");

        model.addAttribute("update", boardService.read(board.getBoardNumber()));
        model.addAttribute("scri", scri);

        return "board/updateView";
    }

    @PostMapping(value = "/update")
    public String update(Board board, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception
    {
        //게시물 수정
        logger.info("update");

        boardService.update(board);

        rttr.addAttribute("page", scri.getPage());
        rttr.addAttribute("perPageNum", scri.getPerPageNum());
        rttr.addAttribute("searchType", scri.getSearchType());
        rttr.addAttribute("keyword", scri.getKeyword());

        return "redirect:/board/list";
    }

    @PostMapping(value = "/delete")
    public String delete(Board board, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception
    {
        //게시물 삭제
        logger.info("delete");

        boardService.delete(board.getBoardNumber());

        rttr.addAttribute("page", scri.getPage());
        rttr.addAttribute("perPageNum", scri.getPerPageNum());
        rttr.addAttribute("searchType", scri.getSearchType());
        rttr.addAttribute("keyword", scri.getKeyword());

        return "redirect:/board/list";
    }

    @PostMapping(value = "/replyWrite")
    public String writeComment(Comment co, SearchCriteria scri, RedirectAttributes rttr) throws Exception
    {
        //댓글 작성
        logger.info("comment Write");

        commentService.writeComment(co);

        rttr.addAttribute("BoardNumber", co.getBoardNumber());
        rttr.addAttribute("page", scri.getPage());
        rttr.addAttribute("perPageNum", scri.getPerPageNum());
        rttr.addAttribute("searchType", scri.getSearchType());
        rttr.addAttribute("keyword", scri.getKeyword());

        return "redirect:/board/readView";
    }

    @GetMapping(value = "/replyUpdateView")
    public String commentUpdateView(Comment co, SearchCriteria scri, Model model) throws Exception
    {
        //댓글 수정 화면
        logger.info("reply UpdateView");

        model.addAttribute("commentUpdate", commentService.selectComment(co.getCommentNumber()));
        model.addAttribute("scri", scri);

        return "board/replyUpdateView";
    }

    @PostMapping(value = "/replyUpdate")
    public String commentUpdate(Comment co, SearchCriteria scri, RedirectAttributes rttr) throws Exception
    {
        //댓글 수정
        logger.info("reply Update");

        commentService.updateComment(co);

        rttr.addAttribute("BoardNumber", co.getBoardNumber());
        rttr.addAttribute("page", scri.getPage());
        rttr.addAttribute("perPageNum", scri.getPerPageNum());
        rttr.addAttribute("searchType", scri.getSearchType());
        rttr.addAttribute("keyword", scri.getKeyword());

        return "redirect:/board/readView";
    }

    @GetMapping(value = "/replyDeleteView")
    public String commentDeleteView(Comment co, SearchCriteria scri, Model model) throws Exception
    {
        //댓글 삭제 화면
        logger.info("reply Write");

        model.addAttribute("commentDelete", commentService.selectComment(co.getCommentNumber()));
        model.addAttribute("scri", scri);

        return "board/replyDeleteView";
    }

    @PostMapping(value = "/replyDelete")
    public String commentDelete(Comment co, SearchCriteria scri, RedirectAttributes rttr) throws Exception
    {
        //댓글 삭제
        logger.info("reply Write");

        commentService.deleteComment(co);

        rttr.addAttribute("BoardNumber", co.getBoardNumber());
        rttr.addAttribute("page", scri.getPage());
        rttr.addAttribute("perPageNum", scri.getPerPageNum());
        rttr.addAttribute("searchType", scri.getSearchType());
        rttr.addAttribute("keyword", scri.getKeyword());

        return "redirect:/board/readView";
    }
}

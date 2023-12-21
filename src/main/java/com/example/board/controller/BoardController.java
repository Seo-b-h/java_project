/** BoardController.java */
/*
 * Programmed by 최민규
 * 사용자가 서비스를 요청 시 요청을 처리하고 결과나 결과 페이지를 반환해주는 역할을 하는 소스 코드이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 * Last Update : 2023.12.04.
 * Major update content : 회원이 글 목록 접속 시 세션 시간 초기화 기능 추가 by 서보혁
 * Last Update : 2023.12.15.
 * Major update content : write 메소드 파일 업로드 기능 추가, read 메소드 파일 조회 기능 추가 by 서보혁
 * Last Update : 2023.12.16.
 * Major update content : 파일 다운로드, 수정 함수 추가 by 서보혁
 * Last Update : 2023.12.21.
 * Major update content : 글 조회 시에만 조회수 증가하도록 read 함수 수정 by 서보혁
 */
package com.example.board.controller;

import com.example.board.model.*;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.service.CommentServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.lang.reflect.Member;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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
    public String write(Board board, MultipartHttpServletRequest mpRequest) throws Exception
    {
        //게시글 작성
        logger.info("write");
        boardService.write(board, mpRequest);

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

        boardService.boardHit(board.getBoardNumber());
        model.addAttribute("read", boardService.read(board.getBoardNumber()));
        model.addAttribute("scri", scri);

        List<Comment> commentList = commentService.readComment(board.getBoardNumber());
        model.addAttribute("commentList", commentList);

        List<Map<String, Object>> fileList = boardService.selectFileList(board.getBoardNumber());
        model.addAttribute("file", fileList);

        return "board/readView";
    }

    @GetMapping(value = "/updateView")
    public String updateView(Board board, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception
    {
        //게시물 수정 화면
        logger.info("updateView");

        model.addAttribute("update", boardService.read(board.getBoardNumber()));
        model.addAttribute("scri", scri);

        List<Map<String, Object>> fileList = boardService.selectFileList(board.getBoardNumber());
        model.addAttribute("file", fileList);

        return "board/updateView";
    }

    @PostMapping(value = "/update")
    public String update(Board board, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, @RequestParam(value="fileNoDel[]") String[] files, @RequestParam(value="fileNameDel[]") String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception
    {
        //게시물 수정
        logger.info("update");
        //logger.info("board : {}", board);
        boardService.update(board, files, fileNames, mpRequest);

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

    @RequestMapping(value="/fileDown")
    public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
        //logger.info("fileDown : {}", map);
        Map<String, Object> resultMap = boardService.selectFileInfo(map);
        String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
        String originalFileName = (String) resultMap.get("ORG_FILE_NAME");

        byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\mp\\file\\"+storedFileName));

        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\""+ URLEncoder.encode(originalFileName, "UTF-8")+"\";");
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}

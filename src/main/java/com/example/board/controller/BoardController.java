package com.example.board.controller;

import com.example.board.model.Board;
import com.example.board.model.Criteria;
import com.example.board.model.PageMaker;
import com.example.board.model.SearchCriteria;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //@Controller + @ResponseBody = @RestController
//AJAX를 사용할 때 @ResponseBody가 필요함
@RequiredArgsConstructor //final로 생성된 변수에 대해 생성자 주입
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    //로그를 info 레벨로 출력

    private final BoardService boardService;

    //model.addAttribute() : Controller에서 생성된 데이터를 Model 객체를 통해 View로 전달
    @GetMapping(value = "/board/writeView")
    public String writeView() throws Exception
    {
        logger.info("writeView");

        return "board/writeView";
    }

    @PostMapping(value = "/board/write")
    public String write(Board board) throws Exception
    {
        logger.info("write");
        boardService.write(board);

        return "redirect:/";
    }

    @GetMapping(value = "/board/list")
    public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception
    {
        logger.info("list");

        model.addAttribute("list", boardService.listPage(scri));

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(scri);
        pageMaker.setTotalCount(boardService.listCount(scri));

        model.addAttribute("pageMaker", pageMaker);

        return "board/list"; //View를 반환 ex) list라는 html을 반환
    }

    @GetMapping(value = "/board/readView")
    public String read(Board board, Model model) throws Exception
    {
        logger.info("read");

        model.addAttribute("read", boardService.read(board.getBoardNumber()));

        return "board/readView";
    }

    @GetMapping(value = "/board/updateView")
    public String updateView(Board board, Model model) throws Exception
    {
        logger.info("updateView");

        model.addAttribute("update", boardService.read(board.getBoardNumber()));

        return "board/updateView";
    }

    @PostMapping(value = "/board/update")
    public String update(Board board) throws Exception
    {
        logger.info("update");

        boardService.update(board);

        return "redirect:/board/list";
    }

    @PostMapping(value = "/board/delete")
    public String delete(Board board) throws Exception
    {
        logger.info("delete");

        boardService.delete(board.getBoardNumber());

        return "redirect:/board/list";
    }
}

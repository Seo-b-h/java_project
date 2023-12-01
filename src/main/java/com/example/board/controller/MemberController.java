package com.example.board.controller;

import com.example.board.service.MemberService;
import com.example.board.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller //@Controller + @ResponseBody = @RestController
//AJAX를 사용할 때 @ResponseBody가 필요함
@RequiredArgsConstructor //final로 생성된 변수에 대해 생성자 주입
@RequestMapping("/member/*")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void getRegister(Member member) throws Exception {
        logger.info("get register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(Member member) throws Exception {
        logger.info("post register");

        memberService.register(member);
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Member member, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
        logger.info("post login");

        HttpSession session = req.getSession();
        Member login = memberService.login(member);

        if(login == null) {
            session.setAttribute("member", null);
            rttr.addFlashAttribute("msg", false);
        }
        else {
            session.setAttribute("member", login);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {
        session.invalidate();

        return "redirect:/";
    }
}

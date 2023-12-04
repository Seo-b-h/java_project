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
import org.springframework.web.bind.annotation.ResponseBody;
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
        int result = memberService.idChk(member);
        try{
            if(result == 1) {
                return "/member/register";
            } else if(result == 0) {
                memberService.register(member);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return "redirect:/";
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
            session.setMaxInactiveInterval(36000);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/memberUpdateView", method = RequestMethod.GET)
    public String registerUpdateView() throws Exception {
        return "member/memberUpdateView";
    }

    @RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
    public String registerUpdate(Member member, HttpSession session) throws Exception {
        memberService.memberUpdate(member);
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
    public String memberDeleteView() throws Exception {
        return "member/memberDeleteView";
    }

    @RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
    public String memberDelete(Member member, HttpSession session, RedirectAttributes rttr) throws Exception {
        Member mem = (Member) session.getAttribute("member");
        // 세션에 있는 비밀번호
        String sessionPassword = mem.getPassword();
        // member로 들어오는 비밀번호
        String memberPassword = member.getPassword();

        if(!sessionPassword.equals(memberPassword)) {
            rttr.addFlashAttribute("msg", false);
            return"redirect:/member/memberDeleteView";
        }
        memberService.memberDelete(member);
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/userChk", method = RequestMethod.POST)
    @ResponseBody
    public int userChk(Member member) throws Exception {
        //logger.info("member info: {}", member);
        return memberService.userChk(member);
    }

    @RequestMapping(value = "/idChk", method = RequestMethod.POST)
    @ResponseBody
    public int idChk(Member member) throws Exception {
        return memberService.idChk(member);
    }
}

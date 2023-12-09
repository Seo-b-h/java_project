/** MemberController.java */
/*
 * Programmed by 서보혁
 * 회원 관리에 관한 요청을 처리하고 결과를 반환하는 소스 코드이다.
 * Date : 2023.12.01.
 * Last Update : 2023.12.01.
 * Major update content : Source code 최초 작성 by 서보혁
 * * Last Update : 2023.12.04.
 * Major update content : 회원이 글 로그인 시 세션 시간 초기화 기능 추가 by 서보혁
 */
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

@Controller // 핸들러가 스캔할 수 있는 Bean 객체로 지정한다.
@RequiredArgsConstructor //final로 생성된 변수에 대해 생성자 주입
@RequestMapping("/member/*")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void getRegister(Member member) throws Exception {
        logger.info("get register");
    } // "/register"라는 값으로 사용자가 요청하면 로그를 띄우는 함수이다.

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(Member member) throws Exception {
        logger.info("post register");
        int result = memberService.idChk(member); // DB에 동일한 ID가 있는지 확인
        try{
            if(result == 1) { // DB에 동일한 ID가 있다면 회원 가입 페이지로 리다이렉트한다.
                return "/member/register";
            } else if(result == 0) { // DB에 동일한 ID가 없다면 DB에 회원 가입 정보를 저장한다.
                memberService.register(member);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return "redirect:/"; // 로그인 페이지로 리다이렉트한다.
    } // "/register"라는 값으로 post 방식으로 사용자가 요청했을 때 처리 결과를 저장하고 지정된 페이지로 리다이렉트하는 함수이다.

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Member member, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
        logger.info("post login");

        HttpSession session = req.getSession();
        Member login = memberService.login(member);

        if(login == null) { // DB의 로그인 정보와 일치하는게 없을 때
            session.setAttribute("member", null);
            rttr.addFlashAttribute("msg", false);
        }
        else { // DB의 로그인 정보와 일치하는게 있을 때
            session.setAttribute("member", login);
            session.setMaxInactiveInterval(36000); // 세션 유지 시간을 10시간으로 설정
        }
        return "redirect:/";
    } // "/login"이라는 값으로 사용자가 요청하면 세션을 만들고 개인 정보 페이지로 리다이렉트하는 함수이다.

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/board/list";
    } // "/logout"이라는 값으로 사용자가 요청하면 세션을 삭제하고 글 목록으로 리다이렉트하는 함수이다.

    @RequestMapping(value = "/memberUpdateView", method = RequestMethod.GET)
    public String registerUpdateView() throws Exception {
        return "member/memberUpdateView";
    } // 회원 정보 수정 페이지를 반환하는 함수이다.

    @RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
    public String registerUpdate(Member member, HttpSession session) throws Exception {
        memberService.memberUpdate(member);
        session.invalidate(); // 세션을 무효화시킨다.
        return "redirect:/";
    } // 회원 정보 수정을 하고 로그인 페이지로 리다이렉트하는 함수이다.

    @RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
    public String memberDeleteView() throws Exception {
        return "member/memberDeleteView";
    } // 회원 정보 삭제 페이지를 반환하는 함수이다.

    @RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
    public String memberDelete(Member member, HttpSession session, RedirectAttributes rttr) throws Exception {
        Member mem = (Member) session.getAttribute("member");
        // 세션에 있는 비밀번호
        String sessionPassword = mem.getPassword();
        // member로 들어오는 비밀번호
        String memberPassword = member.getPassword();

        if(!sessionPassword.equals(memberPassword)) { // 세션의 비밀번호와 입력된 비밀번호가 같지 않을  때
            rttr.addFlashAttribute("msg", false);
            return"redirect:/member/memberDeleteView"; // 회원 정보 삭제 페이지로 리다이렉트한다.
        }
        memberService.memberDelete(member); // 세션의 비밀번호와 입력된 비밀번호가 일치하면 DB에서 해당 회원 정보를 삭제한다.
        session.invalidate(); // 세션을 무효화시킨다.
        return "redirect:/";
    } // 회원 정보 삭제에 관한 처리를 하는 함수이다.

    @RequestMapping(value = "/userChk", method = RequestMethod.POST)
    @ResponseBody
    public int userChk(Member member) throws Exception {
        //logger.info("member info: {}", member);
        return memberService.userChk(member);
    } // DB의 회원 정보가 일치하는 것이 있는지 확인하는 함수이다.

    @RequestMapping(value = "/idChk", method = RequestMethod.POST)
    @ResponseBody
    public int idChk(Member member) throws Exception {
        return memberService.idChk(member);
    } // DB의 ID와 일치하는 정보가 있는지 확인하는 함수이다.
}

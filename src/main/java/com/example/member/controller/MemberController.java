package com.example.member.controller;

import com.example.member.service.MemberService;
import com.example.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //@Controller + @ResponseBody = @RestController
//AJAX를 사용할 때 @ResponseBody가 필요함
@RequiredArgsConstructor //final로 생성된 변수에 대해 생성자 주입
@RequestMapping("/member/*")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void getRegister(Member member) throws Exception {
        logger.info("get register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(Member member) throws Exception {
        logger.info("post register");

        service.register(member);
        return null;
    }
}

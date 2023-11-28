package com.example.board.controller;

import com.example.board.model.Test;
import com.example.board.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService)
    {
        this.testService = testService;
    }

    @GetMapping(value = "/test")
    public Test test() throws Exception
    {
        return testService.test();
    }
}

package com.example.board.service;

import com.example.board.mapper.TestMapper;
import com.example.board.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestMapper testMapper;

    @Autowired
    public TestService(TestMapper testMapper)
    {
        this.testMapper = testMapper;
    }

    public Test test() throws Exception
    {
        return testMapper.test();
    }
}

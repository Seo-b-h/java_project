package com.example.board.service;

import com.example.board.controller.BoardController;
import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.CommentMapper;
import com.example.board.model.Comment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentMapper commentMapper;

    @Override
    public List<Comment> readComment(int FKboardNumber) throws Exception {
        return commentMapper.readComment(FKboardNumber);
    }
}

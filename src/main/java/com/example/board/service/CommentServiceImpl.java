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
    public List<Comment> readComment(int boardNumber) throws Exception {
        return commentMapper.readComment(boardNumber);
    }

    @Override
    public void writeComment(Comment co) throws Exception {
        commentMapper.writeComment(co);
    }

    @Override
    public void updateComment(Comment co) throws Exception {
        commentMapper.updateComment(co);
    }

    @Override
    public void deleteComment(Comment co) throws Exception {
        commentMapper.deleteComment(co);
    }

    @Override
    public Comment selectComment(int commentNumber) throws Exception {
        return commentMapper.selectComment(commentNumber);
    }
}

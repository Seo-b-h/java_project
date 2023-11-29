package com.example.board.service;

import com.example.board.model.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> readComment(int boardNumber) throws Exception;

    public void writeComment(Comment co) throws Exception;
}

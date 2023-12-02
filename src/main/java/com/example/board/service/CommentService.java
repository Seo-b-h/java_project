package com.example.board.service;

import com.example.board.model.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> readComment(int boardNumber) throws Exception;

    public void writeComment(Comment co) throws Exception;

    public void updateComment(Comment co) throws Exception;

    public void deleteComment(Comment co) throws Exception;

    public Comment selectComment(int commentNumber) throws Exception;
}

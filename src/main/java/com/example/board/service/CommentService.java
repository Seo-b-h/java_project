/** CommentService.java */
/*
 * Programmed by 최민규
 * 댓글 작성에 관한 서비스의 역할에 대해 정의한 클래스이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 */
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

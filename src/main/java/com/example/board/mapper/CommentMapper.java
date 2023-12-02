package com.example.board.mapper;

import com.example.board.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //댓글 조회
    public List<Comment> readComment(int boardNumber) throws Exception;

    //댓글 작성
    public void writeComment(Comment co) throws Exception;

    //댓글 수정
    public void updateComment(Comment co) throws Exception;

    //댓글 삭제
    public void deleteComment(Comment co) throws Exception;

    //선택된 댓글 조회
    public Comment selectComment(int commentNumber) throws Exception;
}

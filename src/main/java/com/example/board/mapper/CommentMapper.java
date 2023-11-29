package com.example.board.mapper;

import com.example.board.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //댓글 조회
    public List<Comment> readComment(int FKboardNumber) throws Exception;

    //댓글 작성
    public void writeComment(Comment co) throws Exception;
}

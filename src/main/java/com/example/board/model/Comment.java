package com.example.board.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment {

    private int BoardNumber; //게시물 번호
    private int commentNumber; //댓글 번호
    private String content; //댓글 내용
    private String writer; //댓글 작성자
    private String register; //댓글 등록일

    public Comment(int BoardNumber, String content, String writer, String register)
    {
        this.BoardNumber = BoardNumber;
        this.content = content;
        this.writer = writer;
        this.register = register;
    }

    public Comment(int BoardNumber, int commentNumber, String content, String writer, String register)
    {
        this.BoardNumber = BoardNumber;
        this.commentNumber = commentNumber;
        this.content = content;
        this.writer = writer;
        this.register = register;
    }
}

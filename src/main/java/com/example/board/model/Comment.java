package com.example.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {

    private int BoardNumber;
    private int commentNumber;
    private String content;
    private String writer;
    private String register;

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

package com.example.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {

    private int FKBoardNumber;
    private int commentNumber;
    private String content;
    private String writer;
    private String register;
}

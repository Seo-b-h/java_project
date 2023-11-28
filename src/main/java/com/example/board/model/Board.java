package com.example.board.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor //@NonNULL (NotNULL) 에 대한 생성자를 만들어줌
public class Board {
    private int BoardNumber; //게시물 번호
    private String title; //게시물 제목
    private String content; //게시물 내용
    private String writer; //게시물 작성자
    private String register; //게시물 등록일자

    public Board(int BoardNumber, String title, String content, String writer, String date)
    {
        this.BoardNumber = BoardNumber;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.register = date;
    }

    public Board(String title, String content, String writer, String date)
    {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.register = date;
    }
}

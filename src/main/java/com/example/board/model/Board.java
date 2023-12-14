/** Board.java */
/*
 * Programmed by 최민규
 * Board의 글의 정보들을 저장할 수 있는 클래스에 관한 소스 코드이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 */
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
    private int hit; //게시물 조회수

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

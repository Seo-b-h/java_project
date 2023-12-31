/** Criteria.java */
/*
 * Programmed by 최민규
 * 게시물 페이징 정보들을 저장할 수 있는 클래스에 관한 소스 코드이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 */
package com.example.board.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {
    //게시물 페이징 구현을 위한 객체

    private int page; //현재 페이지
    private int perPageNum; //rowStart ~ rowEnd의 범위 설정
    private int rowStart; //첫번째 페이지 번호
    private int rowEnd; //마지막 페이지 번호

    public Criteria()
    {
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page)
    {
        if(page <= 0)
        {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum)
    {
        if(perPageNum <= 0 || perPageNum > 100)
        {
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getPageStart()
    {
        return (this.page - 1) * perPageNum;
    }

    public int getRowStart()
    {
        rowStart = ((page - 1) * perPageNum) + 1;
        return rowStart;
    }

    public int getRowEnd()
    {
        rowEnd = rowStart + perPageNum - 1;
        return rowEnd;
    }

}

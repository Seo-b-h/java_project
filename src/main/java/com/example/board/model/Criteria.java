package com.example.board.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {
    //게시물 페이징 구현을 위한 객체

    private int page;
    private int perPageNum;
    private int rowStart;
    private int rowEnd;

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
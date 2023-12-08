package com.example.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchCriteria extends Criteria {
    //게시물 검색을 위한 객체
    private String searchType = ""; //검색 타입
    private String keyword = ""; //검색 키워드
}

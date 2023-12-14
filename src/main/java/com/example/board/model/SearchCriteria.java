/** searchCriteria.java */
/*
 * Programmed by 최민규
 * 게시물 검색에 대한 정보를 담는 클래스에 관한 소스 코드이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 */
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

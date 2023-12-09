/** PageMaker.java */
/*
 * Programmed by 최민규
 * 한 페이지를 구성하는 정보를 담는 클래스에 관한 소스 코드이다.
 * Date : 2023.11.28.
 * Last Update : 2023.11.28.
 * Major update content : Source code 최초 작성 by 최민규
 */
package com.example.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter
@Setter
@ToString
public class PageMaker {
    //게시판 한 페이지를 구성하는 객체

    private int totalCount; //게시물 총 개수
    private int startPage; //첫 페이지
    private int endPage; //마지막 페이지
    private boolean prev; //이전번호의 페이지가 있는지
    private boolean next; //다음번호의 페이지가 있는지
    private int displayPageNum = 10; //한 페이지에 보여줄 게시물의 수
    private Criteria cri;

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
    }

    public String makeQuery(int page) {
        //page : 페이지 번호, perPageNum : 페이지에 몇 개의 게시물을 출력할지
        //return으로 url을 만들어서 반환 ex) 매개변수 page = 1을 받은 경우 -> ?page=1&perPageNum=10
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .queryParam("page", page)
                        .queryParam("perPageNum", cri.getPerPageNum())
                        .build();

        return uriComponents.toUriString();
    }

    public String makeSearch(int page)
    {
        // /board/list에서 searchType(카테고리)와 keyword(검색하고자 하는)를 입력하면 url을 만들어주는 메소드
        //return으로 url을 반환 ex) ?page=1&perPageNum=10&searchType=w&keyword=작성자
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .queryParam("page", page)
                        .queryParam("perPageNum", cri.getPerPageNum())
                        .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
                        .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
                        .build();
        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        //keyword를 인코딩

        if(keyword == null || keyword.trim().length() == 0) {
            return "";
        }

        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            return "";
        }
    }
}

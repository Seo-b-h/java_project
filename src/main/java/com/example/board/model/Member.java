/** Member.java */
/*
 * Programmed by 서보혁
 * 회원 정보들을 저장할 수 있는 클래스에 관한 소스 코드이다.
 * Date : 2023.12.01.
 * Last Update : 2023.12.01.
 * Major update content : Source code 최초 작성 by 서보혁
 */
package com.example.board.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member { // 회원 정보를 저장할 수 있는 클래스
    private String ID;
    private String Password;
    private String name;
    private String RegDate; // 회원 가입 날짜
    private String telephone;

    public Member(String ID, String Password, String name, String RegDate, String telephone) {
        this.ID = ID;
        this.Password = Password;
        this.name = name;
        this.RegDate = RegDate;
        this.telephone = telephone;
    }

    public Member(String ID, String Password) {
        this.ID = ID;
        this.Password = Password;
        this.name = null;
        this.RegDate = null;
        this.telephone = null;
    }
}

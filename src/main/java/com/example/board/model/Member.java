package com.example.board.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {
    private String ID;
    private String Password;
    private String name;
    private String RegDate;
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

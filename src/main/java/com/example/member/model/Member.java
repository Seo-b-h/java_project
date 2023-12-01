package com.example.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Member {
    private String ID;
    private String Password;
    private String name;
    private String RegDate;
    private String Telephone;
}

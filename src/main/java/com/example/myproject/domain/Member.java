package com.example.myproject.domain;

import lombok.Data;

import java.time.*;

@Data
public class Member {
    private String id;
    private String password;
    private String name;
    private String position;
    private String gender;
    private String address;
    private String phoneNum;
    private String email;
    private LocalDate birthDate;
    private String authority;
}

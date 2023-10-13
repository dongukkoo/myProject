package com.example.myproject.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Notice {
    private Integer id;
    private String title;
    private String writer;
    private String body;
    private LocalDate inserted;
}

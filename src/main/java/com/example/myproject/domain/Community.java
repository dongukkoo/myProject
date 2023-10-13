package com.example.myproject.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Community {
    private Integer id;
    private String category;
    private String title;
    private String body;
    private String writer;
    private LocalDate inserted;

    private Integer commentCount;
}

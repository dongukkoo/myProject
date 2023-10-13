package com.example.myproject.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommunityComment {
    private Integer id;
    private Integer communityId;
    private String content;
    private String userId;
    private LocalDate inserted;

    private boolean editable;

}

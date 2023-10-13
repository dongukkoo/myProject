package com.example.myproject.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Attendance {
    private Integer id;
    private String userId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String name;
}

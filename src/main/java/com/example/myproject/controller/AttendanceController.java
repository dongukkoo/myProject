package com.example.myproject.controller;

import com.example.myproject.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @PostMapping("/checkInOut")
    public void checkInOut(Authentication authentication) {
        String memberId = authentication.getName();
        service.checkInOut(memberId);
    }




}

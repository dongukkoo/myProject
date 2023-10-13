package com.example.myproject.controller;

import com.example.myproject.domain.Attendance;
import com.example.myproject.domain.Community;
import com.example.myproject.domain.Notice;
import com.example.myproject.service.AttendanceService;
import com.example.myproject.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MemberService service;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public void home(Authentication authentication,
                     HttpSession session,
                     Model model){
        session.setAttribute("member", service.get(authentication.getName()));

        List<Notice> noticeList = service.listNotice();
        model.addAttribute("noticeList", noticeList);

        List<Community> communityList = service.listCommunity();
        model.addAttribute("communityList", communityList);

    }

    @GetMapping("/allUserAttendances")
    @PreAuthorize("hasAuthority('admin')")
    public String allUserAttendances(Model model) {
        List<Attendance> allAttendances = attendanceService.getAllUserAttendances();
        model.addAttribute("attendanceList", allAttendances);
        return "attendance";
    }

}

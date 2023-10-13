package com.example.myproject.service;

import com.example.myproject.domain.Attendance;
import com.example.myproject.domain.Member;
import com.example.myproject.mapper.AttendanceMapper;
import com.example.myproject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper mapper;

    @Autowired
    private MemberMapper memberMapper;

    @Transactional
    public void checkInOut(String memberId) {
        Member member = memberMapper.findById(memberId);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Attendance attendance = mapper.getLatestAttendance(member.getId());

        if (attendance == null || attendance.getCheckOut() != null) {
            // 출근 처리
            mapper.insertCheckIn(member.getId());
        } else {
            // 퇴근 처리
            mapper.updateCheckOut(member.getId());
        }
    }

    public List<Attendance> getAllUserAttendances() {
        return mapper.getAllUserAttendances();
    }
}

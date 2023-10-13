package com.example.myproject.service;

import com.example.myproject.domain.Community;
import com.example.myproject.domain.Member;
import com.example.myproject.domain.Notice;
import com.example.myproject.mapper.CommunityMapper;
import com.example.myproject.mapper.MemberMapper;
import com.example.myproject.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

    @Autowired
    private MemberMapper mapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean add(Member member){
        // 비밀번호 암호화
        String plain = member.getPassword();
        member.setPassword(passwordEncoder.encode(plain));

       int cnt= mapper.insert(member);
       return cnt == 1;
    }

    public List<Member> listMember() {

        return mapper.selectAll();
    }

    public Member get(String id) {
        return mapper.selectById(id);
    }

    public void remove(String id) {
        mapper.deleteById(id);
    }

    public boolean modify(Member member) {

        // 비밀번호를 바꾸기 위해 입력하면
        if(!member.getPassword().isBlank()){

            // 입력된 비밀번호를 암호화
            String plain = member.getPassword();
            member.setPassword(passwordEncoder.encode(plain));
        }
        int cnt = 0;
        cnt = mapper.update(member);
        return cnt == 1;
    }

    public List<Notice> listNotice() {
        List<Notice> list = noticeMapper.selectAll();
        return list;
    }

    public List<Community> listCommunity() {
        List<Community> list = communityMapper.selectAll();
        return list;
    }

    public Map<String, Object> checkId(String id) {
        Member member = mapper.selectById(id);

        return Map.of("available", member == null);
    }

}

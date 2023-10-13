package com.example.myproject.security;

import com.example.myproject.domain.Member;
import com.example.myproject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = mapper.selectById(username);

        if(member == null){
            throw new UsernameNotFoundException(username + "사원정보가 존재하지 않습니다.");
        }

        String[] authorities = member.getAuthority().split(",");

        UserDetails user = User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .authorities(authorities)
                .build();

        return user;
    }
}

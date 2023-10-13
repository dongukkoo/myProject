package com.example.myproject.security;

import com.example.myproject.domain.Community;
import com.example.myproject.domain.CommunityComment;
import com.example.myproject.mapper.CommunityCommentMapper;
import com.example.myproject.mapper.CommunityMapper;
import com.example.myproject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityChecker {
    @Autowired
    private CommunityMapper mapper;

    @Autowired
    private CommunityCommentMapper commentMapper;

    @Autowired
    private MemberMapper memberMapper;

    public boolean checkCommunityWriter(Authentication authentication, Integer id){
        Community community = mapper.selectById(id);

        String userName = authentication.getName();
        String writer = community.getWriter();

        return userName.equals(writer);
    }


    public boolean checkCommunityCommentWriter(Authentication authentication,
                                               Integer commentId){
        CommunityComment communityComment = commentMapper.selectById(commentId);

        return communityComment.getUserId().equals(authentication.getName());
    }

}

package com.example.myproject.service;

import com.example.myproject.domain.CommunityComment;
import com.example.myproject.mapper.CommunityCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommunityCommentService {

    @Autowired
    private CommunityCommentMapper mapper;

    public Map<String, Object> update(CommunityComment comment) {
        int cnt = mapper.update(comment);
        var res = new HashMap<String, Object>();
        if(cnt == 1){
            res.put("message", "댓글이 수정되었습니다.");
        } else {
            res.put("message", "댓글이 수정되지 않았습니다.");
        }
        return res;
    }

    public CommunityComment get(Integer id) {
        return mapper.selectById(id);
    }

    public Map<String, Object> remove(Integer id) {
        int cnt = mapper.deleteById(id);
        var res = new HashMap<String, Object>();
        if(cnt == 1){
            res.put("message", "댓글이 삭제되었습니다.");
        } else {
            res.put("message", "댓글이 삭제되지 않았습니다.");
        }
        return res;

    }

    public Map<String, Object> add(CommunityComment comment, Authentication authentication) {
        comment.setUserId(authentication.getName());
        var res = new HashMap<String, Object>();
        int cnt = mapper.insert(comment);
        if(cnt == 1){
            res.put("message", "댓글이 등록되었습니다.");
        } else {
            res.put("message", "댓글이 등록되지 않았습니다.");
        }
        return res;
    }

    public List<CommunityComment> list(Integer communityId, Authentication authentication) {
        List<CommunityComment> comments = mapper.selectAllByCommunityId(communityId);
        if(authentication != null){
            for(CommunityComment comment : comments){
                comment.setEditable(comment.getUserId().equals(authentication.getName()));
            }
        }
        return comments;
    }
}

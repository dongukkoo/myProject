package com.example.myproject.controller;

import com.example.myproject.domain.CommunityComment;
import com.example.myproject.service.CommunityCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("communityComment")
public class CommunityCommentController {

    @Autowired
    private CommunityCommentService service;

    @PutMapping("update")
    @ResponseBody
    @PreAuthorize("authenticated and @customSecurityChecker.checkCommunityCommentWriter(authentication, #comment.id)")
    public ResponseEntity<Map<String, Object>> update(@RequestBody CommunityComment comment){
        Map<String, Object> res = service.update(comment);

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("id/{id}")
    @ResponseBody
    public CommunityComment get(@PathVariable("id") Integer id){
        return service.get(id);
    }

    @DeleteMapping("id/{id}")
    @PreAuthorize("authenticated and @customSecurityChecker.checkCommunityCommentWriter(authentication, #id)")
    public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Integer id){
        Map<String, Object> res = service.remove(id);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody CommunityComment comment, Authentication authentication){
        Map<String, Object> res = service.add(comment, authentication);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("list")
    @ResponseBody
    public List<CommunityComment> list(@RequestParam("community") Integer communityId, Authentication authentication){
        return service.list(communityId, authentication);
    }
}

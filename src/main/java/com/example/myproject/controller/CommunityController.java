package com.example.myproject.controller;

import com.example.myproject.domain.Community;
import com.example.myproject.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("community")
public class CommunityController {

    @Autowired
    private CommunityService service;

    @GetMapping("list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "search", defaultValue = "") String search,
                       @RequestParam(value = "type", required = false) String type,
                       @RequestParam(value = "category", required = false) String category) {
        Map<String, Object> result = service.selectAll(page, search, type, category);
        model.addAllAttributes(result);

        return "community/list";
    }

    @GetMapping("id/{id}")
    @PreAuthorize("isAuthenticated()")
    public String Community(@PathVariable("id") Integer id, Model model, Authentication authentication){
        Community community = service.getCommunity(id, authentication);
        model.addAttribute("communityList", community);

        return "community/get";
    }

    @GetMapping("/modify/{id}")
    @PreAuthorize("(isAuthenticated() and @customSecurityChecker.checkCommunityWriter(authentication, #id)) or hasAuthority('admin')")
    public String modifyForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("community", service.getCom(id));

        return "community/modify";
    }

    @PostMapping("/modify/{id}")
    @PreAuthorize("(isAuthenticated() and @customSecurityChecker.checkCommunityWriter(authentication, #community.id)) or hasAuthority('admin')")
    public String modifyProcess(Community community,
                                RedirectAttributes rttr){
        boolean ok = service.modify(community);

        if(ok) {

            rttr.addFlashAttribute("message", community.getId() + "번 게시물이 수정되었습니다.");
            return "redirect:/community/id/" + community.getId();
        } else {
            rttr.addFlashAttribute("message", community.getId() + "번 게시물이 수정되지 않았습니다.");
            return "redirect:/community/modify/" + community.getId();
        }
    }

    @PostMapping("remove")
    @PreAuthorize("(isAuthenticated() and @customSecurityChecker.checkCommunityWriter(authentication, #id)) or hasAuthority('admin')")
    public String remove(Integer id, RedirectAttributes rttr) {
        boolean ok = service.remove(id);

        if(ok) {
            rttr.addFlashAttribute("message", id + "번 게시물이 삭제되었습니다.");
            return "redirect:/community/list";
        } else {
            return "redirect:/community/id/" + id;
        }
    }

    @GetMapping("add")
    @PreAuthorize("isAuthenticated()")
    public void addForm(){

    }

    @PostMapping("add")
    @PreAuthorize("isAuthenticated()")
    public String addProcess(Community community,
                             RedirectAttributes rttr,
                             Authentication authentication){
        community.setWriter(authentication.getName());
        boolean ok = service.addCommunity(community);

        if(ok){
            rttr.addFlashAttribute("message", "게시글이 등록되었습니다.");
            return "redirect:/community/list";
        } else {
            rttr.addFlashAttribute("message", "게시글이 등록되지 않았습니다.");
            return "redirect:/community/add";
        }
    }
}

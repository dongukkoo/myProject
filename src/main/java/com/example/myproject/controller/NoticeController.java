package com.example.myproject.controller;

import com.example.myproject.domain.Notice;
import com.example.myproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    NoticeService service;

    @GetMapping("list")
    public String list(Model model,
                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                     @RequestParam(value = "search", defaultValue = "") String search,
                     @RequestParam(value = "type", required = false) String type
                     ){
        Map<String, Object> result = service.selectAll(page, search, type);
        model.addAllAttributes(result);

        return "notice/list";

    }

    @GetMapping("id/{id}")
    @PreAuthorize("isAuthenticated()")
    public String Notice(@PathVariable("id") Integer id, Model model){
        Notice notice = service.getNotice(id);
        model.addAttribute("noticeList", notice);

        return "notice/get";
    }

    @GetMapping("/modify/{id}")
    @PreAuthorize("isAuthenticated()")
    public String modifyForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("notice", service.getNot(id));

        return "notice/modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyProcess(Notice notice, RedirectAttributes rttr){
        boolean ok = service.modify(notice);

        if(ok){
            rttr.addFlashAttribute("message", notice.getId() + "번 공지사항이 수정되었습니다.");
            return "redirect:/notice/id/" + notice.getId();
        } else {
            rttr.addFlashAttribute("message", notice.getId() + "번 공지사항이 수정되지 않았습니다.");
            return "redirect:/notice/modify" + notice.getId();
        }
    }

    @PostMapping("remove")
    public String remove(Integer id, RedirectAttributes rttr){
        boolean ok = service.remove(id);

        if (ok){
            rttr.addFlashAttribute("message", id + "번 공지사항이 삭제되었습니다.");
            return "redirect:/notice/list";
        } else {
            return "redirect:/notice/id/" + id;
        }
    }

    @GetMapping("add")
    @PreAuthorize("isAuthenticated()")
    public void addForm(){

    }

    @PostMapping("add")
    @PreAuthorize("isAuthenticated()")
    public String addProcess(Notice notice,
                             RedirectAttributes rttr,
                             Authentication authentication){
        notice.setWriter(authentication.getName());
        boolean ok = service.addNotice(notice);

        if (ok){
            rttr.addFlashAttribute("message", "공지사항이 등록되었습니다.");
            return "redirect:/notice/list";
        } else {
            rttr.addFlashAttribute("message", "공지사항이 등록되지 않았습니다.");
            return "redirect:/notice/add";
        }
    }
}

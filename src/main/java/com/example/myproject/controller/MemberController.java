package com.example.myproject.controller;

import com.example.myproject.domain.Member;
import com.example.myproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("checkId/{id}")
    @ResponseBody
    public Map<String, Object> checkId(@PathVariable("id") String id){
        return service.checkId(id);
    }

    @GetMapping("login")
    @PreAuthorize("isAnonymous()")
    public void loginForm(){

    }

    @GetMapping("add")
    @PreAuthorize("hasAuthority('admin')")
    public void addForm(){
    }

    @PostMapping("add")
    @PreAuthorize("isAuthenticated()")
    public String addProcess(Member member, RedirectAttributes rttr) {

        try {
            service.add(member);
            rttr.addFlashAttribute("message", "사원등록이 완료되었습니다.");
            return "redirect:/member/list";
            }catch(Exception e) {
                e.printStackTrace();
                rttr.addFlashAttribute("message", "사원등록이 되지 않았습니다.");
                return "redirect:/member/add";
            }
        }

        @GetMapping("list")
        @PreAuthorize("hasAuthority('admin')")
        public void list(Model model){
            List<Member> list = service.listMember();
            model.addAttribute("memberList", list);
        }

        // 경로 : /member/info?id=OOOO
        @GetMapping("info")
        @PreAuthorize("hasAuthority('admin') or (isAuthenticated() and (authentication.name eq #id))")
        public void info(String id, Model model){
           Member member = service.get(id);
           model.addAttribute("member", member);
        }

        @PostMapping("remove")
        @PreAuthorize("isAuthenticated()")
        public String remove(String id, RedirectAttributes rttr, HttpServletRequest request){
            try {
                service.remove(id);
                rttr.addFlashAttribute("message", "사원정보가 삭제되었습니다");
                return "redirect:/member/list";
            } catch (Exception e){
                e.printStackTrace();
                rttr.addFlashAttribute("message", "사원정보가 삭제되지 않았습니다");
                return "redirect:/member/info?id=" + id;
            }
        }

        @GetMapping("modify")
        @PreAuthorize("isAuthenticated() or hasAuthority('admin')")
        public void modifyForm(String id, Model model){
            Member member = service.get(id);
            model.addAttribute("member", member);
        }

        @PostMapping("modify")
        @PreAuthorize("isAuthenticated() or hasAuthority('admin') ")
        public String modifyProcess(Member member, RedirectAttributes rttr, String id){
            boolean ok = service.modify(member);

            if(ok){
                rttr.addFlashAttribute("message", "사원정보가 수정되었습니다.");
                return "redirect:/member/info?id=" + id;
            } else {
                rttr.addFlashAttribute("message", "사원정보가 수정되지 않았습니다.");
                return "redirect:/member/modify";
            }
        }

        @GetMapping("jusoPopup")
        public void jusoPopup(){

        }

        @PostMapping("jusoPopup")
        public void jusoPopup2(){

        }

    }


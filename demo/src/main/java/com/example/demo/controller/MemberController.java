package com.example.demo.controller;

import com.example.demo.repository.Member;
import com.example.demo.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<Member> getMembers() {
        return memberService.getMemberAll();
    }

    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";
    }
}

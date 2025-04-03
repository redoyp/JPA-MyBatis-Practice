package com.example.demo.controller;

import com.example.demo.repository.Member;
import com.example.demo.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ResponseBody // 여기서는 member를 응답으로 보내줌
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member) {
        return memberService.insertMember(member);
    }

    // GET /members/{id} -> member 단건 조회
    @ResponseBody
    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    // DELETE /members/{id} -> 해당 id member 삭제 -> 삭제 성공 메시지 출력
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public String deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "삭제 성공";
    }
}

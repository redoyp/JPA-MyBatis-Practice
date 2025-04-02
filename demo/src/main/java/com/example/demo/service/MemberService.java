package com.example.demo.service;

import com.example.demo.repository.Member;
import com.example.demo.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// @AllArgsConstrucotr : 밑의 MemberRepository memberRepository(생성자 생성)을 여러개 할 수 있음(쉼표로 나열)
public class MemberService {
    private final MemberRepository memberRepository;

//    (RequriedArgsConstructor와 같음)
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // getMemberAll 멤버 정보를 모두 조회
    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }
}

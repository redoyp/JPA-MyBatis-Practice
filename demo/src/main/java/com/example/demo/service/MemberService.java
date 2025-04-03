package com.example.demo.service;

import com.example.demo.repository.Member;
import com.example.demo.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
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

    // member 테이블에 insert 쿼리
    public Member insertMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    // member 단건 조회
    public Member getMemberById(Long id) {
        Optional<Member> optMember = memberRepository.findById(id);
        return optMember.orElse(new Member());  // id에 해당하는 값 없으면 빈 Member 객체 반환
    }

    // member id 삭제
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}

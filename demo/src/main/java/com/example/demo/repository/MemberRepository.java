package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<T, ID> -> table 엔티티, id의 타입
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name); // 기대하는 결과 select --- from memeber where name = ---

    // findByNameAndId(String name, Long id)
    // findByNameContaining(String name) -- select -- from member where name LIKE
}

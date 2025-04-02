package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<T, ID> -> table 엔티티, id의 타입
public interface MemberRepository extends JpaRepository<Member, Long> {

}

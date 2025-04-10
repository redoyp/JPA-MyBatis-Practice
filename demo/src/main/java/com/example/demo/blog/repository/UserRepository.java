package com.example.demo.blog.repository;

import com.example.demo.blog.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);   // email로 사용자 정보를 가져옴
}

package com.example.demo.blog.repository;

import com.example.demo.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // bean 등록
public interface BlogRepository extends JpaRepository<Article, Long> {
}

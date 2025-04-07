package com.example.demo.blog.service;

import com.example.demo.blog.Article;
import com.example.demo.blog.dto.AddArticleRequest;
import com.example.demo.blog.repository.BlogRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service // 빈 등록
public class BlogService {

    private final BlogRepository blogRepository; // 빈 주입

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 전체 목록 조회
    public List<Article> findArticles() {
         return blogRepository.findAll();
    }

    // 단건 조회
    public Article findArticleById(Long id) {
        Optional<Article> optArticle = blogRepository.findById(id);
        return optArticle.orElseGet(Article::new);
    }

}

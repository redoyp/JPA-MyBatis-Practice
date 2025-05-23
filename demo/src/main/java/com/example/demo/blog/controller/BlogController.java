package com.example.demo.blog.controller;

import com.example.demo.blog.domain.Article;
import com.example.demo.blog.dto.AddArticleRequest;
import com.example.demo.blog.dto.ArticleResponse;
import com.example.demo.blog.dto.UpdateArticleRequest;
import com.example.demo.blog.service.BlogService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // POST /api/articles
    // { title: "", content: ""}
    // ResponseEntity
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.saveArticle(request);

        // Article -> ArticleResponse 변환 후 리
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON) // default josn 이므로 빠져도 되긴 함
                .body(savedArticle.toDto());
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<Article> articles = blogService.findArticles();

        // List<Article> -> List<ArticleResponse> 변환
        List<ArticleResponse> responseBody = articles.stream().map(article ->
                new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
                .toList();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable Long id) {
        Article article = blogService.findArticleById(id);

        ArticleResponse responseBody = new ArticleResponse(article.getId(), article.getTitle(), article.getContent());

        return ResponseEntity.ok(article.toDto());
    }

    // DELETE /api/articles/{id}
    // = @RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.deleteArticle(id);

        return ResponseEntity.ok().build();
    }

    // DELETE /api/articles
    @DeleteMapping("/api/articles")
    public ResponseEntity<Void> deleteAllArticles() {
        blogService.deleteAllArticles();

        return ResponseEntity.ok().build();
    }

    // PUT /api/articles//{id}
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable("id") Long id,
                                                         @RequestBody UpdateArticleRequest request) {
        Article article = blogService.updateArticle(id, request);

        // Article -> ArticleResponse
        ArticleResponse response = article.toDto();

        return ResponseEntity.ok(response);
    }

    // IllegalArgumentException 500 X -> 400 Error
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // BAD_REQUEST : 400 오류
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}

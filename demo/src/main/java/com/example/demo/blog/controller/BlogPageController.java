package com.example.demo.blog.controller;

import com.example.demo.blog.domain.Article;
import com.example.demo.blog.dto.ArticleViewResponse;
import com.example.demo.blog.service.BlogService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogPageController {
    private BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articleList = blogService.findArticles().stream()
                .map(ArticleViewResponse::new) // article -> new ArticleViewResponse(article) 와 같음
                .toList();
        model.addAttribute("articles", articleList); // model에 블로그 글 리스트 저장

        return "articleList"; // html 페이지
    }

    // /articles/{id} -> article.html
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        // 게시글 단건 조회
        Article article = blogService.findArticleById(id);

        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    // /new-article -> newArticle.html (생성/수정 에 따라 다른 세팅)
    // /new-article?id=1    @RequestParam

    // /new-article/{id}    @PathVariable("id")
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findArticleById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }

}


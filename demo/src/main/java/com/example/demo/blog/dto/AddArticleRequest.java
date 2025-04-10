package com.example.demo.blog.dto;

import com.example.demo.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    // 외부에서 들어온 요청 값을 entity로 변환해서 저장
    // AddArticleRequest -> Article (Entity)
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
        // return new Article(title, content) 로 넘겨준 거랑 같음
    }
}

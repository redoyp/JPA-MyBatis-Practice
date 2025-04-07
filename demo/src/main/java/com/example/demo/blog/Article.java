package com.example.demo.blog;

import com.example.demo.blog.dto.ArticleResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // = @NoArgsConstructor
//    public Article() {
//
//    }

    // insert 할 때 id 값은 안쓰고 title, contnet를 사용하여 넣기 위해 필요
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDto() {
        return new ArticleResponse(id, title, content);
    }

    /**
     *  1. 빌더 패턴(@Builder)을 사용하지 않았을 때
     *     new Article("블로그 제목", "내용");
     *
     *  2. 빌더 패턴(@Builder) 사용했을 때
     *     Article.Builder()
     *             .title("블로그 제목")
     *             .content("내용")
     *             .build();
     */

//    롬복 빌더(@Builder) 사용하지 않을 경우
//
//    public Article(Builder builder) {
//        this.title = builder.title;
//        this.content = builder.content;
//
//    }
//
//    private static class Builder {
//        private String title;
//        private String content;
//
//        Builder title(String title) {
//            this.title = title;
//            return this;
//        }
//
//        Builder content(String content) {
//            this.content = content;
//            return this;
//        }
//
//        Article build() {
//            return new Article(this);
//        }
//    }

}

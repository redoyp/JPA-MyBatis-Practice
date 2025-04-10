package com.example.demo.blog.domain;

import com.example.demo.blog.dto.ArticleResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
// 엔티티의 변화를 감지하여 엔티티와 매핑된 테이블의 데이터 조작
// AuditingEntityListener 클래스는 엔티티 수정, 영속 이벤트를 감지
// 스프링 부트 메인 클래스에 @EnableJpaAuditing 어노테이션이 있어야 JPA Auditing 기능이 활성화 됨
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // = @NoArgsConstructor
//    public Article() {
//
//    }

    // title, content = ?        commit;   =>   update
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

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

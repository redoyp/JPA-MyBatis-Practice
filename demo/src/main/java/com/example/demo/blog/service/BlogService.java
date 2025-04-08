package com.example.demo.blog.service;

import com.example.demo.blog.Article;
import com.example.demo.blog.dto.AddArticleRequest;
import com.example.demo.blog.dto.UpdateArticleRequest;
import com.example.demo.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
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

    // 단건 삭제
    public void deleteArticle(Long id) {
        blogRepository.deleteById(id); // delete from article where id = ${id}
    }

    // 전체 삭제
    public void deleteAllArticles() {
        blogRepository.deleteAll();
    }

    // 수정
    @Transactional  // = begin; commit;   트랜직션이 없으면 변경 사항이 반영 안됨
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        // findById (수정하기 이전 Article 객체)
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exists id: " + id));
        // 오류가 나면 서버에 오류가 있다고 예외 처리를 함. id가 잘못 요청이 왔어도... 즉, 이렇게 하면 안됨
        // id 값 없으면 IllegalArgumentException -> 5xx 에러(서버 에러) X -> 4xx 에러가 나도록 해야함
        // Controller에서 설정

        // update
        article.update(request.getTitle(), request.getContent());
        return article;
    }
    /***
     * 트랜잭션이 없으면?
     * JPA가 커밋 타이밍을 못 잡음
     * → 변경 감지(JPA는 변경 감지에 의해 변경된 내용을 DB에 반영함)를 실행하지 않음
     * → UPDATE 쿼리도 날아가지 않음
     * → 결과적으로 DB에는 아무 일도 일어나지 않음
     *
     * 트랜직션 사용안하고 싶으면 save() 사용
     * 여기서는 return blogRepository.save(article);
     */

}

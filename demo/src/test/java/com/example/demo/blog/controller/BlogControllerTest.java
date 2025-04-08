package com.example.demo.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.blog.Article;
import com.example.demo.blog.dto.AddArticleRequest;
import com.example.demo.blog.dto.UpdateArticleRequest;
import com.example.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @Test
    public void saveArticle() throws Exception{
        // given: Object -> json (ObjectMapper 사용해서 직렬화)
        AddArticleRequest request =new AddArticleRequest("제목", "내용");
        String requestBody = objectMapper.writeValueAsString(request);

//        위의 코드와 동일
//        String requestBody = """
//                {
//                    "title": "제목",
//                    "content": "내용"
//                }
//                """;

        // when: POST /api/articles (API 요청)
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then:
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));
    }

    // 전체 목록 조회 API 테스트
    @Test
    public void findAllArticles() throws Exception{
        // given: 테스트용 article 값 저장
        Article savedArticle = Article.builder()
                .title("저장하려는 제목")
                .content("저장하려는 내용")
                .build();
        blogRepository.save(savedArticle);

        // when: GET /api/articles API 호출
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()));
    }

    // 단건 조회 API 테스트
    @Test
    public void findArticleById() throws Exception{
        // given
        Article savedArticle = Article.builder()
                .title("저장하려는 제목")
                .content("저장하려는 내용")
                .build();
        blogRepository.save(savedArticle);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", savedArticle.getId()));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$.content").value(savedArticle.getContent()));


        /*
            Article article = blogRepository.save(new Article("제목123", "내용123"));
            Long id = article.getId();

            ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

            resultActions.andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.title").value(article.getTitle()))
                    .andExpect(jsonPath("$.content").value(article.getContent()));
         */
    }

    // 단건 삭제
    @Test
    public void deleteArticle() throws Exception{
        // given : article 저장, getId
        Article article = blogRepository.save(new Article("제목123", "내용123"));
        Long id = article.getId();

        // when : DELETE API 호출
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        // then : status code 200 ok 검증, article 전체 조회시 빈 리스트 검증
        resultActions.andExpect(status().isOk());

        List<Article> list = blogRepository.findAll();
        Assertions.assertThat(list).isEmpty();
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    // 전체 삭제
    @Test
    public void deleteAllArticles() throws Exception{
        // given
        blogRepository.save(new Article("제목111", "내용111"));
        blogRepository.save(new Article("제목222", "내용222"));
        blogRepository.save(new Article("제목333", "내용333"));

        // when
        ResultActions resultActions = mockMvc.perform(delete("/api/articles"));

        // then
        resultActions.andExpect(status().isOk());

        List<Article> list = blogRepository.findAll();
        Assertions.assertThat(list).isEmpty();
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    // 수정
    @Test
    public void updateArticle() throws Exception{
        // given : 게시글 추가, id 추출, 수정할 값 세팅 (json)
        Article saved = blogRepository.save(new Article("제목1", "내용1"));
        Long id = saved.getId();
        UpdateArticleRequest request = new UpdateArticleRequest("제목 수정", "내용 수정");

        // request(object) -> json : 직렬화    Jackson 라이브러리 활용
        String requestBody = objectMapper.writeValueAsString(request);

        // when : 게시글 수정 API 호출
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        // then : status code 검증, 값 검증 (requestBody값 = given 절 값)
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        // 직접 repository 에서 같은지 검증
        Article article = blogRepository.findById(id).orElseThrow();
        // Assertions로 article.getTitle() == request.getTitle() 검증
        Assertions.assertThat(article.getTitle()).isEqualTo(request.getTitle());
        Assertions.assertThat(article.getContent()).isEqualTo(request.getContent());
    }

    // Exception 발생시 400 Status Code 검증하는 테스트 코드
    @Test // update 실패에 대한 테스트 (지금까지는 성공에 대한 테스트)
    public void updateArticleFailed() throws Exception {
        // given : id =
        Long noExisted = 1000L;
        UpdateArticleRequest request = new UpdateArticleRequest("수정할 title", "수정할 content");
        String requestBody = objectMapper.writeValueAsString(request);

        // when : 게시글 수정 API 호출
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", noExisted)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}
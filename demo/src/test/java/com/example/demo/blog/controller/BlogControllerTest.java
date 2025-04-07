package com.example.demo.blog.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.blog.Article;
import com.example.demo.blog.dto.AddArticleRequest;
import com.example.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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
    void saveArticle() throws Exception{
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
    void findAllArticles() throws Exception{
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
    void findArticleById() throws Exception{
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
}
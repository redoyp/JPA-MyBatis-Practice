package com.example.demo.blog.service;

import com.example.demo.blog.dto.PostContent;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ExternalService {

    public void call() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        //RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PostContent>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});

        log.info("code: {}", responseEntity.getStatusCode());   // code: 200 OK
        List<PostContent> postContent = responseEntity.getBody();
        log.info("postContent: {}", postContent);   // id, id ,id, ... (PostContent 에서 toString() 을 그렇게 출력하도록 override함)
    }
}

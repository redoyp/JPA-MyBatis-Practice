package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafPage(Model model) {
        // Model의 역할 : 서버 쪽에 있는 데이터를 view 한테 보내주는 역할
        Person person = new Person();
        // id, name, hobbies, age
        person.setId(1L);
        person.setName("홍길동");
        person.setAge(20);
        person.setHobbies(Arrays.asList("여행", "조깅"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage";   // html 페이지
    }
}

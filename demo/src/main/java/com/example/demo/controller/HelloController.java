package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 생성자 주입
    private final HelloService helloService;

    // helloService 가 bean 으로 등록되어 있어야 함
    public HelloController(HelloService helloService) { // Dependency Injection
        // this.helloService = new HelloService();  일반적인 흐름, 제어
        this.helloService = helloService;  // 제어의 역전(IoC)
    }

    @GetMapping("/hello")
    public String hello() {
        // service 메소드 호출
        return helloService.sayHello();
    }
}

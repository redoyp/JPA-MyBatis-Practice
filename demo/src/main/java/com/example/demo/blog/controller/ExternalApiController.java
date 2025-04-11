package com.example.demo.blog.controller;

import com.example.demo.blog.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExternalApiController {
    private final ExternalService externalService;

    public ExternalApiController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/api/external")
    public ResponseEntity<String> callExternal() {
        // RestTemplate
        externalService.call();

        return ResponseEntity.ok().build();
    }
}

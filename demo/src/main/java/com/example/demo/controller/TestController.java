package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class TestController {

    // Filter에서는 사용 가능, Interceptor에서는 사용 불가능
//    @GetMapping("/test")
//    public ResponseEntity<Map<String, Object>> testFilter(@RequestParam String query) {
//        log.info("##TestController testFilter()");
//
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("query", query);    // 안에 있는 값을 json 형태로 만들어 줌
//
//        return ResponseEntity.ok(hashMap);
//    }

    @GetMapping("/test")
    public String testFilterInterceptor(@RequestParam String query,
                                        ModelAndView modelAndView) {
        log.info("##TestController testFilter()");

        modelAndView.addObject("query", query);
        return "test";
    }
}

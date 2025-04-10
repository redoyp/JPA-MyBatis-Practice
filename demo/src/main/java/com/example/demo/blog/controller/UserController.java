package com.example.demo.blog.controller;

import com.example.demo.blog.dto.AddUserRequest;
import com.example.demo.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 /signup 사용자 정보 저장, 로그인하면 연결
    @PostMapping("/user")
    public String signUp(@ModelAttribute AddUserRequest request) {
        userService.signUp(request);    // 사용자 정보 저장
        return "redirect:/login";   // login 화면으로 리다이렉션
    }

    @PostMapping("/logoutPage")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        new SecurityContextLogoutHandler().logout(request, response, authentication);

        return "redirect:/login";
    }
}

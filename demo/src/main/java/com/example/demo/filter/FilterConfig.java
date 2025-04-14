package com.example.demo.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 필터 관련 설정
 * - 필터를 빈으로 등록
 * - 필터 체인 순서 지정
 * - url 체인
 */

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> firstFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new FirstFilter());
        filter.setOrder(1);
        filter.addUrlPatterns("/test");
        return filter;
    }

    @Bean
    public FilterRegistrationBean<Filter> secondFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new SecondFilter());
        filter.setOrder(2);
        filter.addUrlPatterns("/test");
        return filter;
    }
}

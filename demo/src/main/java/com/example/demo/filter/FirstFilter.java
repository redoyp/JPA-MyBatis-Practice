package com.example.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * RequestURI 정보 로그로 남기기
 */
@Slf4j
public class FirstFilter implements Filter {
//    private final static Logger log = LoggerFactory.getLogger(FirstFilter.class);
    // log를 남기는 방법
    // Slf4j 혹은 위의 방식으로 선언해주면 됨

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        log.info("FirstFilter - requestURI: {}", requestURI);

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("FirstFilter destroy()");
    }
}

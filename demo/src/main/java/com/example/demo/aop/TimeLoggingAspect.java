package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeLoggingAspect {
    /* Pointcut */
    // 모든 접근자, com.example.demo.blog.service 아래에 있는 모든 클래스, 모든 매개변수
    @Pointcut("execution(* com.example.demo.blog.service..*(..))")
    public void pointcut() {}

    /* Advice */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 시간측정로직 (end - start)
        long startTimeMs = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            long endTimeMs = System.currentTimeMillis();
            log.info("메소드 실행 시간: {} ms", endTimeMs - startTimeMs);
        }

    }
}

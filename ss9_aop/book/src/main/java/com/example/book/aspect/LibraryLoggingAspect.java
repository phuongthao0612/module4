package com.example.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LibraryLoggingAspect {

    @Before("execution(* com.example.book.service.impl.BookService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Logging before method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.book.service.impl.BookService.borrowBook(..))")
    public void logAfterBorrow(JoinPoint joinPoint) {
        System.out.println("Logging after book borrow: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.book.service.impl.BookService.returnBook(..))")
    public void logAfterReturn(JoinPoint joinPoint) {
        System.out.println("Logging after book return: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.book.service.impl.BookService.*(..))")
    public void logAllActions(JoinPoint joinPoint) {
        System.out.println("Logging action: " + joinPoint.getSignature().getName());
    }
}
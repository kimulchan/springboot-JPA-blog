package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice//어디에서든지 exception이 발생하였을때 이 hendler을 이용하게 하는 역할
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)//IllegalArgumentException 이 발생했을 때 실행하는 함수를 나타낸것
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>"+e.getMessage()+"</h1>";

    }
}

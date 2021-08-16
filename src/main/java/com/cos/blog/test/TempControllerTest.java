package com.cos.blog.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    //http://localhost:8000/blog/temp/home
    @GetMapping("temp/home")
    public String tempHome(){
        System.out.println("tempHome()");
        //파일 리턴 기본경로:src/main/resources/static
        //리턴명 : /home.html
        //풀 경로: src/main/resources/static/home/html
        return "/home.html";
    }


}

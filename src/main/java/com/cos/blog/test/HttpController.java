package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(HTML파일)
//@Controller

@RestController//사용자가 요청->응답(data)


public class HttpController {

    private static final String TAG= "HttpController:";

    @GetMapping("http/lombok")
    public String lombokTest(){

        Member m1 = Member.builder().username("sarr").password("1234").email("sarremail").build();
        m1.setUsername("cos");
        System.out.println(TAG+m1.getId());
        m1.setId(5000);
        System.out.println(TAG+m1.getId());
        return "lombok Test 완료";
    }
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청 :"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail() ;
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){//MessageConverter(스프링부트)
        return "post요청 :"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}

package com.cos.blog.test;

import lombok.*;
//
//@Getter
//@Setter

@Data//getter and setter
//@AllArgsConstructor//모든 변수로 생성자 만들기
@NoArgsConstructor//빈 생성자 만들기
//@RequiredArgsConstructor//final이 붙은 constructor 생성
public class Member {

    private  int id;
    private  String username;
    private  String password;
    private  String email;//불변성을 위해 final 작성

    @Builder//자신이 선택한 변수만 입력할 수 있도록 만들음,생성자의 순서를 넣지 않아도 된다는 장점이 있음
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
//ORM -> Java(다를언어) Object -> table로 매핑해주는 기술

@Entity// User 클래스가 MySQL 에 테이블이 생성이 된다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//builder 패턴!
//@DynamicInsert//insert 할때 null 인 필드를 제외한다
public class User {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결 된 DB의 넘버링 전략을 따라간다
    // MySql을 사용하면 auti increment를 사용//나머지는 따로 공부

    private int id;//시퀀스, auto_increment

    @Column(nullable = false,length = 30,unique = true)
    private String username;//아이디

    @Column(nullable = false,length = 100)//123456=>해쉬(비밀 번호 암호화)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

//    @ColumnDefault("'user'")//insert를 직접 해주지는 않음
//    private String role;//Enum을 쓰는것이 좋다 ,Enum을 쓰면 도매인(어떤 범위)을 설정할 수 있음 //Admin,user,manager

    //DB는 RoleType이라는 것이 없음
    @Enumerated(EnumType.STRING)//RoleType라는값이 string이라고 알려줌
    private RoleType role;
    @CreationTimestamp//시간이 자동으로 입력//기본적으로 save 될때 insert해줌
    private Timestamp createDate;

}

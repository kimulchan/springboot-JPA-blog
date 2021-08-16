package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity//
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment;
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob//대용량 데이터
    private String content;//섬머노트 라이브러리 <html>테그가 섞여 디자인 됨

    @ColumnDefault("0")
    private int count;//조회수

    @ManyToOne(fetch = FetchType.EAGER) //board = many, user=one 한명의 유저는 여러개의 개시물을 쓸수 있음
                                        //board 테이블을 select할 때 유저를 무조건 가져온다
    @JoinColumn(name="userId")//userId는 user table의 primary 키인 id를 저장함
    private User user;//DB는 오브젝트를 저장할 수 없음.FK, 자바는 오브젝트를 저장할 수 있다

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)//mappedBy가 적히면 연관관계의 주인이 아니란 뜻 (FK가 아님) DB에 칼럼을 만들지마라는 말임
                                  //mappedBy의 인자로는 feed이름이 들어간다
                                  //기본 default 값이다
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}

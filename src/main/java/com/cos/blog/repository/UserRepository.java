package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//DAO라고 생각해도 됨
//자동으로 Been으로 등록됨
//@Repository생략 가능
public interface UserRepository extends JpaRepository<User,Integer> //userRepository의 jpa repository는 user table이 관리하는 repository고 user의 primarykey는 숫자다 라고 정의
{

}

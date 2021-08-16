package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//스프링이 컨포넌트 스캔을 통해 bean에 등록을 해줌. ioc를 해줌
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int 회원가입(User user){

        try {
            userRepository.save(user);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService : 회원가입():"+e.getMessage());
        }
        return -1;

    }
}

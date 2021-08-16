package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

//html 파일이 아니라 data를 return 해주는 controller = restcontroller
@RestController
public class DummyControllerTest {

    @Autowired//의존성 주입 (di)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            return "삭제에 실패하였습니다, 해당 id는 db에 없습니다";
        }


        return "삭제 되었습니다";
    }

    @Transactional//함수 종료시에 자동 commit
    //더티체킹
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){
        //json 데이터를 요청 -> java object(Message Converter의 Jackson 라이브러리가 변환해서 받아줌)로 변환해 받아줌

        User user = userRepository.findById(id).orElseThrow(()->{//영속화
            return new IllegalArgumentException("수정에 실패하였습니다");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(requestUser);
        //save함수는 id를 전달하지 않으면 insert를 해주고
        //save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
        //save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌
        return user;//종료시 커밋 처음에 있던 오브젝트와 비교해서 변경되었으면 영속성 컨텍스트에서 update문으로 db에 밀어넣어줌
    }

    @GetMapping("dummy/users")
    public List<User> list(){
        return userRepository.findAll();//전체 리턴
    }

    @GetMapping("/dummy/user")
    public List<User>pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        List<User> users = userRepository.findAll(pageable).getContent();
        return users;
    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

        //람다식
        //User user = userRepository,findById(id).orElseThrow(()->{return IllegalAccessError("해당 사용자는 없습니다")})

        //Optional로 null인지 아닌지 판단할 수 있다
        //1.빈 객체를 반환
//        User user= userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });
        //2.오류 반환
        User user= userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다.id:"+id);
            }
        });

        //요청 : 웹 브라우저
        //user 객체 = 자바 object
        //변환을 해야함 (웹 브라우저가 이해할 수 있는 데이터)->(json)
        //스프링 부트는 =MessageConverter라는 애가 응답시에 자동 작동
        //만약 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson라이브러리를 호출해서
        //user오브젝트를 json으로 변환해 브라우저에 던져준다
        return user;
    }



    @PostMapping("/dummy/join")
    //http://localhost:8000/blog/dummy/join
    public String join(User user){//key = value 값을 받아줌//x-www-form-urlencoded
        System.out.println("id:"+user.getId() );
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("email:"+user.getEmail());
        System.out.println("role:"+user.getRole());
        System.out.println("createDate:"+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

}

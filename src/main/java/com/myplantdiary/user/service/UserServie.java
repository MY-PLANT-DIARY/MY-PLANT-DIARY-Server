package com.myplantdiary.user.service;

import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.entity.UserMbti;
import com.myplantdiary.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServie {

    private final UserRepository userRepository;

    @Transactional
    public String join(String uid, String pw, String name, UserMbti userMbti){

        overLayUid(uid);

        User user = new User();
        user.setUid(uid);
        user.setName(name);
        user.setPw(pw);
        user.setMbti(userMbti);

        userRepository.save(user);

        return user.getUid();
    }

    //uid 중복 확인
    public void overLayUid(String uid){
        User user = userRepository.findByUid(uid);
        if(user!=null){
            throw new RuntimeException("overLayuid");
        }
    }

    public void login(String uid, String pw){
        User byUid = userRepository.findByUid(uid);
        if(byUid==null){
            throw new RuntimeException("회원 id가 존재하지 않음");
        }
        User byPw = userRepository.findByPw(pw);
        if(byPw==null||byUid.getUid()!=byPw.getUid()){
            throw new RuntimeException("회원 pw가 틀렸다");
        }
    }
}

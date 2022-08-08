package com.myplantdiary.user.service;

import com.myplantdiary.global.exception.UserException;
import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.entity.UserMbti;
import com.myplantdiary.user.domain.repository.UserRepository;
import com.myplantdiary.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServie {

    private final UserRepository userRepository;

    @Transactional
    public String join(UserDto userDto){

        overlapUid(userDto.getUid());

        User user = new User(userDto.getUid(),userDto.getPw(),userDto.getName(),userDto.getUserMbti());
        userRepository.save(user);

        return user.getUid();
    }

    //uid 중복 확인
    public void overlapUid(String uid){
        User user = userRepository.findByUid(uid);
        if(user!=null){
            throw new UserException("overLayuid");
        }
    }

    public void login(String uid, String pw){
        User byUid = userRepository.findByUid(uid);
        if(byUid==null){
            throw new UserException("회원 id가 존재하지 않음");
        }
        User byPw = userRepository.findByPw(pw);
        if(byPw==null||byUid.getUid()!=byPw.getUid()){
            throw new UserException("회원 pw가 틀렸다");
        }
    }
}

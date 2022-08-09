package com.myplantdiary.user.service;

import com.myplantdiary.global.exception.UserException;
import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.repository.UserRepository;
import com.myplantdiary.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String join(UserDto userDto){
        overlapUid(userDto.getUid());

        User user = new User(userDto);
        userRepository.save(user);

        return user.getUid();
    }

    //uid 중복 확인
    public void overlapUid(String uid){
        Optional.ofNullable(userRepository.findByUid(uid)).ifPresent(user -> {
            throw new UserException("중복된 id 입니다");
        });
    }

    public void login(String uid, String pw){
        checkUid(uid);
        checkPw(uid, pw);
    }

    public void checkUid(String uid){
        Optional.ofNullable(userRepository.findByUid(uid)).orElseThrow(() -> {
            throw new UserException("회원 id가 존재하지 않음");
        });
    }

    public void checkPw(String uid, String pw){
        Optional.ofNullable(userRepository.findByPw(pw)).ifPresentOrElse(user -> {
            if(user.getPw()!=userRepository.findByUid(uid).getPw()){
                throw new UserException("회원 pw가 불일치합니다");
            }
        },()->{ throw new UserException("회원 pw가 불일치합니다");});
    }
}

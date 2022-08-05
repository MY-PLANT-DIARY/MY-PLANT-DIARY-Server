package com.myplantdiary.user.controller;

import com.myplantdiary.global.http.DefaultRes;
import com.myplantdiary.global.http.ResponseMessage;
import com.myplantdiary.global.http.StatusCode;
import com.myplantdiary.user.domain.entity.UserMbti;
import com.myplantdiary.user.service.UserServie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServie userServie;

    @PostMapping("/join")
    public ResponseEntity userJoin(@RequestBody UserDto userRequest){
        String uid = userServie.join(userRequest.getUid(), userRequest.getPw(), userRequest.getName(), userRequest.getUserMbti());
        JoinUserResponse joinUserResponse = new JoinUserResponse(uid);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, joinUserResponse), HttpStatus.OK);
    }

    @GetMapping("/login/{uid}/{pw}")
    public ResponseEntity userLogin(@PathVariable("uid")String uid,@PathVariable("pw")String pw){
        userServie.login(uid,pw);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS), HttpStatus.OK);
    }

    @Getter
    @AllArgsConstructor
    static class UserDto{
        private String uid;
        private String pw;
        private String name;
        private UserMbti userMbti;
    }

    @Getter
    @AllArgsConstructor
    static class JoinUserResponse{
        private String uid;
    }
}

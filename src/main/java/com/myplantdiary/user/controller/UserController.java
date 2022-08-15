package com.myplantdiary.user.controller;

import com.myplantdiary.global.http.DefaultRes;
import com.myplantdiary.global.http.ResponseMessage;
import com.myplantdiary.global.http.StatusCode;
import com.myplantdiary.user.dto.JoinUserResponse;
import com.myplantdiary.user.dto.UserDto;
import com.myplantdiary.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity userJoin(@RequestBody UserDto userRequest){
        String uid = userService.join(userRequest);
        JoinUserResponse joinUserResponse = new JoinUserResponse(uid);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, joinUserResponse), HttpStatus.OK);
    }

    @GetMapping("/login/{uid}/{pw}")
    public ResponseEntity userLogin(@PathVariable("uid")String uid,@PathVariable("pw")String pw){
        userService.login(uid,pw);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS), HttpStatus.OK);
    }
}

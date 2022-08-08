package com.myplantdiary.user.dto;

import com.myplantdiary.user.domain.entity.UserMbti;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String pw;
    private String name;
    private UserMbti userMbti;
}

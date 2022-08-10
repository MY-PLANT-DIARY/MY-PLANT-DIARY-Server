package com.myplantdiary.user.dto;

import com.myplantdiary.user.domain.entity.UserMbti;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String pw;
    private String name;
    private UserMbti userMbti;
}

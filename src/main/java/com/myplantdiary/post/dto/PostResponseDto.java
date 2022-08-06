package com.myplantdiary.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private LocalDate postDate;
    private String imgUrl;
    private String text;
}

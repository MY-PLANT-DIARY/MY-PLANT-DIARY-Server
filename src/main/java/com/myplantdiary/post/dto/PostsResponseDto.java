package com.myplantdiary.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostsResponseDto{
    private Long postId;
    private String imgUrl;
}

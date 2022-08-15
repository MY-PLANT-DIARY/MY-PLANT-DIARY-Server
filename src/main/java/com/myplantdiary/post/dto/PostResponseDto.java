package com.myplantdiary.post.dto;

import com.myplantdiary.post.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private int postCount;
    private LocalDate postDate;
    private String imgUrl;
    private String text;
}

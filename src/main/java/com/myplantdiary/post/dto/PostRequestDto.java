package com.myplantdiary.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PostRequestDto implements Serializable {
    private Long userId;
    private String text;
    private MultipartFile file;
}

package com.myplantdiary.post.controller;

import com.myplantdiary.post.domain.entity.Post;
import com.myplantdiary.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity post(@ModelAttribute PostRequestDto postRequestDto){
        postService.post(postRequestDto.getUid(), postRequestDto.getText(), postRequestDto.getFile());
        return ResponseEntity.ok().body("게시물 등록 성공");
    }

    @GetMapping("/posts")
    public Result posts(){
        List<Post> posts = postService.findPosts();
        List<PostsResponseDto> collect = posts.stream()
                .map(post -> new PostsResponseDto(post.getId(), post.getImgUrl()))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }

    @GetMapping("/post/{postId}")
    public PostResponseDto postReadOne(@PathVariable("postId")Long id){
        Post post = postService.findPost(id);
        return new PostResponseDto(post.getPostDate(), post.getImgUrl(), post.getText());
    }

    @Getter
    @AllArgsConstructor
    static class PostResponseDto{
        private LocalDate postDate;
        private String imgUrl;
        private String text;
    }

    @Getter
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Getter
    @AllArgsConstructor
    static class PostsResponseDto{
        private Long postId;
        private String imgUrl;
    }

    @Getter
    @AllArgsConstructor
    static class PostRequestDto implements Serializable {
        private String uid;
        private String text;
        private MultipartFile file;
    }
}

package com.myplantdiary.post.controller;

import com.myplantdiary.global.http.DefaultRes;
import com.myplantdiary.global.http.ResponseMessage;
import com.myplantdiary.global.http.StatusCode;
import com.myplantdiary.post.domain.entity.Post;
import com.myplantdiary.post.dto.PostRequestDto;
import com.myplantdiary.post.dto.PostResponseDto;
import com.myplantdiary.post.dto.PostsResponseDto;
import com.myplantdiary.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        postService.post(postRequestDto);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_POST), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity posts(){
        List<Post> posts = postService.findPosts();
        List<PostsResponseDto> collect = posts.stream()
                .map(post -> new PostsResponseDto(post.getId(), post.getImgUrl()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_POSTS,collect), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity postReadOne(@PathVariable("postId")Long id) {
        Post post = postService.findPost(id);
        PostResponseDto postResponseDto = new PostResponseDto(post.getPostDate(), post.getImgUrl(), post.getText());
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_POST, postResponseDto), HttpStatus.OK);
    }
}

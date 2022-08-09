package com.myplantdiary.post.service;

import com.myplantdiary.global.exception.PostException;
import com.myplantdiary.post.domain.entity.Post;
import com.myplantdiary.post.domain.repository.PostRepository;
import com.myplantdiary.post.dto.PostRequestDto;
import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void post(PostRequestDto postRequestDto){

        User user = checkUser(postRequestDto.getUserId());

        if( !postRequestDto.getFile().isEmpty() ) {
            log.debug("file org name = {}", postRequestDto.getFile().getOriginalFilename());
            log.debug("file content type = {}", postRequestDto.getFile().getContentType());
            try {
                postRequestDto.getFile().transferTo(new File(postRequestDto.getFile().getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Post post = Post.createPost(user, postRequestDto.getText(), postRequestDto.getFile().getOriginalFilename());
        postRepository.save(post);
    }

    public User checkUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new PostException("회원이 존재하지 않음");
        });
    }

    public List<Post> findPosts(Long userId) {
        checkUser(userId);
        return postRepository.findAllByUserId(userId);
    }

    public Post findPost(Long id) {
        checkPost(id);
        return postRepository.findById(id).get();
    }

    public void checkPost(Long id){
        postRepository.findById(id).orElseThrow(() -> {
            throw new PostException("게시물이 존재하지 않음");
        });
    }
}

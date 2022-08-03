package com.myplantdiary.post.service;

import com.myplantdiary.post.domain.entity.Post;
import com.myplantdiary.post.domain.repository.PostRepository;
import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void post(String uid, String text, MultipartFile file){

        User user = userRepository.findByUid(uid);

        if( !file.isEmpty() ) {
            log.debug("file org name = {}", file.getOriginalFilename());
            log.debug("file content type = {}", file.getContentType());
            try {
                file.transferTo(new File(file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Post post = Post.createPost(user, text, file.getOriginalFilename());
        postRepository.save(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).get();
    }
}

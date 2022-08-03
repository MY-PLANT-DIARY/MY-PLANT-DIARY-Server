package com.myplantdiary.post.domain.repository;

import com.myplantdiary.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

package com.myplantdiary.post.domain.entity;

import com.myplantdiary.grow.domain.entity.Grow;
import com.myplantdiary.user.domain.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grow_id")
    private Grow grow;

    private LocalDate postDate;

    private String imgUrl;

    private String text;

    //연관 관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }

    //글 작성할 때마다 grow의 growcount 1식증가
    public void addPostCount(){

    }

    //생성 메서드
    public static Post createPost(User user, String text, String imgName){
        Post post = new Post();
        post.setUser(user);
        post.setText(text);
        post.setPostDate(LocalDate.now());
        post.setImgUrl(imgName);
        return post;
    }
}
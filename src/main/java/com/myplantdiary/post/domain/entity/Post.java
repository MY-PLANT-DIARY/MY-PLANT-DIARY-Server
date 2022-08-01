package com.myplantdiary.post.domain.entity;

import com.myplantdiary.Grow.domain.entity.Grow;
import com.myplantdiary.user.domain.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grow_id")
    private Grow grow;

    private LocalDateTime postDate;

    private String imgUrl;

    private String text;
}

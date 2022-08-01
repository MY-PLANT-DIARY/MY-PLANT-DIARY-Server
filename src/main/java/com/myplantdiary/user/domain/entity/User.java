package com.myplantdiary.user.domain.entity;

import com.myplantdiary.Grow.domain.entity.Grow;
import com.myplantdiary.post.domain.entity.Post;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id @GeneratedValue
    @Column(name = "user_idx")
    private Long idx;

    @NotEmpty
    private String id;

    @NotEmpty
    private String pw;

    @NotEmpty
    private String name;

    private UserMbti mbti;

    @OneToMany(mappedBy = "user")
    private List<Grow> grows = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}

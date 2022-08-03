package com.myplantdiary.user.domain.entity;

import com.myplantdiary.grow.domain.entity.Grow;
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
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String uid;

    @NotEmpty
    private String pw;

    @NotEmpty
    private String name;

    @Enumerated(value = EnumType.STRING)
    private UserMbti mbti;

    @OneToMany(mappedBy = "user")
    private List<Grow> grows = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}

package com.myplantdiary.user.domain.entity;

import com.myplantdiary.grow.domain.entity.Grow;
import com.myplantdiary.post.domain.entity.Post;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String uid;

    @NotNull
    private String pw;

    @NotNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    private UserMbti mbti;

    @OneToMany(mappedBy = "user")
    private List<Grow> grows = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}

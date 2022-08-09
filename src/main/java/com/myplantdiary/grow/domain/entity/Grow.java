package com.myplantdiary.grow.domain.entity;

import com.myplantdiary.user.domain.entity.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grow {

    @Id @GeneratedValue
    @Column(name = "grow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    private int postCount;

    private GrowStatus growStatus;

    private String name;

    private GrowLevel growLevel;
}

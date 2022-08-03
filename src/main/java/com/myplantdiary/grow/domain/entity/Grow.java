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
    @JoinColumn(name = "user_id")
    private User user;

    private int postCount;

    @Enumerated(value = EnumType.STRING)
    private GrowStatus growStatus;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private GrowLevel growLevel;
}

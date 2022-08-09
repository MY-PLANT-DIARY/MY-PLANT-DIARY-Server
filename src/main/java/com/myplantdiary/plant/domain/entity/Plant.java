package com.myplantdiary.grow.domain.entity;

import com.myplantdiary.user.domain.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Grow {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "grow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int postCount;

    private int dayCount;

    @Enumerated(value = EnumType.STRING)
    private GrowStatus growStatus;

    @NotNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    private GrowLevel growLevel;

    @Enumerated(value = EnumType.STRING)
    private PlantType plantType;

    //연관 관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getGrows().add(this);
    }

    //생성 메서드
    public static Grow createGrow(User user, String name, PlantType plantType){
        Grow grow = new Grow();
        grow.setUser(user);
        grow.setPostCount(0);
        grow.setDayCount(plantType.getCount());
        grow.setGrowStatus(GrowStatus.USING);
        grow.setName(name);
        grow.setGrowLevel(GrowLevel.LEVEL1);
        grow.setPlantType(plantType);
        return grow;
    }
}

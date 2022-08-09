package com.myplantdiary.plant.domain.entity;

import com.myplantdiary.user.domain.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Plant {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "plant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int postCount;

    private int dayCount;

    @Enumerated(value = EnumType.STRING)
    private PlantStatus plantStatus;

    @NotNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    private PlantLevel plantLevel;

    @Enumerated(value = EnumType.STRING)
    private PlantType plantType;

    //연관 관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getPlants().add(this);
    }

    //생성 메서드
    public static Plant createPlant(User user, String name, PlantType plantType){
        Plant plant = new Plant();
        plant.setUser(user);
        plant.setPostCount(0);
        plant.setDayCount(plantType.getCount());
        plant.setPlantStatus(PlantStatus.USING);
        plant.setName(name);
        plant.setPlantLevel(PlantLevel.LEVEL1);
        plant.setPlantType(plantType);
        return plant;
    }
}

package com.myplantdiary.plant.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Plant {

    @Id @GeneratedValue
    @Column(name = "plant_id")
    private Long id;

    private PlantType plantType;

    private int waterCycle;
}

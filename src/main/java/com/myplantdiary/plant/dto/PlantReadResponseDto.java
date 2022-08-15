package com.myplantdiary.plant.dto;

import com.myplantdiary.plant.domain.entity.Plant;
import com.myplantdiary.plant.domain.entity.PlantLevel;
import com.myplantdiary.plant.domain.entity.PlantType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantReadResponseDto {
    private String plantName;
    private PlantType plantType;
    private int postCount;
    private int dayCount;
    private PlantLevel plantLevel;

    public PlantReadResponseDto(Plant plant) {
        this.plantName = plant.getName();
        this.plantType = plant.getPlantType();
        this.postCount = plant.getPostCount();
        this.dayCount = plant.getDayCount();
        this.plantLevel = plant.getPlantLevel();
    }
}

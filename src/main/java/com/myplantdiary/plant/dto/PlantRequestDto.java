package com.myplantdiary.plant.dto;

import com.myplantdiary.plant.domain.entity.PlantType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantRequestDto {
    private Long userId;
    private String plantName;
    private PlantType plantType;
}

package com.myplantdiary.plant.dto;

import com.myplantdiary.plant.domain.entity.PlantType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlantRequestDto {
    private Long userId;
    private String plantName;
    private PlantType plantType;
}

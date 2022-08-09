package com.myplantdiary.plant.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlantType {
    lavender("라벤더",3),
    monstera("몬스테라",4),
    eucalyptus("유칼립투스",5),
    Cactus("선인장",7);

    private final String typeName;
    private final int count;
}

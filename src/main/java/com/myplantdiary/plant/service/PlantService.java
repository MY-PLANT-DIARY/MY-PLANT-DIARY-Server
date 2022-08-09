package com.myplantdiary.plant.service;

import com.myplantdiary.global.exception.PlantException;
import com.myplantdiary.global.exception.UserException;
import com.myplantdiary.plant.domain.entity.Plant;
import com.myplantdiary.plant.domain.repostiory.PlantRepository;
import com.myplantdiary.plant.dto.PlantReadResponseDto;
import com.myplantdiary.plant.dto.PlantRequestDto;
import com.myplantdiary.plant.dto.PlantCreateResponseDto;
import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;

    @Transactional
    public PlantCreateResponseDto savePlant(PlantRequestDto plantRequestDto) {

        User user = checkUser(plantRequestDto.getUserId());

        Plant plant = Plant.createPlant(user, plantRequestDto.getPlantName(), plantRequestDto.getPlantType());
        plantRepository.save(plant);

        return new PlantCreateResponseDto(plant.getName());
    }

    public User checkUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new UserException("회원이 존재하지 않음");
        });
    }

    public PlantReadResponseDto findPlant(Long plantId) {
        Plant plant = checkPlant(plantId);
        return new PlantReadResponseDto(plant);
    }

    public Plant checkPlant(Long plantId){
        return plantRepository.findById(plantId).orElseThrow(() -> {
            throw new PlantException("식물이 존재하지 않음");
        });
    }
}

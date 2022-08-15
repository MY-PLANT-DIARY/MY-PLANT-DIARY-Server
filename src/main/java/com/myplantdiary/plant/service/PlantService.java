package com.myplantdiary.plant.service;

import com.myplantdiary.global.exception.PlantException;
import com.myplantdiary.global.exception.UserException;
import com.myplantdiary.plant.domain.entity.Address;
import com.myplantdiary.plant.domain.entity.Plant;
import com.myplantdiary.plant.domain.entity.PlantStatus;
import com.myplantdiary.plant.domain.repostiory.PlantRepository;
import com.myplantdiary.plant.dto.PlantReadResponseDto;
import com.myplantdiary.plant.dto.PlantRequestDto;
import com.myplantdiary.plant.dto.PlantCreateResponseDto;
import com.myplantdiary.user.domain.entity.User;
import com.myplantdiary.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;

    @Transactional
    public PlantCreateResponseDto savePlant(PlantRequestDto plantRequestDto) {

        User user = checkUser(plantRequestDto.getUserId());
        checkUsingPlant(plantRequestDto.getUserId());

        Plant plant = Plant.createPlant(user, plantRequestDto.getPlantName(), plantRequestDto.getPlantType());
        plantRepository.save(plant);

        return new PlantCreateResponseDto(plant.getName());
    }

    public User checkUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new UserException("회원이 존재하지 않습니다");
        });
    }

    public void checkUsingPlant(Long userId){
        Optional.ofNullable(plantRepository.findByUserId(userId)).ifPresent(plant -> {
            throw new PlantException("사용되고 있는 식물이 존재합니다.");
        });
    }

    public PlantReadResponseDto findPlant(Long plantId) {
        Plant plant = checkPlant(plantId);
        checkPlantStatusUsing(plant.getPlantStatus());
        return new PlantReadResponseDto(plant);
    }

    public Plant checkPlant(Long plantId){
        return plantRepository.findById(plantId).orElseThrow(() -> {
            throw new PlantException("식물이 존재하지 않습니다.");
        });
    }

    public void checkPlantStatusUsing(PlantStatus plantStatus){
        if(plantStatus==PlantStatus.UNUSING){
            throw new PlantException("이 식물은 사용되고 있지 않습니다.");
        }
    }
    @Transactional
    public void setAddress(Long plantId, Address address) {
        Plant plant = checkPlant(plantId);
        checkPlantStatusUnUsing(plant.getPlantStatus());
        plant.setAddress(address);
    }

    public void checkPlantStatusUnUsing(PlantStatus plantStatus){
        if(plantStatus==PlantStatus.USING){
            throw new PlantException("이 식물은 사용되고 있습니다.");
        }
    }

    public PlantReadResponseDto minusDayCount(Long plantId) {
        Plant plant = checkPlant(plantId);
        plant.minusDay();
        return new PlantReadResponseDto(plant);
    }
}

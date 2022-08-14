package com.myplantdiary.plant.controller;

import com.myplantdiary.global.http.DefaultRes;
import com.myplantdiary.global.http.ResponseMessage;
import com.myplantdiary.global.http.StatusCode;
import com.myplantdiary.plant.domain.entity.Address;
import com.myplantdiary.plant.dto.PlantReadResponseDto;
import com.myplantdiary.plant.dto.PlantRequestDto;
import com.myplantdiary.plant.dto.PlantCreateResponseDto;
import com.myplantdiary.plant.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @PostMapping("/plant")
    public ResponseEntity plant(@RequestBody PlantRequestDto plantRequestDto){
        PlantCreateResponseDto plantCreateResponseDto = plantService.savePlant(plantRequestDto);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_PLANT, plantCreateResponseDto), HttpStatus.OK);
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity readPlant(@PathVariable("plantId")Long plantId){
        PlantReadResponseDto plant = plantService.findPlant(plantId);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_PLANT, plant), HttpStatus.OK);
    }

    //postCount 가 30이될 때 요청
    @PostMapping("/plant/address/{plantId}")
    public ResponseEntity address(@PathVariable("plantId")Long plantId, @RequestBody Address address){
        plantService.setAddress(plantId, address);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_ADDRESS), HttpStatus.OK);
    }

    @GetMapping("/plant/minusDayCount/{plantId}")
    private ResponseEntity minusDayCount(@PathVariable("plantId")Long plantId) {
        PlantReadResponseDto plant = plantService.minusDayCount(plantId);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.MINUS_DAY, plant), HttpStatus.OK);
    }
}

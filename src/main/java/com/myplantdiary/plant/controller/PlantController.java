package com.myplantdiary.plant.controller;

import com.myplantdiary.global.http.DefaultRes;
import com.myplantdiary.global.http.ResponseMessage;
import com.myplantdiary.global.http.StatusCode;
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
}
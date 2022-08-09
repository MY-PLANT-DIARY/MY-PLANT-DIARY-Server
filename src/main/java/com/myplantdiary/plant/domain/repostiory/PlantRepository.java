package com.myplantdiary.plant.domain.repostiory;

import com.myplantdiary.plant.domain.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    Plant findByUserId(Long userId);
}

package com.gusto.gustoapi.services;

import com.gusto.gustoapi.dtos.CreateUnitMeasurement;
import com.gusto.gustoapi.models.UnitMeasurement;
import com.gusto.gustoapi.repositories.UnitMeasurementRepository;
import com.gusto.gustoapi.mappers.UnitMeasurementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitMeasurementService {

    @Autowired
    private UnitMeasurementRepository unitMeasurementRepository;

    public UnitMeasurement createUnitMeasurement(CreateUnitMeasurement createUnitMeasurement) {
        UnitMeasurement newUnitMeasurement = UnitMeasurementMapper.mapToUnitMeasurementModel(createUnitMeasurement);

        return unitMeasurementRepository.save(newUnitMeasurement);
    }

    public List<UnitMeasurement> readAllUnitMeasurements() {
        return unitMeasurementRepository.findAll();
    }


}

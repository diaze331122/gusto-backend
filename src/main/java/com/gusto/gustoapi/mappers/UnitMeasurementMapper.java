package com.gusto.gustoapi.mappers;

import com.gusto.gustoapi.dtos.CreateUnitMeasurement;
import com.gusto.gustoapi.models.UnitMeasurement;

import java.time.LocalDateTime;

public class UnitMeasurementMapper {

    public static CreateUnitMeasurement mapToCreateUnitMeasurementDto(UnitMeasurement unitMeasurement) {
        CreateUnitMeasurement createUnitMeasurement = new CreateUnitMeasurement();

        createUnitMeasurement.setUnit(unitMeasurement.getUnit());
        createUnitMeasurement.setAbbr(unitMeasurement.getAbbr());
        createUnitMeasurement.setUnitConversions(unitMeasurement.getUnitConversions());

        return createUnitMeasurement;
    }

    public static UnitMeasurement mapToUnitMeasurementModel(CreateUnitMeasurement createUnitMeasurement) {
        UnitMeasurement unitMeasurement = new UnitMeasurement();

        unitMeasurement.setUnit(createUnitMeasurement.getUnit());
        unitMeasurement.setAbbr(createUnitMeasurement.getAbbr());
        unitMeasurement.setUnitConversions(createUnitMeasurement.getUnitConversions());
        unitMeasurement.setCreatedAt(LocalDateTime.now());

        return unitMeasurement;
    }
}

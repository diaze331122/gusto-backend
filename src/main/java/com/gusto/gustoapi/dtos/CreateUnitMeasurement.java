package com.gusto.gustoapi.dtos;

import com.gusto.gustoapi.models.UnitMeasurement;
import lombok.*;

import java.util.List;

@Data
public class CreateUnitMeasurement {

    private String unit;

    private String abbr;

    private List<UnitMeasurement.Conversion> unitConversions;

    @Data
    public static class Conversion {

        private String unit;

        private float quantity;
    }
}

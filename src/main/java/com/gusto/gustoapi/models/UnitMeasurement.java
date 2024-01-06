package com.gusto.gustoapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "unit_measurements")
public class UnitMeasurement {

    @Id
    private String id;

    private String unit;

    private String abbr;

    private List<Conversion> unitConversions;

    private LocalDateTime createdAt;

    @Data
    public static class Conversion {

        private String unit;

        private float quantity;
    }
}

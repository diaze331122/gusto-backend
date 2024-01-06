package com.gusto.gustoapi.repositories;

import com.gusto.gustoapi.models.UnitMeasurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitMeasurementRepository extends MongoRepository<UnitMeasurement, String> {

    List<UnitMeasurement> findUnitMeasurementsByAbbr(String abbr);

    List<UnitMeasurement> findUnitMeasurementsByUnit(String unit);
}

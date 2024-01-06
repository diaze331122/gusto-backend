package com.gusto.gustoapi.controllers;

import com.gusto.gustoapi.dtos.CreateUnitMeasurement;
import com.gusto.gustoapi.models.UnitMeasurement;
import com.gusto.gustoapi.services.UnitMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/unit_measurements")
public class UnitMeasurementController {

    @Autowired
    private UnitMeasurementService unitMeasurementService;


    @PostMapping("")
    public ResponseEntity<UnitMeasurement> createUnitMeasurement(@RequestBody CreateUnitMeasurement newUnitMeasurement) {
        UnitMeasurement unitMeasurement = unitMeasurementService.createUnitMeasurement(newUnitMeasurement);

        return new ResponseEntity<UnitMeasurement>(unitMeasurement, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<UnitMeasurement>> readUnitMeasurements() {
        List<UnitMeasurement> unitMeasurements = unitMeasurementService.readAllUnitMeasurements();

        return new ResponseEntity<List<UnitMeasurement>>(unitMeasurements, HttpStatus.OK);
    }
}

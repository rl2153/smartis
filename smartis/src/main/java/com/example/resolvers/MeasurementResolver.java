package com.example.resolvers;

import java.security.Timestamp;
import java.time.Instant;
import java.util.List;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.example.repositories.BuildingRepository;
import com.example.repositories.DeviceRepository;
import com.example.repositories.MeasurementRepository;
import com.example.models.Building;
import com.example.models.Device;
import com.example.models.Measurement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@GraphQLApi
@ApplicationScoped
public class MeasurementResolver {

    @Inject
    MeasurementRepository measurementRepo;

    @Inject
    DeviceRepository deviceRepo;

    @Inject
    BuildingRepository buildingRepo;

    @Query("measurement")
    public Measurement getMeasurement(@Name("id") Long id) {
        return measurementRepo.findById(id);
    }

    @Query("measurements")
    public List<Measurement> getAllMeasurements(@Name("page") Integer page, @Name("size") Integer size) {
        if (page != null && size != null) {
            return measurementRepo.findAll()
                                .page(page, size)
                                .list();
        }
        return measurementRepo.listAll();
    }

    @Mutation
    @Transactional
    public Measurement createMeasurement(@Name("deviceId") Long deviceId, @Name("buildingId") Long buildingId, @Name("timestamp") Instant timestamp, @Name("energyKwh") float energyKwh) {
        Measurement measurement = new Measurement();
        Device device = deviceRepo.findById(deviceId);
        if (device == null) {
            throw new RuntimeException("Device not found");
        }
        Building building = buildingRepo.findById(buildingId);
        if (building == null) {
            throw new RuntimeException("Building not found");
        }
        measurement.setDevice(device);
        measurement.setBuilding(building);
        measurement.setTimestamp(timestamp);
        measurement.setEnergyKwh(energyKwh);
        return measurement;
    }

    @Mutation
    @Transactional
    public Measurement updateMeasurement(@Name("id") Long id, @Name("deviceId") long deviceId, @Name("buildingId") Long buildingId, @Name("timestamp") Instant timestamp, @Name("energyKwh") float energyKwh) {
        Measurement measurement = measurementRepo.findById(id);
        if (measurement == null) {
            throw new RuntimeException("measurement not found");
        }
        Device device = deviceRepo.findById(deviceId);
        if (device == null) {
            throw new RuntimeException("Device not found");
        }
        Building building = buildingRepo.findById(buildingId);
        if (building == null) {
            throw new RuntimeException("Building not found");
        }
        measurement.setDevice(device);
        measurement.setBuilding(building);
        measurement.setTimestamp(timestamp);
        measurement.setEnergyKwh(energyKwh);
        return measurement;
    }

    @Mutation
    @Transactional
    public Boolean deleteMeasurement(@Name("id") Long id) {
        return measurementRepo.deleteById(id);
    }
    
}

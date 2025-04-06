package com.example.resolvers;

import java.security.Timestamp;
import java.time.Instant;
import java.util.List;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.example.repositories.MeasurementRepository;
import com.example.models.Building;
import com.example.models.Device;
import com.example.models.Measurement;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@GraphQLApi
@ApplicationScoped
public class MeasurementResolver {

    @Inject
    MeasurementRepository measurementRepo;

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
    public Measurement createMeasurement(@Name("device") Device device, @Name("building") Building building, @Name("timestamp") Instant timestamp, @Name("energyKwh") float energyKwh) {
        Measurement measurement = new Measurement();
        measurement.setDevice(device);
        measurement.setBuilding(building);
        measurement.setTimestamp(timestamp);
        measurement.setEnergyKwh(energyKwh);
        return measurement;
    }

    @Mutation
    public Measurement updateMeasurement(@Name("id") Long id, @Name("device") Device device, @Name("building") Building building, @Name("timestamp") Instant timestamp, @Name("energyKwh") float energyKwh) {
        Measurement measurement = measurementRepo.findById(id);
        if (measurement == null) {
            throw new RuntimeException("measurement not found");
        }
        measurement.setDevice(device);
        measurement.setBuilding(building);
        measurement.setTimestamp(timestamp);
        measurement.setEnergyKwh(energyKwh);
        return measurement;
    }

    @Mutation
    public Boolean deleteMeasurement(@Name("id") Long id) {
        return measurementRepo.deleteById(id);
    }
    
}

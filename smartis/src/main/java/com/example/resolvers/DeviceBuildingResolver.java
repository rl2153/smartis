package com.example.resolvers;

import com.example.models.Building;
import com.example.models.Device;
import com.example.models.DeviceBuilding;
import com.example.repositories.DeviceBuildingRepository;
import com.example.repositories.DeviceRepository;
import com.example.repositories.BuildingRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;
import io.smallrye.graphql.api.Context;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@GraphQLApi
public class DeviceBuildingResolver {

    @Inject
    DeviceBuildingRepository deviceBuildingRepo;

    @Inject
    DeviceRepository deviceRepo;

    @Inject
    BuildingRepository buildingRepo;

    @Query("deviceBuilding")
    public DeviceBuilding getBuilding(@Name("id") Long id) {
        return deviceBuildingRepo.findById(id);
    }

    @Query("deviceBuildings")
    public List<DeviceBuilding> getAllDeviceBuildings(@Name("page") Integer page, @Name("size") Integer size) {
        if (page != null && size != null) {
            return deviceBuildingRepo.findAll()
                               .page(page, size)
                               .list();
        }
        return deviceBuildingRepo.listAll();
    }

    @Mutation
    @Transactional
    public DeviceBuilding createDeviceBuilding(Long deviceId, Long buildingId, Instant installedSince) {
        //System.out.println()
        Device device = deviceRepo.findById(deviceId);
        if (device == null) {
            throw new RuntimeException("Device not found");
        }

        Building building = buildingRepo.findById(buildingId);
        if (building == null) {
            throw new RuntimeException("Building not found");
        }
        
        DeviceBuilding deviceBuilding = new DeviceBuilding();
        deviceBuilding.setDevice(device);
        deviceBuilding.setBuilding(building);
        deviceBuilding.setInstalledSince(installedSince);

        deviceBuildingRepo.persist(deviceBuilding);
        return deviceBuilding;
    }

    @Mutation
    @Transactional
    public Boolean deleteDeviceBuilding(Long id) {
        return deviceBuildingRepo.deleteById(id);
    }
}

package com.example.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import com.example.models.DeviceBuilding;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DeviceBuildingRepository implements PanacheRepository<DeviceBuilding> {
    
}

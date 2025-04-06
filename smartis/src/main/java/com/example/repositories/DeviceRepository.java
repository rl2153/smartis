package com.example.repositories;

import com.example.models.Device; 
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeviceRepository implements PanacheRepository<Device> {
    
}

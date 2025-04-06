package com.example.repositories;

import com.example.models.Building;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BuildingRepository implements PanacheRepository<Building> {
    
}


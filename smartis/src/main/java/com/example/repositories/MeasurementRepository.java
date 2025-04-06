package com.example.repositories;

import com.example.models.Measurement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement>{
    
}

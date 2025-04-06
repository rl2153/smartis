package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "building")  // Določimo ime tabele, če želimo, da ni privzeto
public class Building {

    @Id  // Primarni ključ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 255)  // Unique, Not Null, Length = 255
    private String name;

    @Column(name = "location", nullable = false, length = 500)  // Not Null, Length = 500
    private String location;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "building")
    private Set<DeviceBuilding> deviceBuildings;

    // Getterji in setterji

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Set<DeviceBuilding> getDeviceBuildings() {
        return deviceBuildings;
    }

    public void setDeviceBuildings(Set<DeviceBuilding> deviceBuildings) {
        this.deviceBuildings = deviceBuildings;
    }
}

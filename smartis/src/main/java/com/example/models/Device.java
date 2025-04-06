package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Set;

import com.example.enums.DeviceType;

@Entity
@Table(name = "device")
public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(name = "manufacturer", nullable = false, length = 255)
    private String manufacturer;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "device")
    private Set<DeviceBuilding> deviceBuildings;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceType getType() {
        return this.type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

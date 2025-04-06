package com.example.models;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "device_building") // Join table for Device ↔ Building
public class DeviceBuilding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(name = "installed_since", nullable = false)
    private Instant installedSince;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Instant getInstalledSince() {
        return installedSince;
    }

    public void setInstalledSince(Instant installedSince) {
        this.installedSince = installedSince;
    }
}


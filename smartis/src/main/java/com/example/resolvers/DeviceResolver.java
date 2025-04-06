package com.example.resolvers;

import java.util.List;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.example.repositories.DeviceRepository;
import com.example.enums.DeviceType;
import com.example.models.Device;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@GraphQLApi
@ApplicationScoped
public class DeviceResolver {

    @Inject
    DeviceRepository deviceRepo;

    @Query("device")
    public Device getDevice(@Name("id") Long id) {
        return deviceRepo.findById(id);
    }

    @Query("devices")
    @Transactional
    public List<Device> getAllDevices(@Name("page") Integer page, @Name("size") Integer size) {
        if (page != null && size != null) {
            return deviceRepo.findAll()
                                .page(page, size)
                                .list();
        }
        return deviceRepo.listAll();
    }

    @Mutation
    @Transactional
    public Device createDevice(@Name("manufacturer") String manufacturer, @Name("type") DeviceType type) {
        Device device = new Device();
        device.setManufacturer(manufacturer);
        device.setType(type);
        deviceRepo.persist(device);
        return device;
    }

    @Mutation
    public Device updateDevice(@Name("id") Long id,
                               @Name("manufacturer") String manufacturer,
                               @Name("type") DeviceType type) {
        Device device = deviceRepo.findById(id);
        if (device == null) {
            throw new RuntimeException("Device not found");
        }
        device.setManufacturer(manufacturer);
        device.setType(type);
        return device;
    }

    @Mutation
    public Boolean deleteDevice(@Name("id") Long id) {
        return deviceRepo.deleteById(id);
    }
    
}

package com.example.resolvers;

import com.example.models.Building;
import com.example.repositories.BuildingRepository;
import org.eclipse.microprofile.graphql.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@GraphQLApi
@ApplicationScoped
public class BuildingResolver {

    @Inject
    BuildingRepository buildingRepo;

    @Query("building")
    public Building getBuilding(@Name("id") Long id) {
        return buildingRepo.findById(id);
    }

    @Query("buildings")
    public List<Building> getAllBuildings(@Name("page") Integer page, @Name("size") Integer size) {
        if (page != null && size != null) {
            return buildingRepo.findAll()
                               .page(page, size)
                               .list();
        }
        return buildingRepo.listAll();
    }

    @Mutation
    @Transactional
    public Building createBuilding(@Name("name") String name, @Name("location") String location) {
        Building building = new Building();
        building.setName(name);
        building.setLocation(location);
        buildingRepo.persist(building);
        return building;
    }

    @Mutation
    @Transactional
    public Building updateBuilding(@Name("id") Long id,
                                   @Name("name") String name,
                                   @Name("location") String location) {
        Building building = buildingRepo.findById(id);
        if (building == null) {
            throw new RuntimeException("Building not found");
        }
        building.setName(name);
        building.setLocation(location);
        return building;
    }

    
    @Mutation
    @Transactional
    public Boolean deleteBuilding(@Name("id") Long id) {
        return buildingRepo.deleteById(id);
    }
}

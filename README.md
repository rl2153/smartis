# smartis

## koraki:
1. zapakiramo quarkus aplikacijo v jar:
   
``mvn clean package``

2. zgradimo docker image (v tem primeru je smartis ime slike - ime mora biti enako kot v docker-compose.yml datoteki)
   
``docker build -f ./src/main/docker/Dockerfile.jvm -t smartis .``

3. pozenemo docker-compose.yml datoteko z ukazom:
   
``docker-compose up --build``

4. aplikacije je dostopna na povezavi:
   
http://localhost:8080/q/graphql-ui



## primeri GraphQl poizvedb:

Get a single building by ID (you can change the id)
```
query {
  building(id: 1) {
    id
    name
    location 
  }
}
```

Get paginated list of buildings
```
query {
  buildings (page: 0, size: 3){
    id
    name
    location
  }
}
```

Get list of all buildings
```
query {
  buildings {
    id
    name
    location
  }
}
```

Get a single device by ID
```
query {
  device(id: 1) {
    id
    manufacturer
    type
  }
}
```

Get paginated list of devices
```
query {
  devices(page: 0, size: 3) {
    id
    manufacturer
    type
  }
}
```

Get list of devices
```
query {
  devices {
    id
    manufacturer
    type
  }
}
```

Get a single deviceBuilding by ID
```
query {
  deviceBuilding(id: 1) {
    id
    installedSince
    device {
      id
      manufacturer
    }
    building {
      id
      name
    }
  }
}
```

Get paginated list of deviceBuildings
```
query {
  deviceBuildings (page: 0, size: 4){
    id
    installedSince
    device {
      id
      manufacturer
    }
    building {
      id
      name
    }
  }
}
```

Get list of deviceBuildings
```
query {
  deviceBuildings {
    id
    installedSince
    device {
      id
      manufacturer
    }
    building {
      id
      name
    }
  }
}
```

Get a single measurement by ID
```
query {
  measurement(id: 1) {
    id
    timestamp
    energyKwh
    device {
      id
      manufacturer
    }
    building {
      id
      name
    }
  }
}
```

Get paginated list of measurements
```
query {
  measurements(page: 0, size: 5) {
    id
    timestamp
    energyKwh
    device {
      id
      manufacturer
    }
    building {
      id
      name
    }
  }
}
```

Get list of measurements
```
query {
  measurements {
    id
    timestamp
    energyKwh
    device {
      id
      manufacturer
    }
    building {
      id
      name
    }
  }
}
```

## mutations

Create a building
```
mutation {
  createBuilding(name: "Building F", location: "Downtown") {
    id
    name
    location
  }
}
```

Update a building
```
mutation {
  updateBuilding(id: 1, name: "Updated Building", location: "Midtown") {
    id
    name
    location
  }
}
```

Delete a building
```
mutation {
  deleteBuilding(id: 1)
}
```

Create a device
```
mutation {
  createDevice(manufacturer: "Siemens", type: SOLAR) {
    id
    manufacturer
    type
  }
}
```

Update a device
```
mutation {
  updateDevice(id: 1, manufacturer: "Samsung", type: HVAC) {
    id
    manufacturer
    type
  }
}
```

Delete a device
```
mutation {
  deleteDevice(id: 1)
}
```

Create a deviceBuilding connection
```
mutation {
  createDeviceBuilding(buildingId: 2, installedSince: "2024-01-01T00:00:00Z") {
    id
    installedSince
  }
}
```

Delete a deviceBuilding
```
mutation {
  deleteDeviceBuilding(id: 1)
}
```

Create a measurement
```
mutation {
  createMeasurement(
    device: { id: 1 }
    building: { id: 2 }
    timestamp: "2024-04-08T12:00:00Z"
    energyKwh: 12.5
  ) {
    id
    energyKwh
  }
}
```

Update a measurement
```
mutation {
  updateMeasurement(
    id: 1
    device: { id: 1 }
    building: { id: 2 }
    timestamp: "2024-04-08T14:00:00Z"
    energyKwh: 15.2
  ) {
    id
    energyKwh
  }
}
```

Delete a measurement
```
mutation {
  deleteMeasurement(id: 1)
}
```



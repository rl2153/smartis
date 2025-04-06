-- Tabela za Device
CREATE TABLE device (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    CONSTRAINT check_device_type CHECK (type IN ('HVAC', 'SOLAR', 'METER'))
);

-- Tabela za Building
CREATE TABLE building (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(500) NOT NULL
);

-- Tabela za Measurement
CREATE TABLE measurement (
    id BIGSERIAL PRIMARY KEY,
    device_id BIGINT NOT NULL,
    building_id BIGINT NOT NULL,
    timestamp TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    energy_kwh NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_measurement_device FOREIGN KEY (device_id) REFERENCES device(id),
    CONSTRAINT fk_measurement_building FOREIGN KEY (building_id) REFERENCES building(id)
);

-- Tabela za DeviceBuilding (Join tabela)
CREATE TABLE device_building (
    id BIGSERIAL PRIMARY KEY,
    device_id BIGINT NOT NULL,
    building_id BIGINT NOT NULL,
    installed_since TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_devicebuilding_device FOREIGN KEY (device_id) REFERENCES device(id),
    CONSTRAINT fk_devicebuilding_building FOREIGN KEY (building_id) REFERENCES building(id),
    CONSTRAINT uc_device_building UNIQUE (device_id, building_id)
);

INSERT INTO building (name, location)
VALUES 
    ('Building A', '123 Main St, City A'),
    ('Building B', '456 Oak Rd, City B'),
    ('Building C', '789 Pine Ave, City C');

INSERT INTO device (type, manufacturer) 
VALUES 
    ('HVAC', 'Carrier'),
    ('SOLAR', 'SunPower'),
    ('METER', 'Schneider Electric');

INSERT INTO measurement (device_id, building_id, timestamp, energy_kwh)
VALUES 
    (1, 1, '2025-04-05T14:30:00Z', 15.5),
    (2, 1, '2025-04-05T14:35:00Z', 18.3),
    (3, 2, '2025-04-05T14:40:00Z', 22.1);

INSERT INTO device_building (device_id, building_id, installed_since)
VALUES 
    (1, 1, '2025-01-01T00:00:00Z'),
    (2, 1, '2025-03-01T00:00:00Z'),
    (3, 2, '2025-02-15T00:00:00Z');


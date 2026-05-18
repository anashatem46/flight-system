CREATE TABLE IF NOT EXISTS flights(
    flight_id SERIAL PRIMARY KEY ,
    flight_number VARCHAR(20) NOT NULL UNIQUE ,
    airline VARCHAR(100) NOT NULL ,
    origin VARCHAR(10)  NOT NULL ,
    destination VARCHAR(10) NOT NULL ,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    available_seats INT NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_flights_origin_destination
    ON flights(origin, destination);

CREATE INDEX idx_flights_departure_time
    ON flights(departure_time);
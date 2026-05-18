INSERT INTO flights (
    flight_number,
    airline,
    origin,
    destination,
    departure_time,
    arrival_time,
    price,
    available_seats,
    status
)
VALUES
    ('MS101', 'EgyptAir', 'CAI', 'DXB', '2026-06-01 09:00:00', '2026-06-01 13:00:00', 25000.00, 50, 'SCHEDULED'),
    ('MS102', 'EgyptAir', 'CAI', 'DXB', '2026-06-01 18:00:00', '2026-06-01 22:00:00', 28000.00, 30, 'SCHEDULED'),
    ('EK501', 'Emirates', 'CAI', 'DXB', '2026-06-02 10:30:00', '2026-06-02 14:30:00', 32000.00, 40, 'SCHEDULED');
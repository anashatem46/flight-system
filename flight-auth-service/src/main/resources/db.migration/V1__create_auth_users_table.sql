create TABLE IF NOT EXISTS auth_users
(
    id
    SERIAL
    PRIMARY
    KEY,
    username
    VARCHAR
(
    50
) UNIQUE NOT NULL,
    email VARCHAR
(
    100
) UNIQUE NOT NULL,
    password VARCHAR
(
    100
) UNIQUE NOT NULL,
    role VARCHAR
(
    20
) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    )
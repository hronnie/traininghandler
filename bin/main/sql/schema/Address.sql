DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
    addressId BIGINT(20) NOT NULL,
    addressLine1 VARCHAR(255) NULL,
    addressLine2 VARCHAR(255) NULL,
    city VARCHAR(100) NULL,
    state VARCHAR(100) NULL,
    country VARCHAR(200) NULL,
    postalCode VARCHAR(15) NULL,
    isTrainingPlace BIT(1) NOT NULL DEFAULT b'0',
    isAppointmentPlace BIT(1) NOT NULL DEFAULT b'0',

    PRIMARY KEY (addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


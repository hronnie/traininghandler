DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
    addressId BIGINT(20) NOT NULL,
    postalCode VARCHAR(15) NOT NULL,
    city VARCHAR(50) NULL,
    street VARCHAR(70) NULL,
    houseNo VARCHAR(10) NULL,    
    country VARCHAR(50) NULL,
    oneLineAddress VARCHAR(100) NULL,
    isTrainingPlace BIT(1) NOT NULL DEFAULT b'0',
    isAppointmentPlace BIT(1) NOT NULL DEFAULT b'0',

    PRIMARY KEY (addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


DROP TABLE IF EXISTS User;

CREATE TABLE User (
    userId BIGINT(20) NOT NULL,
    displayName VARCHAR(50) NULL,
    password VARCHAR(255) NULL,
    isMaster BIT(1) NOT NULL DEFAULT b'0',
    isPatient BIT(1) NOT NULL DEFAULT b'0',
    isHealer BIT(1) NOT NULL DEFAULT b'0',
    isTrainee BIT(1) NOT NULL DEFAULT b'0',
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    userName VARCHAR(30) NULL UNIQUE,
    dob DATETIME NULL,
    email VARCHAR(80) NOT NULL UNIQUE,
    mobileNo VARCHAR(50) NULL,
    isEnabled BIT(1) NOT NULL DEFAULT b'1',
    addressId BIGINT(20) NOT NULL,

    PRIMARY KEY (userId),
    FOREIGN KEY FK_User_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

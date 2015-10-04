DROP TABLE IF EXISTS User;

CREATE TABLE User (
    userId BIGINT(20) NOT NULL,
    displayName VARCHAR(50) NULL,
    password VARCHAR(255) NULL,
    userTypeId BIGINT(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    userName VARCHAR(30) NULL UNIQUE,
    dob DATETIME NULL,
    email VARCHAR(80) NOT NULL,
    mobileNo VARCHAR(50) NULL,
    isEnabled BIT(1) NOT NULL DEFAULT b'1',
    addressId BIGINT(20) NOT NULL,
    locale VARCHAR(5) NOT NULL DEFAULT 'hu_HU',
    created DATETIME NOT NULL,

    PRIMARY KEY (userId),
    FOREIGN KEY FK_User_Address (addressId) REFERENCES Address(addressId),
    FOREIGN KEY FK_User_UserType (userTypeId) REFERENCES UserType(userTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

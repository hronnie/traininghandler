DROP TABLE IF EXISTS TrainingType;

CREATE TABLE TrainingType (
    trainingTypeId BIGINT(20) NOT NULL,
    name VARCHAR(100) NULL,
    levelNo DECIMAL(2,0),
    description VARCHAR(300) NULL,
    addressId BIGINT(20) NOT NULL,

    PRIMARY KEY (trainingTypeId),
    FOREIGN KEY FK_User_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS TrainingRef;

CREATE TABLE TrainingRef (
    trainingRefId BIGINT(20) NOT NULL,
    name VARCHAR(100) NULL,
    levelNo DECIMAL(2,0),
    description VARCHAR(300) NULL,
    addressId BIGINT(20) NOT NULL,

    PRIMARY KEY (trainingRefId),
    FOREIGN KEY FK_User_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


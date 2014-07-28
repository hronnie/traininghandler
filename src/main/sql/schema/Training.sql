DROP TABLE IF EXISTS Training;

CREATE TABLE Training (
    trainingId BIGINT(20) NOT NULL,
    nameOfMaster VARCHAR(100) NOT NULL,
    dateTime DATETIME NOT NULL,
    description VARCHAR(200) NULL,
    addressId BIGINT(20) NOT NULL,
    autoInvite BIT(1) NOT NULL DEFAULT b'0',
    trainingTypeId BIGINT(20) NOT NULL,

    PRIMARY KEY (trainingId),
    FOREIGN KEY FK_Training_TrainingType (trainingTypeId) REFERENCES TrainingType(trainingTypeId),
    FOREIGN KEY FK_Training_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

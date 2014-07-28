DROP TABLE IF EXISTS TrainingType;

CREATE TABLE TrainingType (
    trainingTypeId BIGINT(20) NOT NULL,
    name VARCHAR(100) NULL,
    levelNo DECIMAL(2,0),
    description VARCHAR(300) NULL,

    PRIMARY KEY (trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


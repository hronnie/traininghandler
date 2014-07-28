DROP TABLE IF EXISTS TrainingPrerequisite;

CREATE TABLE TrainingPrerequisite (
    trainingPrerequisiteId BIGINT(20) NOT NULL,
    trainingId BIGINT(20) NOT NULL,
    trainingTypeId BIGINT(20) NOT NULL,
    completedDate DATETIME NOT NULL,

    PRIMARY KEY (trainingPrerequisiteId),
    FOREIGN KEY FK_TrainingPrerequisite_Training (trainingId) REFERENCES Training(trainingId),
    FOREIGN KEY FK_TrainingPrerequisite_TrainingType (trainingTypeId) REFERENCES TrainingType(trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

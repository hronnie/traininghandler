DROP TABLE IF EXISTS TrainingPrerequisite;

CREATE TABLE TrainingPrerequisite (
    trainingPrerequisiteId BIGINT(20) NOT NULL,
    trainingId BIGINT(20) NOT NULL,
    trainingRefId BIGINT(20) NOT NULL,
    completedDate DATETIME NOT NULL,

    PRIMARY KEY (trainingPrerequisiteId),
    FOREIGN KEY FK_TrainingPrerequisite_Training (trainingId) REFERENCES Training(trainingId),
    FOREIGN KEY FK_TrainingPrerequisite_TrainingRef (trainingRefId) REFERENCES TrainingRef(trainingRefId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

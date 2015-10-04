DROP TABLE IF EXISTS TrainingPrerequisite;

CREATE TABLE TrainingPrerequisite (
    trainingPrerequisiteId BIGINT(20) NOT NULL,
    dependentTrainingTypeId BIGINT(20) NOT NULL,
    prerequisiteTrainingTypeId BIGINT(20) NOT NULL,
    betweenMonth SMALLINT NOT NULL,

    PRIMARY KEY (trainingPrerequisiteId),
    FOREIGN KEY FK_TrainingPrerequisite_TrainingType_Dep (dependentTrainingTypeId) REFERENCES TrainingType(trainingTypeId),
    FOREIGN KEY FK_TrainingPrerequisite_TrainingType_Pre (prerequisiteTrainingTypeId) REFERENCES TrainingType(trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
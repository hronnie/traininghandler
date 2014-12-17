DROP TABLE IF EXISTS CompletedUserTraining;

CREATE TABLE CompletedUserTraining (
    completedUserTrainingId BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,
    trainingTypeId BIGINT(20) NOT NULL,
    completedDate DATETIME NOT NULL,
    
    PRIMARY KEY (completedUserTrainingId),
    FOREIGN KEY FK_CompletedUserTraining_TrainingType (trainingTypeId) REFERENCES TrainingType(trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

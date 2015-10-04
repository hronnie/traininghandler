DROP TABLE IF EXISTS UserLevel;

CREATE TABLE UserLevel (
    userLevelId BIGINT(20) NOT NULL,
    completedDate DATETIME NULL,
    userId BIGINT(20) NOT NULL,
    trainingTypeId BIGINT(20) NOT NULL,

    PRIMARY KEY (userLevelId),
    FOREIGN KEY FK_UserLevel_User (userId) REFERENCES User(userId),
    FOREIGN KEY FK_UserLevel_TrainingType (trainingTypeId) REFERENCES TrainingType(trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


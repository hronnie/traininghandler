DROP TABLE IF EXISTS UserLevel;

CREATE TABLE UserLevel (
    userLevelId BIGINT(20) NOT NULL,
    completedDate DATETIME NULL,
    userId BIGINT(20) NOT NULL,
    trainingRefId BIGINT(20) NOT NULL,

    PRIMARY KEY (userLevelId),
    FOREIGN KEY FK_UserLevel_User (userId) REFERENCES User(userId),
    FOREIGN KEY FK_UserLevel_TrainingRef (trainingRefId) REFERENCES TrainingRef(trainingRefId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


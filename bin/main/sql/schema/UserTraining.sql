DROP TABLE IF EXISTS UserTraining;

CREATE TABLE UserTraining (
    userId BIGINT(20) NOT NULL,
    trainingId BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,
    tainingId BIGINT(20) NOT NULL,
    
    PRIMARY KEY (userId, trainingId),
    FOREIGN KEY FK_UserTraining_User (userId) REFERENCES User(userId),
    FOREIGN KEY FK_UserTraining_Training (trainingId) REFERENCES Training(tainingId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

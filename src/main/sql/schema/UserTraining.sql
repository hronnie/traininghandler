DROP TABLE IF EXISTS UserTraining;

CREATE TABLE UserTraining (
    userId BIGINT(20) NOT NULL,
    trainingId BIGINT(20) NOT NULL,
    
    PRIMARY KEY (userId, trainingId),
    FOREIGN KEY FK_UserTraining_User (userId) REFERENCES User(userId) ON DELETE CASCADE,
    FOREIGN KEY FK_UserTraining_Training (trainingId) REFERENCES Training(trainingId) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

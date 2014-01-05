DROP TABLE IF EXISTS SentTrainingEmail;

CREATE TABLE SentTrainingEmail (
    sentTrainingEmailId BIGINT(20) NOT NULL,
    traineeEmailToken VARCHAR(30) NOT NULL,
    isConfirmed BIT(1) NOT NULL DEFAULT b'0',
    isAttend BIT(1) NOT NULL DEFAULT b'0',
    userId BIGINT(20) NOT NULL,
    emailTemplateId BIGINT(20) NOT NULL,
    trainingId BIGINT(20) NOT NULL,

    PRIMARY KEY (sentTrainingEmailId),
    FOREIGN KEY FK_SentTrainingEmail_EmailTemplate (emailTemplateId) REFERENCES EmailTemplate(emailTemplateId),
    FOREIGN KEY FK_SentTrainingEmail_Training (trainingId) REFERENCES Training(trainingId),
    FOREIGN KEY FK_SentTrainingEmail_User (userId) REFERENCES User(userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

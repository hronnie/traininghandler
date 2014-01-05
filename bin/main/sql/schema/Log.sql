DROP TABLE IF EXISTS Log;

CREATE TABLE Log (
    logId BIGINT(20) NOT NULL,
    action VARCHAR(50) NOT NULL,
    message VARCHAR(255) NULL,
    dateTime DATETIME NOT NULL,
    userId BIGINT(20) NOT NULL,

    PRIMARY KEY (logId),
    FOREIGN KEY FK_Log_User (who) REFERENCES User(userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
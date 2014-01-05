DROP TABLE IF EXISTS Authorities;

CREATE TABLE Authorities (
    userRoleId BIGINT(20) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    userId BIGINT(20) NOT NULL,

    PRIMARY KEY (userRoleId),
    FOREIGN KEY FK_Authorities_User (userId) REFERENCES User(userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


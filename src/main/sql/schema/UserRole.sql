DROP TABLE IF EXISTS UserRole;

CREATE TABLE UserRole (
    userRoleId BIGINT(20) NOT NULL,
    roleId BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,

    PRIMARY KEY (userRoleId),
    FOREIGN KEY FK_UserRole_User (userId) REFERENCES User(userId),
    FOREIGN KEY FK_UserRole_Role (roleId) REFERENCES Role(roleId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


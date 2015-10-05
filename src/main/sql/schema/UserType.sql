DROP TABLE IF EXISTS UserType;

CREATE TABLE UserType (
    userTypeId BIGINT(20) NOT NULL,
    userTypeName VARCHAR(50) NULL,
    
    PRIMARY KEY (userTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

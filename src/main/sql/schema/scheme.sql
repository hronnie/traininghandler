DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
    addressId BIGINT(20) NOT NULL,
    postalCode VARCHAR(15) NOT NULL,
    city VARCHAR(50) NULL,
    street VARCHAR(70) NULL,
    houseNo VARCHAR(10) NULL,    
    country VARCHAR(50) NULL,
    oneLineAddress VARCHAR(100) NULL,
    isTrainingPlace BIT(1) NOT NULL DEFAULT b'0',
    isAppointmentPlace BIT(1) NOT NULL DEFAULT b'0',

    PRIMARY KEY (addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS UserType;

CREATE TABLE UserType (
    userTypeId BIGINT(20) NOT NULL,
    userTypeName VARCHAR(50) NULL,
    
    PRIMARY KEY (userTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS User;

CREATE TABLE User (
    userId BIGINT(20) NOT NULL,
    displayName VARCHAR(50) NULL,
    password VARCHAR(255) NULL,
    userTypeId BIGINT(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    userName VARCHAR(30) NULL UNIQUE,
    dob DATETIME NULL,
    email VARCHAR(80) NOT NULL,
    mobileNo VARCHAR(50) NULL,
    isEnabled BIT(1) NOT NULL DEFAULT b'1',
    addressId BIGINT(20) NOT NULL,
    locale VARCHAR(5) NOT NULL DEFAULT 'hu_HU',
    created DATETIME NOT NULL,

    PRIMARY KEY (userId),
    FOREIGN KEY FK_User_Address (addressId) REFERENCES Address(addressId),
    FOREIGN KEY FK_User_UserType (userTypeId) REFERENCES UserType(userTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS Appointment;

CREATE TABLE Appointment (
    appointmentId BIGINT(20) NOT NULL,
    fromDateTime DATETIME NOT NULL,
    toDateTime DATETIME NOT NULL,
    description VARCHAR(300) NULL,
    isEmailConfirmation BIT(1) NOT NULL DEFAULT b'0',
    healerId BIGINT(20) NOT NULL,
    patientId BIGINT(20) NOT NULL,
    addressId BIGINT(20) NOT NULL,

    PRIMARY KEY (appointmentId),
    FOREIGN KEY FK_Appointment_User_healer (healerId) REFERENCES User(userId),
    FOREIGN KEY FK_Appointment_User_patient (patientId) REFERENCES User(userId),
    FOREIGN KEY FK_Appointment_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS Role;

CREATE TABLE Role (
    roleId BIGINT(20) NOT NULL,
    roleName VARCHAR(50) NOT NULL,

    PRIMARY KEY (roleId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS UserRole;

CREATE TABLE UserRole (
    userRoleId BIGINT(20) NOT NULL,
    roleId BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,

    PRIMARY KEY (userRoleId),
    FOREIGN KEY FK_UserRole_User (userId) REFERENCES User(userId),
    FOREIGN KEY FK_UserRole_Role (roleId) REFERENCES Role(roleId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS EmailTemplate;

CREATE TABLE EmailTemplate (
    emailTemplateId BIGINT(20) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    body text NOT NULL,

    PRIMARY KEY (emailTemplateId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS GeneralSettings;

CREATE TABLE GeneralSettings (
    generalSettingsId BIGINT(20) NOT NULL,
    name VARCHAR(255) NOT NULL,
    value  VARCHAR(255) NOT NULL,
    PRIMARY KEY (generalSettingsId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS Log;

CREATE TABLE Log (
    logId BIGINT(20) NOT NULL,
    action VARCHAR(50) NOT NULL,
    message VARCHAR(255) NULL,
    dateTime DATETIME NOT NULL,
    who BIGINT(20) NOT NULL,

    PRIMARY KEY (logId),
    FOREIGN KEY FK_Log_User (who) REFERENCES User(userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS TrainingType;

CREATE TABLE TrainingType (
    trainingTypeId BIGINT(20) NOT NULL,
    name VARCHAR(100) NULL,
    levelNo VARCHAR(10),
    description VARCHAR(300) NULL,

    PRIMARY KEY (trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS Training;

CREATE TABLE Training (
    trainingId BIGINT(20) NOT NULL,
    nameOfMaster VARCHAR(100) NOT NULL,
    dateTime DATETIME NOT NULL,
    description VARCHAR(200) NULL,
    addressId BIGINT(20) NOT NULL,
    autoInvite BIT(1) NOT NULL DEFAULT b'0',
    trainingTypeId BIGINT(20) NOT NULL,

    PRIMARY KEY (trainingId),
    FOREIGN KEY FK_Training_TrainingType (trainingTypeId) REFERENCES TrainingType(trainingTypeId),
    FOREIGN KEY FK_Training_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS TrainingPrerequisite;

CREATE TABLE TrainingPrerequisite (
    trainingPrerequisiteId BIGINT(20) NOT NULL,
    dependentTrainingTypeId BIGINT(20) NOT NULL,
    prerequisiteTrainingTypeId BIGINT(20) NOT NULL,
    betweenMonth SMALLINT NOT NULL,

    PRIMARY KEY (trainingPrerequisiteId),
    FOREIGN KEY FK_TrainingPrerequisite_TrainingType_Dep (dependentTrainingTypeId) REFERENCES TrainingType(trainingTypeId),
    FOREIGN KEY FK_TrainingPrerequisite_TrainingType_Pre (prerequisiteTrainingTypeId) REFERENCES TrainingType(trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

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

DROP TABLE IF EXISTS UserTraining;

CREATE TABLE UserTraining (
    userId BIGINT(20) NOT NULL,
    trainingId BIGINT(20) NOT NULL,
    
    PRIMARY KEY (userId, trainingId),
    FOREIGN KEY FK_UserTraining_User (userId) REFERENCES User(userId) ON DELETE CASCADE,
    FOREIGN KEY FK_UserTraining_Training (trainingId) REFERENCES Training(trainingId) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS CompletedUserTraining;

CREATE TABLE CompletedUserTraining (
    completedUserTrainingId BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,
    trainingTypeId BIGINT(20) NOT NULL,
    completedDate DATETIME NOT NULL,
    
    PRIMARY KEY (completedUserTrainingId),
    FOREIGN KEY FK_CompletedUserTraining_TrainingType (trainingTypeId) REFERENCES TrainingType(trainingTypeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

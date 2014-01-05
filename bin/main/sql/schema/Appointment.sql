DROP TABLE IF EXISTS Appointment;

CREATE TABLE Appointment (
    appointmentId BIGINT(20) NOT NULL,
    fromDateTime DATETIME NOT NULL,
    toDateTime DATETIME NOT NULL,
    description VARCHAR(300) NULL,
    isEmailConfirmation BIT(1) NOT NULL DEFAULT b'0',
    userId BIGINT(20) NOT NULL,
    addressId BIGINT(20) NOT NULL,

    PRIMARY KEY (appointmentId),
    FOREIGN KEY FK_Appointment_User_healer (healerId) REFERENCES User(userId),
    FOREIGN KEY FK_Appointment_User_patient (patientId) REFERENCES User(userId),
    FOREIGN KEY FK_Appointment_Address (addressId) REFERENCES Address(addressId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

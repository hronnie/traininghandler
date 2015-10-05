DROP TABLE IF EXISTS GeneralSettings;

CREATE TABLE GeneralSettings (
    generalSettingsId BIGINT(20) NOT NULL,
    name VARCHAR(255) NOT NULL,
    value  VARCHAR(255) NOT NULL,
    PRIMARY KEY (generalSettingsId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
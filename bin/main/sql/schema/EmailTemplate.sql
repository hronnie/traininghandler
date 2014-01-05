DROP TABLE IF EXISTS EmailTemplate;

CREATE TABLE EmailTemplate (
    emailTemplateId BIGINT(20) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    body text NOT NULL,

    PRIMARY KEY (emailTemplateId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


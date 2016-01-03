INSERT INTO Address (addressId,postalCode,city,street,houseNo,country,oneLineAddress,isTrainingPlace,isAppointmentPlace) VALUES (1,'1111','Budapest','','','Magyarország',NULL,0,0);
INSERT INTO Address (addressId,postalCode,city,street,houseNo,country,oneLineAddress,isTrainingPlace,isAppointmentPlace) VALUES (2,'2600','Vác','','','Magyarország',NULL,0,0);


INSERT INTO UserType VALUES (1, 'USER_TYPE_MASTER');
INSERT INTO UserType VALUES (2, 'USER_TYPE_PATIENT');
INSERT INTO UserType VALUES (3, 'USER_TYPE_HEALER');
INSERT INTO UserType VALUES (4, 'USER_TYPE_TRAINEE');
    
INSERT INTO User (userId,displayName,password,userTypeId,name,userName,dob,email,mobileNo,isEnabled,addressId,locale,created) VALUES (1,'Aron','$2a$10$HSM94gT1/BqMFw8putfQueGW6YT5HgHALhMggFWE3PFriamQrxK1S',1,'Harsfalvi Aron','hronnie','1982-09-05 09:37:00','aron.harsfalvi@gmail.com','07743792382',1,1,'hu_HU','2014-01-11 12:00:00');

INSERT INTO Role VALUES (1, 'ROLE_ADMIN');
INSERT INTO Role VALUES (2, 'ROLE_MASTER');
INSERT INTO Role VALUES (3, 'ROLE_HEALER');
INSERT INTO Role VALUES (4, 'ROLE_PATIENT');
INSERT INTO Role VALUES (5, 'ROLE_UNREGISTERED_PATIENT');
INSERT INTO Role VALUES (6, 'ROLE_GUEST');

INSERT INTO UserRole VALUES (1, 1, 1); # ROLE_ADMIN

INSERT INTO TrainingType VALUES (1, '1-es tanfolyam', '1', '');
INSERT INTO TrainingType VALUES (2, '2-es tanfolyam', '2', '');
INSERT INTO TrainingType VALUES (3, '3-as tanfolyam', '3', '');
INSERT INTO TrainingType VALUES (4, '3/C tanfolyam', '3/C', 'Avatómesteri tanfolyam az 1-es és 2-es tanfolyamhoz');
INSERT INTO TrainingType VALUES (5, '4/A csipi tanfolyam', '4/A', 'Csipi tanfolyam');
INSERT INTO TrainingType VALUES (6, '4/B csonti tanfolyam', '4/B', 'Csonti tanfolyam');
INSERT INTO TrainingType VALUES (7, '4/B csonti 2-es tanfolyam', '4/B2', 'Csonti 2-es tanfolyam');
INSERT INTO TrainingType VALUES (8, '5-ös tanfolyam', '5', '');
INSERT INTO TrainingType VALUES (9, '6-os tanfolyam', '6', '');
INSERT INTO TrainingType VALUES (10, '7-es tanfolyam', '7', '');
INSERT INTO TrainingType VALUES (11, '8/E előkészítő tanfolyam', '8/E', 'A 8-as előkészítő tanfolyama');
INSERT INTO TrainingType VALUES (12, '8/2-es tanfolyam', '8/2', '');
INSERT INTO TrainingType VALUES (13, '8/3-as tanfolyam', '8/3', '');
INSERT INTO TrainingType VALUES (14, '8/4-es tanfolyam', '8/4', '');
INSERT INTO TrainingType VALUES (15, 'Utaztató 1-es tanfolyam', 'R1', '');
INSERT INTO TrainingType VALUES (16, 'Utaztató 2-es tanfolyam', 'R2', '');
INSERT INTO TrainingType VALUES (17, 'Utaztató 3-es tanfolyam', 'R3', '');
INSERT INTO TrainingType VALUES (18, 'Utaztató 3+-os tanfolyam', 'R3+', '');
INSERT INTO TrainingType VALUES (19, 'Utaztató 4-es tanfolyam', 'R4', '');
INSERT INTO TrainingType VALUES (20, 'Meditációs 1-es tanfolyam', 'M1', '');
INSERT INTO TrainingType VALUES (21, 'Meditációs 2-es tanfolyam', 'M2', '');
INSERT INTO TrainingType VALUES (22, 'Meditációs 3-as tanfolyam', 'M3', '');
INSERT INTO TrainingType VALUES (23, 'Pudzsa 1-es tanfolyam', 'P1', '');
INSERT INTO TrainingType VALUES (24, 'Masszázs 1-es tanfolyam', 'Ma1', '');
INSERT INTO TrainingType VALUES (25, 'Masszázs 2-es tanfolyam', 'Ma2', '');
INSERT INTO TrainingType VALUES (26, 'Továbbképzés 1 tanfolyam', 'TK1', '');
INSERT INTO TrainingType VALUES (27, 'Továbbképzés 2 tanfolyam', 'TK2', '');
INSERT INTO TrainingType VALUES (28, 'Továbbképzés 3 tanfolyam', 'TK3', '');
INSERT INTO TrainingType VALUES (29, 'Továbbképzés 4 tanfolyam', 'TK4', '');

INSERT INTO TrainingPrerequisite (trainingPrerequisiteId, dependentTrainingTypeId, prerequisiteTrainingTypeId, betweenMonth)
VALUES 
(1, 2, 1, 2),
(2, 3, 2, 4),
(3, 4, 3, 3),
(4, 5, 3, 6),
(5, 6, 5, 6),
(6, 7, 6, 6),
(7, 8, 7, 6),
(8, 9, 8, 12),
(9, 10, 9, 12),
(10, 11, 10, 12),
(11, 12, 11, 12),
(12, 13, 12, 12),
(13, 14, 13, 1),
(14, 15, 14, 6),
(15, 16, 15, 12),
(16, 17, 16, 6),
(17, 17, 7, 1),
(18, 18, 17, 6),
(19, 18, 7, 0),
(20, 19, 2, 0),
(21, 20, 19, 12),
(22, 21, 20, 12),
(23, 22, 3, 0),
(24, 24, 23, 0),
(25, 25, 3, 0),
(26, 26, 7, 0),
(27, 26, 25, 3),
(28, 27, 8, 0),
(29, 27, 26, 0),
(30, 28, 27, 0),
(31, 28, 9, 0);

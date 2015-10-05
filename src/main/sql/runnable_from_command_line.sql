drop database vacikezelo;
create database vacikezelo CHARACTER SET utf8 COLLATE utf8_general_ci;

mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/Address.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/UserType.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/User.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/Appointment.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/Role.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/UserRole.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/EmailTemplate.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/GeneralSettings.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/Log.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/TrainingType.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/Training.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/SentTrainingEmail.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/TrainingPrerequisite.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/UserLevel.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/UserTraining.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/schema/CompletedUserTraining.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/AddressData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/UserTypeData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/UserData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/RoleData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/UserRoleData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/EmailTemplateData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/GeneralSettingsData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/TrainingTypeData.sql
mysql -u training -p vacikezelo < /home/roni/dev/projects/vacikezelo/traininghandler/src/main/sql/data/prod/TrainingPrerequisiteData.sql

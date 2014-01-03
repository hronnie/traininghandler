Address
addressId
addressLine1
addressLine2
city
state
country
postalCode
isTrainingPlace
isAppointmentPlace


User one2one Address with addressId
Training one2one Address with addressId
Appointment one2one Address with addressId
TrainingRef one2one Training with trainingRefId
TrainingRef one2one TrainingPrerequisite with trainingRefId
TrainingRef one2one UserLevel with trainingRefId
Training one2many TrainingPrerequisite with trainingId
Training one2many SentTrainingEmail with trainingId
Training many2many User via UserTraining
EmailTemplate one2many SentTrainingEmail with emailTemplateId
User one2many SentTrainingEmail with userId
User one2many UserLevel with userId
UserLevel one2one TrainingRef with trainingRefId
Appointment one2one User with healerId (userId) and patientId (userId)
User one2many Authorities
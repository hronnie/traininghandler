package com.codeproj.traininghandler.dao;

public class ListAndEditTraineesDao {
/*
SELECT 
 u.userId, 
 u.name, 
 u.mobileNo, 
 u.addressId,
 comp_training_sub_table.complTrNames
FROM 
 User AS u  
INNER JOIN Address a on (u.addressId = a.addressId)
INNER JOIN
(
 SELECT 
  cut.userId AS complUserId, group_concat(tt.name SEPARATOR ', ') AS complTrNames
 FROM 
  CompletedUserTraining cut
 LEFT JOIN TrainingType tt on (tt.trainingTypeId = cut.trainingTypeId)
 GROUP BY cut.userId
 ORDER BY cut.trainingTypeId DESC
) 
AS comp_training_sub_table ON (comp_training_sub_table.complUserId = u.userId)


 */
}

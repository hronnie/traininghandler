package com.codeproj.traininghandler.service.importTraining;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.domain.ImportTrainingInputs;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.excel.TrainingExcelValidator;

@Component
public class ImportTrainingService {
	
	private static final Logger logger = Logger.getLogger(ImportTrainingService.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompletedTrainingService completedTrainingService;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;
	
	public ModelAndView importTrainingsFromAttendentList(List<TrainingExcelDto> trainingAttendendList, 
			ModelAndView mav, ImportTrainingInputs inputParams) {
		logger.debug("importTrainingsFromAttendentList with trainingAttendendList >> " + trainingAttendendList);
		StringBuilder processValidationMessage = initValidationMessage(inputParams);
		StringBuilder successUserList = new StringBuilder("A következő tanítványokat sikeresen hozzáadtam: <br>");
		
		boolean isThereFailedUser = false;
		
		try {
			TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList);
		} catch (ValidationException ve) {
			logger.debug("the list of attendend validation wasn't successful. the validation message was: " + ve.getMessage());
			mav.addObject(Constants.MAV_PARAM_NAME_VALIDATION_MSG, Constants.VALIDATION_EXCEL_IMPORT_INVALID_CONTENT + ve.getMessage()); 
			mav.addObject(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS, new Boolean(false));
			return mav;
		}
		
		DateTime complDt = new DateTime(new Integer(inputParams.getYear()), new Integer(inputParams.getMonth()), new Integer(inputParams.getDay()), 0, 0, 0);
		Date complDate = complDt.toDate();
		
		Long trainingTypeId = new Long(inputParams.getTrainingTypeId());
		
		
		for (TrainingExcelDto item : trainingAttendendList) {
			boolean isNewUserFailed = isUserDoesntExistAndThereIsPrerequisite(mav, trainingTypeId, item);
			if (isNewUserFailed) {
				addFailedNewUserValidationMsg(item, processValidationMessage);
				isThereFailedUser = true;
				continue;
			}
			
			GeneralIdResponse userId = userService.createUserWithAddress(item);
			GeneralIdResponse createOneResult = completedTrainingService.createOne(new CompletedUserTrainingDto(userId.getValue(), trainingTypeId, complDate));
			if (!createOneResult.getSuccess()) {
				addFailedExistingUserValidationMsg(item, processValidationMessage);
				isThereFailedUser = true;
			} else {
				successUserList.append(item.getName());
				successUserList.append("<br>");
			}
		}
		
		
		if (isThereFailedUser) {
			logger.debug("There is at least one failed user. Validation message: " + processValidationMessage.toString());
			processValidationMessage.append(successUserList);
			mav.addObject(Constants.MAV_PARAM_NAME_VALIDATION_MSG, processValidationMessage.toString());
			mav.addObject(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS, new Boolean(false));
		} else {
			logger.debug("Import was succesful");
			mav.addObject(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS, new Boolean(true));
		}
		return mav;

	}
	
	private void addFailedNewUserValidationMsg(TrainingExcelDto item, StringBuilder processValidationMessage) {
		processValidationMessage.append("Tanítvány, aki nem szerepel még az adatbázisban: ");
		addTraineeInfo(item, processValidationMessage);
	}

	private void addFailedExistingUserValidationMsg(TrainingExcelDto item, StringBuilder processValidationMessage) {
		processValidationMessage.append("Tanítvány, aki szerepel már az adatbázisban, de nincs meg az előfeltétele: ");
		addTraineeInfo(item, processValidationMessage);
	}

	private void addTraineeInfo(TrainingExcelDto item,
			StringBuilder processValidationMessage) {
		processValidationMessage.append("Név: ");
		processValidationMessage.append(item.getName());
		processValidationMessage.append(", Email: ");
		String email = item.getEmail() == null ? "nincs" : item.getEmail();
		processValidationMessage.append(email);
		processValidationMessage.append("<br>");
	}

	private StringBuilder initValidationMessage(ImportTrainingInputs inputParams) {
		StringBuilder result = new StringBuilder();
		result.append("A következő tanfolyamnál volt néhány tanítványt, akit nem tudtam hozzáadni: ");
		result.append("<br>");
		result.append(inputParams.getTrainingTypeId());
		result.append(". tanfolyam - ");
		result.append(inputParams.getYear());
		result.append("-");
		result.append(inputParams.getMonth());
		result.append("-");
		result.append(inputParams.getDay());
		result.append("<br>");
		result.append("Tanítványok, akiket nem tudtam hozzáadni:");
		result.append("<br>");
		return result;
	}


	private boolean isUserDoesntExistAndThereIsPrerequisite(ModelAndView mav,
			Long trainingTypeId, TrainingExcelDto item) {
		GeneralIdResponse excelItemUserId = userService.getUserIdByEmailAndName(item.getEmail(), item.getName());
		
		if (!excelItemUserId.getSuccess()) {
			List<TrainingPrerequisite> prereqs = showTraineesEligibleForTrainingManager.getPrerequisitesByTrainingTypeId(trainingTypeId);
			boolean isTrainingTypeHasPrereq = (prereqs == null || prereqs.size() == 0) ? false : true;
			if (isTrainingTypeHasPrereq) {
				return true;
			}
		}
		return false;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCompletedTrainingService(
			CompletedTrainingService completedTrainingService) {
		this.completedTrainingService = completedTrainingService;
	}

	public void setShowTraineesEligibleForTrainingManager(
			ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager) {
		this.showTraineesEligibleForTrainingManager = showTraineesEligibleForTrainingManager;
	}
}

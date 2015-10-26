package com.codeproj.traininghandler.service.importTraining;

import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULD_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.WRONG_VALIDATION_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.domain.ImportTrainingInputs;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ImportTrainingServiceTest {
	
	@Mock
	UserService userService;
	
	@Mock
	CompletedTrainingService completedTrainingService;
	
	@Mock
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;
	
	public ImportTrainingService service;
	
	public ModelAndView VALID_MAV = null;
	public ImportTrainingInputs VALID_INPUT_PARAMS = null; 
	public String VALID_TRAINING_TYPE_ID = "1";
	public String VALID_YEAR = "2015";
	public String VALID_MONTH = "01";
	public String VALID_DAY = "01";
	public TrainingExcelDto VALID_EXCEL_DTO;
	public List<TrainingExcelDto> VALID_TR_ATT_LIST;
	public String VALID_NAME = "test name";
	public String VALID_POST_CODE = "5454";
	public String VALID_ADDRESS = "test address";
	public String VALID_PHONE_NO = "111111111";
	public String VALID_EMAIL = "test@example.com";
	public Long VALID_USER_ID_RESULT = 1L;
	public GeneralIdResponse VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT;
	public GeneralIdResponse VALID_CREATE_USER_WITH_ADDRESS_RESULT;
	public Date VALID_COMPL_DATE;
	

	@Before
	public void setUp() throws Exception {
		service = new ImportTrainingService();
		service.setCompletedTrainingService(completedTrainingService);
		service.setUserService(userService);
		service.setShowTraineesEligibleForTrainingManager(showTraineesEligibleForTrainingManager);
		
		VALID_MAV = new ModelAndView("manageTraining/importTraining");
		VALID_INPUT_PARAMS = new ImportTrainingInputs(VALID_TRAINING_TYPE_ID, VALID_YEAR, VALID_MONTH, VALID_DAY);
		VALID_EXCEL_DTO = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		VALID_TR_ATT_LIST = new ArrayList<>();
		VALID_TR_ATT_LIST.add(VALID_EXCEL_DTO);
		
		VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT = new GeneralIdResponse(2L);
		VALID_CREATE_USER_WITH_ADDRESS_RESULT = new GeneralIdResponse(3L);

		VALID_COMPL_DATE = new DateTime(new Integer(VALID_INPUT_PARAMS.getYear()), new Integer(VALID_INPUT_PARAMS.getMonth()), new Integer(VALID_INPUT_PARAMS.getDay()), 0, 0, 0).toDate();
		
		List<TrainingPrerequisite> prereqList = new ArrayList<>();
		prereqList.add(new TrainingPrerequisite(1L, 1L, 1L, 3));
		when(showTraineesEligibleForTrainingManager.getPrerequisitesByTrainingTypeId(new Long(VALID_TRAINING_TYPE_ID))).thenReturn(prereqList);
	}

	//@Test
	public void testImportTrainingsFromAttendentListWithInvalidInputs() {
		// with null trainingAttendendList
		List<TrainingExcelDto> trainingAttendendList = null;
		ModelAndView result = service.importTrainingsFromAttendentList(trainingAttendendList, VALID_MAV, VALID_INPUT_PARAMS);
		String validationMsg = (String)result.getModel().get(Constants.MAV_PARAM_NAME_VALIDATION_MSG);
		assertEquals("Wrong validation message", Constants.VALIDATION_EXCEL_IMPORT_INVALID_CONTENT + Constants.VALIDATION_EXCEL_IMPORT_EMPTY_TR_ATT_LIST,validationMsg);
		// with empty trainingAttendendList
		trainingAttendendList = new ArrayList<>();
		result = service.importTrainingsFromAttendentList(trainingAttendendList, VALID_MAV, VALID_INPUT_PARAMS);
		validationMsg = (String)result.getModel().get(Constants.MAV_PARAM_NAME_VALIDATION_MSG);
		assertEquals("Wrong validation message", Constants.VALIDATION_EXCEL_IMPORT_INVALID_CONTENT + Constants.VALIDATION_EXCEL_IMPORT_EMPTY_TR_ATT_LIST,validationMsg);
	}

	@Test
	public void testImportTrainingsFromAttendentList() {
		when(userService.getUserIdByEmailAndName(VALID_EMAIL, VALID_NAME)).thenReturn(new GeneralIdResponse(VALID_USER_ID_RESULT));
		when(userService.createUserWithAddress(VALID_EXCEL_DTO)).thenReturn(VALID_CREATE_USER_WITH_ADDRESS_RESULT);
		when(completedTrainingService.createOne(new CompletedUserTrainingDto(VALID_CREATE_USER_WITH_ADDRESS_RESULT.getValue(), new Long(VALID_INPUT_PARAMS.getTrainingTypeId()), VALID_COMPL_DATE))).thenReturn(VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT);
		ModelAndView result = service.importTrainingsFromAttendentList(VALID_TR_ATT_LIST, VALID_MAV, VALID_INPUT_PARAMS);
		assertEquals("There shouldn't be validation message", null , (String)result.getModel().get(Constants.MAV_PARAM_NAME_VALIDATION_MSG));
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));
	}
	
	@Test
	public void testImportTrainingsFromAttendentListWithMissingNameOrEmail() {
		List<TrainingExcelDto> attList = new ArrayList<>();
		TrainingExcelDto excelDto = new TrainingExcelDto("", VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		attList.add(excelDto);
		ModelAndView result = service.importTrainingsFromAttendentList(attList, VALID_MAV, VALID_INPUT_PARAMS);
		assertEquals(WRONG_VALIDATION_MESSAGE, "Az importálni kívánt excel fájl tartalma nem megfelelő. Kérlek töltsd le a minta excel fájlt és ne hagyj ki sorokat, valamint csak a megfelelő oszlopokba írjál!Ezt a paramétert kötelező megadni: Név" , (String)result.getModel().get(Constants.MAV_PARAM_NAME_VALIDATION_MSG));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));

		attList = new ArrayList<>();
		excelDto = new TrainingExcelDto(null, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		attList.add(excelDto);
		result = service.importTrainingsFromAttendentList(attList, VALID_MAV, VALID_INPUT_PARAMS);
		assertEquals(WRONG_VALIDATION_MESSAGE, "Az importálni kívánt excel fájl tartalma nem megfelelő. Kérlek töltsd le a minta excel fájlt és ne hagyj ki sorokat, valamint csak a megfelelő oszlopokba írjál!Ezt a paramétert kötelező megadni: Név" , (String)result.getModel().get(Constants.MAV_PARAM_NAME_VALIDATION_MSG));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));

		excelDto = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, "");
		when(userService.getUserIdByEmailAndName("", VALID_NAME)).thenReturn(new GeneralIdResponse(VALID_USER_ID_RESULT));
		when(userService.createUserWithAddress(excelDto)).thenReturn(VALID_CREATE_USER_WITH_ADDRESS_RESULT);
		when(completedTrainingService.createOne(new CompletedUserTrainingDto(VALID_CREATE_USER_WITH_ADDRESS_RESULT.getValue(), new Long(VALID_INPUT_PARAMS.getTrainingTypeId()), VALID_COMPL_DATE))).thenReturn(VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT);
		attList = new ArrayList<>();
		attList.add(excelDto);
		result = service.importTrainingsFromAttendentList(attList, VALID_MAV, VALID_INPUT_PARAMS);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));
		
		excelDto = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, null);
		when(userService.getUserIdByEmailAndName(null, VALID_NAME)).thenReturn(new GeneralIdResponse(VALID_USER_ID_RESULT));
		when(userService.createUserWithAddress(excelDto)).thenReturn(VALID_CREATE_USER_WITH_ADDRESS_RESULT);
		when(completedTrainingService.createOne(new CompletedUserTrainingDto(VALID_CREATE_USER_WITH_ADDRESS_RESULT.getValue(), new Long(VALID_INPUT_PARAMS.getTrainingTypeId()), VALID_COMPL_DATE))).thenReturn(VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT);
		attList = new ArrayList<>();
		attList.add(excelDto);
		result = service.importTrainingsFromAttendentList(attList, VALID_MAV, VALID_INPUT_PARAMS);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));
		
		excelDto = new TrainingExcelDto(VALID_NAME, null, null, null, null);
		when(userService.getUserIdByEmailAndName(null, VALID_NAME)).thenReturn(new GeneralIdResponse(VALID_USER_ID_RESULT));
		when(userService.createUserWithAddress(excelDto)).thenReturn(VALID_CREATE_USER_WITH_ADDRESS_RESULT);
		when(completedTrainingService.createOne(new CompletedUserTrainingDto(VALID_CREATE_USER_WITH_ADDRESS_RESULT.getValue(), new Long(VALID_INPUT_PARAMS.getTrainingTypeId()), VALID_COMPL_DATE))).thenReturn(VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT);
		attList = new ArrayList<>();
		attList.add(excelDto);
		result = service.importTrainingsFromAttendentList(attList, VALID_MAV, VALID_INPUT_PARAMS);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));
	}
	
	@Test 
	public void testImportTrainingsFromAttendentListWhenAValidAFirstTimeFailedAndAExistingUserFailed() {
		GeneralIdResponse failedIdResponse = new GeneralIdResponse();
		failedIdResponse.setSuccess(false);
		when(userService.getUserIdByEmailAndName("firstTime@failed.com", "first time failed")).thenReturn(failedIdResponse);
		when(userService.getUserIdByEmailAndName("existing@failed.com", "existing failed")).thenReturn(failedIdResponse);
		when(userService.getUserIdByEmailAndName(VALID_EMAIL, VALID_NAME)).thenReturn(new GeneralIdResponse(VALID_USER_ID_RESULT));
		
		TrainingExcelDto existingExcelDto = new TrainingExcelDto("existing failed", VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, "existing@failed.com");		
		when(userService.createUserWithAddress(existingExcelDto)).thenReturn(new GeneralIdResponse(4L));
		when(userService.createUserWithAddress(VALID_EXCEL_DTO)).thenReturn(VALID_CREATE_USER_WITH_ADDRESS_RESULT);
		
		when(completedTrainingService.createOne(new CompletedUserTrainingDto(VALID_CREATE_USER_WITH_ADDRESS_RESULT.getValue(), new Long(VALID_INPUT_PARAMS.getTrainingTypeId()), VALID_COMPL_DATE))).thenReturn(VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT);
		when(completedTrainingService.createOne(new CompletedUserTrainingDto(4L, new Long(VALID_INPUT_PARAMS.getTrainingTypeId()), VALID_COMPL_DATE))).thenReturn(VALID_COMPLETED_TRAINING_CREATE_ONE_RESULT);
		
		List<TrainingExcelDto> attList = new ArrayList<>();
		attList.add(VALID_EXCEL_DTO);
		attList.add(new TrainingExcelDto("first time failed", VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, "firstTime@failed.com"));
		attList.add(new TrainingExcelDto("existing failed", VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, "existing@failed.com"));

		
		ModelAndView result = service.importTrainingsFromAttendentList(attList, VALID_MAV, VALID_INPUT_PARAMS);
		assertEquals(WRONG_VALIDATION_MESSAGE, "A következő tanfolyamnál volt néhány tanítványt, akit nem tudtam hozzáadni: <br>1. tanfolyam - 2015-01-01<br>Tanítványok, akiket nem tudtam hozzáadni:<br>Tanítvány, aki nem szerepel még az adatbázisban: Név: first time failed, Email: firstTime@failed.com<br>Tanítvány, aki nem szerepel még az adatbázisban: Név: existing failed, Email: existing@failed.com<br>A következő tanítványokat sikeresen hozzáadtam: <br>test name<br>" , (String)result.getModel().get(Constants.MAV_PARAM_NAME_VALIDATION_MSG));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, (Boolean)result.getModel().get(Constants.MAV_PARAM_NAME_IMPORT_SUCCESS));
	}

	
}

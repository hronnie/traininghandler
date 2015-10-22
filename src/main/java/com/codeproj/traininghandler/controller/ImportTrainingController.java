package com.codeproj.traininghandler.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.excel.ExcelImportHelper;
import com.codeproj.traininghandler.util.excel.TrainingExcelValidator;


@Controller
@RequestMapping("/manageTraining/importTraining")
public class ImportTrainingController {
	private static final Logger logger = Logger.getLogger(ImportTrainingController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompletedTrainingService completedTrainingService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView("manageTraining/importTraining");
		
		mav.addObject("isNotMainPage", new Boolean(true));
		mav.addObject("isPublicPage", new Boolean(false));
		mav.addObject("backUrl", "/manageTraining");
		logger.debug("Going to importTraining page..");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected  ModelAndView handlePostRequest(@RequestParam("trainingTypeId") String trainingTypeId, 
			@RequestParam("year") String year, 
			@RequestParam("month") String month, 
			@RequestParam("day") String day, 
			@RequestParam("importFile") MultipartFile importFile) throws Exception {
		importFile.isEmpty();
		ModelAndView mav = new ModelAndView("manageTraining/importTraining");
		
		String paramValidMsg = TrainingExcelValidator.validateImportExcelInputParams(trainingTypeId, year, month, day, importFile);
		
		mav.addObject("isNotMainPage", new Boolean(true));
		mav.addObject("isPublicPage", new Boolean(false));
		mav.addObject("backUrl", "/manageTraining");
		
		if (!"".equals(paramValidMsg)) {
			mav.addObject("validationMsg", paramValidMsg);
			return mav;
		}
		Long trainingTypeIdLong = new Long(trainingTypeId);
		
		String excelFileName = importFile.getOriginalFilename();
		File file = new File(excelFileName);
		importFile.transferTo(file);
		
		String excelFileContentCorrectMsg = null;
		try {
			excelFileContentCorrectMsg = ExcelImportHelper.validateExcelFileContent(file);
		} catch (Exception e) {
			excelFileContentCorrectMsg = Constants.VALIDATION_EXCEL_PROBLEM_DURING_OPENING_EXCEL;
		}
		
		if (!"".equals(excelFileContentCorrectMsg)) {
			mav.addObject("validationMsg", excelFileContentCorrectMsg);
			return mav;
		}
		
		List<TrainingExcelDto> trainingAttendendList = ExcelImportHelper.importTrainingExcel(file);
		
		try {
			TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList);
		} catch (ValidationException ve) {
			mav.addObject("validationMsg", Constants.VALIDATION_EXCEL_IMPORT_IVALID_CONTENT); //TODO: wrong error message
			return mav;
		}
		
		DateTime complDt = new DateTime(new Integer(year), new Integer(month), new Integer(day), 0, 0, 0);
		Date complDate = complDt.toDate();
		
		for (TrainingExcelDto item : trainingAttendendList) {
			GeneralIdResponse excelItemUserId = userService.getUserIdByEmailAndName(item.getEmail(), item.getName());
			boolean isUserExist = excelItemUserId.getValue() == -1L ? false : true; 
			
			if (!isUserExist) {
				List<TrainingPrerequisite> prereqs = showTraineesEligibleForTrainingManager.getPrerequisitesByTrainingTypeId(trainingTypeIdLong);
				boolean isTrainingTypeHasPrereq = (prereqs == null || prereqs.size() == 0) ? false : true;
				if (isTrainingTypeHasPrereq) {
					addFailedUserDataToView(mav, item);
					return mav;
				}
			}
			
			GeneralIdResponse userId = userService.createUserWithAddress(item);
			GeneralIdResponse createOneResult = completedTrainingService.createOne(new CompletedUserTrainingDto(userId.getValue(), trainingTypeIdLong, complDate));
			if (!createOneResult.getSuccess()) {
				addFailedUserDataToView(mav, item);
				return mav;
			}
		}
		
		logger.debug("Processing POST request for importTraining page..");
		mav.addObject("isImportSuccess", new Boolean(true));
		mav.addObject("excelFileName", excelFileName);
		return mav;
	}

	private void addFailedUserDataToView(ModelAndView mav, TrainingExcelDto item) {
		String validationMsg = Constants.VALIDATION_EXCEL_IMPORT_USER_HAS_NO_PREREQUISITE +
				item.toStringUserFriendly() + Constants.VALIDATION_EXCEL_IMPORT_USER_HAS_NO_PREREQUISITE_INFO; 
		mav.addObject("validationMsg", validationMsg);
		mav.addObject("isImportSuccess", new Boolean(false));
	}

	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCompletedTrainingService(
			CompletedTrainingService completedTrainingService) {
		this.completedTrainingService = completedTrainingService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}


}

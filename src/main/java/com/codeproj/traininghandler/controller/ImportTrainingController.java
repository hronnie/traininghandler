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
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
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
		
		File file = new File(importFile.getOriginalFilename());
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
		
		String excelValuesValidMsg = TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList);
		
		if (!"".equals(excelValuesValidMsg)) {
			mav.addObject("validationMsg", paramValidMsg + excelValuesValidMsg);
			return mav;
		}
		
		DateTime complDt = new DateTime(new Integer(year), new Integer(month), new Integer(day), 0, 0, 0);
		Date complDate = complDt.toDate();
		
		for (TrainingExcelDto item : trainingAttendendList) {
			Long userId = userService.createUserWithAddress(item);
			completedTrainingService.createOne(new CompletedUserTrainingDto(userId, new Long(trainingTypeId), complDate));
		}
		
		logger.debug("Processing POST request for importTraining page..");
		mav.addObject("isImportSuccess", new Boolean(true));
		return mav;
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

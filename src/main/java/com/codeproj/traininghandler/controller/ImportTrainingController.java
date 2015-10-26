package com.codeproj.traininghandler.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.domain.ImportTrainingInputs;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.service.importTraining.ImportTrainingService;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.excel.ExcelImportHelper;
import com.codeproj.traininghandler.util.excel.TrainingExcelValidator;


@Controller
@RequestMapping("/manageTraining/importTraining")
public class ImportTrainingController {
	private static final Logger logger = Logger.getLogger(ImportTrainingController.class);
	
	@Autowired
	ImportTrainingService importTrainingService;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView("manageTraining/importTraining");
		
		mav.addObject("isNotMainPage", new Boolean(true));
		mav.addObject("isPublicPage", new Boolean(false));
		logger.debug("Going to importTraining page..");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected  ModelAndView handlePostRequest(@RequestParam("trainingTypeId") String trainingTypeId, 
			@RequestParam("year") String year, 
			@RequestParam("month") String month, 
			@RequestParam("day") String day, 
			@RequestParam("importFile") MultipartFile importFile) throws Exception {
		
		ModelAndView mav = new ModelAndView("manageTraining/importTraining");
		String excelFileName = importFile.getOriginalFilename();
		
		ImportTrainingInputs inputParams = getInputParamsFromFileName(excelFileName);
		if ((StringUtils.isEmpty(trainingTypeId) || StringUtils.isEmpty(year) || StringUtils.isEmpty(month) || StringUtils.isEmpty(day))
				&& inputParams != null) {
			trainingTypeId = inputParams.getTrainingTypeId();
			year = inputParams.getYear();
			month = inputParams.getMonth();
			day = inputParams.getDay();
		}
		
		String paramValidMsg = TrainingExcelValidator.validateImportExcelInputParams(trainingTypeId, year, month, day, importFile);
		
		mav.addObject("isNotMainPage", new Boolean(true));
		mav.addObject("isPublicPage", new Boolean(false));
		
		if (!"".equals(paramValidMsg)) {
			mav.addObject(Constants.MAV_PARAM_NAME_VALIDATION_MSG, paramValidMsg);
			return mav;
		}
		
		File file = new File(excelFileName);
		importFile.transferTo(file);
		
		String excelFileContentCorrectMsg = null;
		try {
			excelFileContentCorrectMsg = ExcelImportHelper.validateExcelFileContent(file);
		} catch (Exception e) {
			excelFileContentCorrectMsg = Constants.VALIDATION_EXCEL_PROBLEM_DURING_OPENING_EXCEL;
		}
		
		if (!"".equals(excelFileContentCorrectMsg)) {
			mav.addObject(Constants.MAV_PARAM_NAME_VALIDATION_MSG, excelFileContentCorrectMsg);
			return mav;
		}
		
		List<TrainingExcelDto> trainingAttendendList = ExcelImportHelper.importTrainingExcel(file);
		
		importTrainingService.importTrainingsFromAttendentList(trainingAttendendList, mav, new ImportTrainingInputs(trainingTypeId, year, month, day));

		logger.debug("Processing POST request for importTraining page..");
		mav.addObject("excelFileName", excelFileName);
		return mav;
	}

	private ImportTrainingInputs getInputParamsFromFileName(String excelFileName) {
		Pattern pattern = Pattern.compile(Constants.IMPORT_EXCEL_REGEXP_PATTERN);
		Matcher matcher = pattern.matcher(excelFileName);
		if (StringUtils.isEmpty(excelFileName) || !matcher.matches()) {
			return null;
		}
		int endOfFirst = excelFileName.indexOf("_");
		String trainingTypeId = excelFileName.substring(0, endOfFirst);
		String year = excelFileName.substring(endOfFirst + 1, endOfFirst + 5);
		String month = excelFileName.substring(endOfFirst + 6, endOfFirst + 8);
		String day = excelFileName.substring(endOfFirst + 9, endOfFirst + 11);
		ImportTrainingInputs result = new ImportTrainingInputs(trainingTypeId, year, month, day);
		return result;
	}

	public void setImportTrainingService(ImportTrainingService importTrainingService) {
		this.importTrainingService = importTrainingService;
	}
}

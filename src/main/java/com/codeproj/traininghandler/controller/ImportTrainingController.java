package com.codeproj.traininghandler.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ThStringUtils;
import com.codeproj.traininghandler.util.excel.ExcelImportHelper;
import com.codeproj.traininghandler.util.excel.TrainingExcelValidator;


@Controller
@RequestMapping("/importTraining")
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
		
		logger.debug("Going to importTraining page..");
		return new ModelAndView("importTraining");
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	protected @ResponseBody ModelAndView handlePostRequest(@RequestParam("trainingTypeId") String trainingTypeId, 
			@RequestParam("year") String year, 
			@RequestParam("month") String month, 
			@RequestParam("day") String day, 
			@RequestParam("importFile") MultipartFile importFile) throws Exception {
		
		ModelAndView mav = new ModelAndView("importTraining");
		
		String paramValidMsg = TrainingExcelValidator.validateImportExcelInputParams(year, month, day, importFile);
		
		List<TrainingExcelDto> trainingAttendendList = ExcelImportHelper.importTrainingExcel(importFile);
		
		String excelValuesValidMsg = TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList);
		
		if (!"".equals(paramValidMsg) || !"".equals(excelValuesValidMsg)) {
			mav.addObject("validationMsg", paramValidMsg + excelValuesValidMsg);
			return mav;
		}
		
		DateTime complDt = new DateTime(new Integer(year), new Integer(month), new Integer(day), 0, 0, 0);
		Date complDate = complDt.toDate();
		
		for (TrainingExcelDto item : trainingAttendendList) {
			Long userId = getUserIdIfExist(item);
			if (userId == -1L) {
				userId = createNewUser(item);
			}
			CompletedUserTrainingDto newComplTraining = new CompletedUserTrainingDto(userId, new Long(trainingTypeId), complDate);
			BooleanResponse completedUserTrainingCheckResult = completedTrainingService.isCompletedTrainingExist(newComplTraining);
			if (completedUserTrainingCheckResult.getPrimitiveBooleanValue()) {
				continue;
			}
			completedTrainingService.create(new CompletedUserTrainingDto(userId, new Long(trainingTypeId), complDate));
		}
		
		logger.debug("Processing POST request for importTraining page..");
		mav.addObject("isImportSuccess", new Boolean(true));
		return mav;
	}

	private Long createNewUser(TrainingExcelDto item) throws ValidationException {
		GeneralIdResponse addressIdResp = addressService.createFromForm(new AddressDto(item.getPostCode(), item.getAddress()));
		GeneralIdResponse userIdResp = userService.createFromForm(new UserDto(item.getName(), item.getPhoneNo(), item.getEmail(), addressIdResp.getValue()));
		return userIdResp.getValue();
	}

	private Long getUserIdIfExist(TrainingExcelDto item) {
		String email = item.getEmail();
		if (StringUtils.isEmpty(email)) {
			String cleanedPhoneNo = ThStringUtils.cleanPhoneNumber(item.getPhoneNo());
			email = cleanedPhoneNo + Constants.EXCEL_TRAINING_MISSING_EMAIL_DOMAIN;
		}
		GeneralIdResponse userId = userService.getUserIdByEmail(email);
		return userId.getValue();
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

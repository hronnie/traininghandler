package com.codeproj.traininghandler.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.util.excel.ExcelImportHelper;


@Controller
@RequestMapping("/importTraining")
public class ImportTrainingController {
	private static final Logger logger = Logger.getLogger(ImportTrainingController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		logger.debug("Going to importTraining page..");
		return new ModelAndView("importTraining");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected @ResponseBody String handlePostRequest(@RequestParam("trainingTypeId") String trainingTypeId, 
			@RequestParam("year") String year, 
			@RequestParam("month") String month, 
			@RequestParam("day") String day, 
			@RequestParam("importFile") MultipartFile importFile) throws Exception {
		
		List<TrainingExcelDto> trainingAttendendList = ExcelImportHelper.importTrainingExcel(importFile);
		
		logger.debug("Processing POST request for importTraining page..");
		 return "You successfully uploaded file";
	}


}

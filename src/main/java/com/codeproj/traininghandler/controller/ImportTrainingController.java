package com.codeproj.traininghandler.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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


@Controller
@RequestMapping("/importTraining")
public class ImportTrainingController {
	private static final Logger logger = Logger.getLogger(ImportTrainingController.class);
	
	public static final int EXCEL_TRAINING_START_ROW_INDEX = 3;
	public static final int EXCEL_TRAINING_START_COLUMN_INDEX = 2;
	public static final int EXCEL_TRAINING_NAME_COL_INDEX = 0;
	public static final int EXCEL_TRAINING_POST_CODE_COL_INDEX = 1;
	public static final int EXCEL_TRAINING_ADDRESS_COL_INDEX = 2;
	public static final int EXCEL_TRAINING_PHONE_NO_COL_INDEX = 3;
	public static final int EXCEL_TRAINING_EMAIL_COL_INDEX = 4;
	
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
		
		
		FileInputStream fileIn = null;

        try
        {
        	File file = new File(importFile.getOriginalFilename());
        	importFile.transferTo(file);
        	fileIn = new FileInputStream(file);
        	
        	Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.getSheetAt(0);
            
            Row row = sheet.getRow(EXCEL_TRAINING_START_ROW_INDEX);
//            Cell cell = row.getCell(5);
            TrainingExcelDto training = new TrainingExcelDto(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_NAME_COL_INDEX).getStringCellValue(), 
            		row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_POST_CODE_COL_INDEX).getStringCellValue(), 
            		row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_ADDRESS_COL_INDEX).getStringCellValue(), 
            		row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_PHONE_NO_COL_INDEX).getStringCellValue(), 
            		row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_EMAIL_COL_INDEX).getStringCellValue());
        } finally {
            if (fileIn != null) {
            	fileIn.close();
            }
        }
		
		logger.debug("Processing POST request for importTraining page..");
		 return "You successfully uploaded file";
	}
	
	public static boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}
}

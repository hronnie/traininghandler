package com.codeproj.traininghandler.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.codeproj.traininghandler.dto.TrainingExcelDto;

import static com.codeproj.traininghandler.util.Constants.*;

public class ExcelImportHelper {
	
	public static List<TrainingExcelDto> importTrainingExcel(MultipartFile importFile)
			throws IOException, FileNotFoundException, InvalidFormatException {
		List<TrainingExcelDto> result = new ArrayList<>();

		File file = new File(importFile.getOriginalFilename());
		importFile.transferTo(file);
        try(FileInputStream fileIn = new FileInputStream(file)) {
        	
        	Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.getSheetAt(0);
            
            int currentExcelRow = EXCEL_TRAINING_START_ROW_INDEX;
            
            while (true) {
            	Row row = sheet.getRow(currentExcelRow);
            	if (isRowEmpty(row)) {
            		break;
            	}
            	TrainingExcelDto training = new TrainingExcelDto(
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_NAME_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_POST_CODE_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_ADDRESS_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_PHONE_NO_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_EMAIL_COL_INDEX))
            			);
            	
            	result.add(training);
            	currentExcelRow++;
            }
        } 
        return result;
	}
	
	private static String getCellValueAsString(Cell cell) {
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK || cell.getCellType() == Cell.CELL_TYPE_ERROR) {
			return "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return new Boolean(cell.getBooleanCellValue()).toString();
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA || cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return new Double(cell.getNumericCellValue()).toString();
		} else {
			return "";
		}
	}
	
	public static boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		
        Cell cell = row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_NAME_COL_INDEX);
        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
        	return true;
        }
        return false;
	}

	public static String validateExcelFileContent(MultipartFile importFile) throws IllegalStateException, IOException, InvalidFormatException {
		String result = "";
		
		File file = new File(importFile.getOriginalFilename());
		importFile.transferTo(file);
        try(FileInputStream fileIn = new FileInputStream(file)) {
        	
        	Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.getSheetAt(0);
            
            int currentExcelRow = EXCEL_TRAINING_START_ROW_INDEX;
            
            while (true) { // TODO : IMPLEMENT VALIDATION
            	Row row = sheet.getRow(currentExcelRow);
            	if (isRowEmpty(row)) {
            		break;
            	}
            	TrainingExcelDto training = new TrainingExcelDto(
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_NAME_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_POST_CODE_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_ADDRESS_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_PHONE_NO_COL_INDEX)), 
            			getCellValueAsString(row.getCell(EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_EMAIL_COL_INDEX))
            			);
            	
            	//result.add(training);
            	currentExcelRow++;
            }
        } 
		return result;
	}
}

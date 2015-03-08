package com.codeproj.traininghandler.util.excel;

import static com.codeproj.traininghandler.util.Constants.EXCEL_NUMBER_OF_DATA_ROWS;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_ADDRESS_COL_INDEX;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_EMAIL_COL_INDEX;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_MAX_CHECK_COLUMN;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_MAX_CHECK_ROW;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_NAME_COL_INDEX;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_PHONE_NO_COL_INDEX;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_POST_CODE_COL_INDEX;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_START_COLUMN_INDEX;
import static com.codeproj.traininghandler.util.Constants.EXCEL_TRAINING_START_ROW_INDEX;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_PROBLEM_DURING_READING_CONTENT;

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

import com.codeproj.traininghandler.dto.TrainingExcelDto;

public class ExcelImportHelper {
	
	public static List<TrainingExcelDto> importTrainingExcel(File file)
			throws IOException, FileNotFoundException, InvalidFormatException {
		List<TrainingExcelDto> result = new ArrayList<>();

        try(FileInputStream fileIn = new FileInputStream(file)) {
        	
        	Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.getSheetAt(0);
            
            int currentExcelRow = EXCEL_TRAINING_START_ROW_INDEX;
            
            while (true) {
            	Row row = sheet.getRow(currentExcelRow);
            	if (isDataRowEmpty(row)) {
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
	
	public static String validateExcelFileContent(File file) throws IllegalStateException, IOException, InvalidFormatException {
		String result = "";
		
        try(FileInputStream fileIn = new FileInputStream(file)) {
        	
        	Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.getSheetAt(0);
            
            for (int i = 0; i < EXCEL_TRAINING_START_ROW_INDEX - 1; i++) {
            	Row row = sheet.getRow(i);
            	if (!isNonDataRowEmpty(row)) {
            		return VALIDATION_EXCEL_PROBLEM_DURING_READING_CONTENT + (i + 1);
            	}
            }
            
            for (int i = EXCEL_TRAINING_START_ROW_INDEX - 1; i < EXCEL_TRAINING_MAX_CHECK_ROW; i++) {
            	Row row = sheet.getRow(i);
            	if (!isNonDataCellsAreEmptyInDataRow(row)) {
            		return VALIDATION_EXCEL_PROBLEM_DURING_READING_CONTENT + (i + 1);
            	}
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		} else {
			return "";
		}
	}
	
	private static boolean isDataRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		
		return isCellEmpty(row, EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_TRAINING_NAME_COL_INDEX);
	}
	
	private static boolean isNonDataRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		for (int i = 0; i < EXCEL_TRAINING_MAX_CHECK_COLUMN; i++) {
			if (!isCellEmpty(row, i)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isCellEmpty(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
        	return true;
        }
        return false;
	}

	private static boolean isNonDataCellsAreEmptyInDataRow(Row row) {
		if (row == null) {
			return true;
		}
		for (int i = 0; i < EXCEL_TRAINING_MAX_CHECK_COLUMN; i++) {
			if (i >= EXCEL_TRAINING_START_COLUMN_INDEX && i < (EXCEL_TRAINING_START_COLUMN_INDEX + EXCEL_NUMBER_OF_DATA_ROWS)) {
				continue;
			}
			if (!isCellEmpty(row, i)) {
				return false;
			}
		}
		return true;
	}
}

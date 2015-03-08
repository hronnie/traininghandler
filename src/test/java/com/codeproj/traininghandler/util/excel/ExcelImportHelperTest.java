package com.codeproj.traininghandler.util.excel;

import static org.junit.Assert.*;
import static com.codeproj.traininghandler.util.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.codeproj.traininghandler.dto.TrainingExcelDto;

public class ExcelImportHelperTest {

	@Test
	public void testImportTrainingExcel() throws FileNotFoundException, InvalidFormatException, IOException {
		URL url = this.getClass().getResource("/valid_training.xlsx");
		File testExcelFile = new File(url.getFile());
		List<TrainingExcelDto> rows = ExcelImportHelper.importTrainingExcel(testExcelFile);
		TrainingExcelDto row1 = rows.get(0);
		assertEquals("The name should be: ", "Most 1es 1", row1.getName());
		assertEquals("The post code should be: 111111", "111111", row1.getPostCode());
		assertEquals("The address should be: 1-es nem reg cim 1", "1-es nem reg cim 1", row1.getAddress());
		assertEquals("The phone no should be: 1111111111 1", "1111111111 1", row1.getPhoneNo());
		assertEquals("The email should be: nemreg1es@mail.com1", "nemreg1es@mail.com1", row1.getEmail());
		TrainingExcelDto row2 = rows.get(1);
		assertEquals("The name should be: ", "Most 1es 2", row2.getName());
		assertEquals("The post code should be: 111112", "111112", row2.getPostCode());
		assertEquals("The address should be: 1-es nem reg cim 2", "1-es nem reg cim 2", row2.getAddress());
		assertEquals("The phone no should be: 1111111111 2", "1111111111 2", row2.getPhoneNo());
		assertEquals("The email should be: nemreg1es@mail.com2", "nemreg1es@mail.com2", row2.getEmail());
		TrainingExcelDto row3 = rows.get(2);
		assertEquals("The name should be: ", "Most 1es 3", row3.getName());
		assertEquals("The post code should be: 111113", "111113", row3.getPostCode());
		assertEquals("The address should be: 1-es nem reg cim 3", "1-es nem reg cim 3", row3.getAddress());
		assertEquals("The phone no should be: 1111111111 3", "1111111111 3", row3.getPhoneNo());
		assertEquals("The email should be: nemreg1es@mail.com3", "nemreg1es@mail.com3", row3.getEmail());
		TrainingExcelDto row4 = rows.get(3);
		assertEquals("The name should be: ", "Most 1es 4", row4.getName());
		assertEquals("The post code should be: 111114", "111114", row4.getPostCode());
		assertEquals("The address should be: 1-es nem reg cim 4", "1-es nem reg cim 4", row4.getAddress());
		assertEquals("The phone no should be: 1111111111 4", "1111111111 4", row4.getPhoneNo());
		assertEquals("The email should be: nemreg1es@mail.com4", "nemreg1es@mail.com4", row4.getEmail());
		TrainingExcelDto row5 = rows.get(4);
		assertEquals("The name should be: ", "Most 1es 5", row5.getName());
		assertEquals("The post code should be: 111115", "111115", row5.getPostCode());
		assertEquals("The address should be: 1-es nem reg cim 5", "1-es nem reg cim 5", row5.getAddress());
		assertEquals("The phone no should be: 1111111111 5", "1111111111 5", row5.getPhoneNo());
		assertEquals("The email should be: nemreg1es@mail.com5", "nemreg1es@mail.com5", row5.getEmail());
	}

	@Test
	public void testValidateExcelFileContent() throws IllegalStateException, InvalidFormatException, IOException {
		testOneExcelFile("/test_training_wrong_format_at_line_1.xlsx", 1);
		testOneExcelFile("/test_training_wrong_format_at_line_1_middle.xlsx", 1);
		testOneExcelFile("/test_training_wrong_format_at_line_3_start.xlsx", 3);
		testOneExcelFile("/test_training_wrong_format_at_line_3_end.xlsx", 3);
		testOneExcelFile("/test_training_wrong_format_at_line_4_start.xlsx", 4);
		testOneExcelFile("/test_training_wrong_format_at_line_4_end.xlsx", 4);
		testOneExcelFile("/test_training_wrong_format_at_line_27_random.xlsx", 27);
	}

	private void testOneExcelFile(String path, int wrongLine) throws IOException, InvalidFormatException {
		URL url = this.getClass().getResource(path);
		File testExcelFile = new File(url.getFile());
		String actValidationMsg = VALIDATION_EXCEL_PROBLEM_DURING_READING_CONTENT + wrongLine;
		assertEquals("The validation message should be: " + actValidationMsg, actValidationMsg, ExcelImportHelper.validateExcelFileContent(testExcelFile));
	}

}

package com.codeproj.traininghandler.rest.trainingtype;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.codeproj.traininghandler.exceptions.ValidationException;

public class TrainingTypeServiceTest {
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
	
	
	public TrainingTypeService service = null;

	@Before
	public void setUp() throws Exception {
		service = new TrainingTypeService();
	}

	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullName() throws ValidationException {
		service.create(null, "8/a", "test description");
	}

	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyName() throws ValidationException {
		service.create("", "8/a", "test description");
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullLevelNo() throws ValidationException {
		service.create("name", null, "test description");
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyLevelNo() throws ValidationException {
		service.create("name", "", "test description");
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullDesc() throws ValidationException {
		service.create("name", "8/a", null);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyDesc() throws ValidationException {
		service.create("name", "8/a", "");
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNameTooLong() throws ValidationException {
		service.create(PARAMETER_STRING_SIZE_MORE_THEN_100, "8/a", "description");
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithlevelNoTooLong() throws ValidationException {
		service.create("name", PARAMETER_STRING_SIZE_MORE_THEN_10, "description");
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithDescTooLong() throws ValidationException {
		service.create("name", "8/a", PARAMETER_STRING_SIZE_MORE_THEN_300);
	}
	
	
	@Test
	public void testCreateTrainingTypeWithValidValues() throws ValidationException {
		service.create("name", "8/a", "dsfdsf");
	}
	
	


}

package com.codeproj.traininghandler.rest.trainingtype;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;

@RunWith(MockitoJUnitRunner.class)
public class TrainingTypeServiceTest {
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
	
	public TrainingTypeService service;
	
	@Mock
	public TrainingTypeManager trainingTypeManager;
	
	public static final String validName = "Aron";
	public static final String validLevelNo = "8/a";
	public static final String validDescription = "test description";
	
	@Before
	public void setUp() throws Exception {
		service = new TrainingTypeService();
		service.setTrainingTypeManager(trainingTypeManager);
		when(trainingTypeManager.create(validName, validLevelNo, validDescription)).thenReturn(true);
	}

	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullName() throws ValidationException {
		service.create(null, validLevelNo, validDescription);
	}

	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyName() throws ValidationException {
		service.create("", validLevelNo, validDescription);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullLevelNo() throws ValidationException {
		service.create(validName, null, validDescription);
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyLevelNo() throws ValidationException {
		service.create(validName, "", validDescription);
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullDesc() throws ValidationException {
		service.create(validName, validLevelNo, null);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyDesc() throws ValidationException {
		service.create(validName, validLevelNo, "");
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNameTooLong() throws ValidationException {
		service.create(PARAMETER_STRING_SIZE_MORE_THEN_100, validLevelNo, validDescription);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithlevelNoTooLong() throws ValidationException {
		service.create(validName, PARAMETER_STRING_SIZE_MORE_THEN_10, validDescription);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithDescTooLong() throws ValidationException {
		service.create(validName, validLevelNo, PARAMETER_STRING_SIZE_MORE_THEN_300);
	}
	
	@Test
	public void testCreateTrainingTypeWithValidValues() throws ValidationException {
		boolean trainingTypesResponse = service.create(validName, validLevelNo, validDescription);
		assertTrue("Create failed.", trainingTypesResponse);
	}
}

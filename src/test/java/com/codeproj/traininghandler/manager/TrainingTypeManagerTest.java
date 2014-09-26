package com.codeproj.traininghandler.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.common.TrainingTypeTestBase;
import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;

@RunWith(MockitoJUnitRunner.class)
public class TrainingTypeManagerTest extends TrainingTypeTestBase {

	public TrainingTypeManager manager = null;
	public TrainingType refTrainingType = new TrainingType(1l, "name", "levelNo", "description");
	
	@Mock
	public TrainingTypeDAO trainingTypeDAO;

	@Before
	public void setUp() throws Exception {
		manager = new TrainingTypeManager();
		manager.setTrainingTypeDAO(trainingTypeDAO);
		when(trainingTypeDAO.getTrainingTypeById(1l)).thenReturn(refTrainingType);
	}

	@Test
	public void testCreateTrainingType() {
		boolean result = manager.create("Valid name", "8/a", "Test description");
		assertTrue("Failed to call DAO method", result);
	}

	@Test 
	public void testGetTrainingTypeById() {
		TrainingType result = manager.getTrainingTypeById(1l);
		assertNotNull("TrainingType result is null", result);
		assertEquals("TrainingType doesn't match", refTrainingType, result);
	}
	
	// getAll
	
	@Test
	public void getAllTrainingTypeWithEmptyResult() {
		when(trainingTypeDAO.getAll()).thenReturn(null);
		assertNull("Result should be null", manager.getAllTrainingType());
	}
	
	@Test
	public void getAllTrainingTypeWithOneResult() {
		when(trainingTypeDAO.getAll()).thenReturn(oneTraingType);
		List<TrainingType> resultFromManager = manager.getAllTrainingType();
		assertNotNull("The result list size should be 1, but it's null", resultFromManager);
		assertEquals("The result list size should be 1, but it's " + resultFromManager.size(), 1, resultFromManager.size());
		assertTrainingType(resultFromManager.get(0), 1l, "name1", "levelNo1", "description1");
	}
	
	@Test
	public void getAllTrainingTypeWithThreeResult() {
		when(trainingTypeDAO.getAll()).thenReturn(threeTraingType);
		List<TrainingType> resultFromManager = manager.getAllTrainingType();

		assertNotNull("The result list size should be 3, but it's null", resultFromManager);
		assertEquals("The result list size should be 3, but it's " + resultFromManager.size(), 3, resultFromManager.size());
		
		assertTrainingType(resultFromManager.get(0), 1l, "name1", "levelNo1", "description1");
		assertTrainingType(resultFromManager.get(1), 2l, "name2", "levelNo2", "description2");
		assertTrainingType(resultFromManager.get(2), 3l, "name3", "levelNo3", "description3");
	}
	
	private void assertTrainingType(TrainingType trainingType, long id, String name, String levelNo, String description) {
		assertTrue("The content of the result has changed in the service", id == trainingType.getTrainingTypeId() 
				&& name.equals(trainingType.getName())
				&& levelNo.equals(trainingType.getLevelNo())
				&& description.equals(trainingType.getDescription()));
	}
}

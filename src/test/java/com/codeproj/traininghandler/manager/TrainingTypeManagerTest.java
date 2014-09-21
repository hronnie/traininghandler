package com.codeproj.traininghandler.manager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;

@RunWith(MockitoJUnitRunner.class)
public class TrainingTypeManagerTest {

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
}

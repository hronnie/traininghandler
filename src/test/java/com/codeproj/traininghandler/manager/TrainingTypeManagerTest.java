package com.codeproj.traininghandler.manager;

import static org.junit.Assert.*;

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
	
	@Mock
	public TrainingTypeDAO trainingTypeDAO;

	@Before
	public void setUp() throws Exception {
		manager = new TrainingTypeManager();
		manager.setTrainingTypeDAO(trainingTypeDAO);
	}

	@Test
	public void testCreateManageType() {
		boolean result = manager.create("Valid name", "8/a", "Test description");
		assertTrue("Failed to call DAO method", result);
	}

}

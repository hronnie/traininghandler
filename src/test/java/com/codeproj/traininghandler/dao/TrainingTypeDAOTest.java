package com.codeproj.traininghandler.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.impl.TrainingTypeDAOImpl;
import com.codeproj.traininghandler.model.TrainingType;

@RunWith(MockitoJUnitRunner.class)
public class TrainingTypeDAOTest {

	public TrainingTypeDAO trainingTypeDAO;
	
	@Before
	public void setUp() throws Exception {
		trainingTypeDAO = new TrainingTypeDAOImpl();
	}

	//@Test
	public void testCreateTraingType() {
		TrainingType trainingType = new TrainingType("Name", "levelNo", "description");
	}
	
	@Test
	public void test() {
		
	}

}

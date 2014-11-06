package com.codeproj.traininghandler.dao;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.codeproj.traininghandler.dao.impl.TrainingTypeDAOImpl;
import com.codeproj.traininghandler.model.TrainingType;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;



// For reference: https://github.com/hronnie/spring-test-dbunit

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"/datasource-testcontext.xml"})
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//		DbUnitTestExecutionListener.class })
public class TrainingTypeDAOTest {

	@Autowired
	public TrainingTypeDAO trainingTypeDAO;
	
	@Before
	public void setUp() throws Exception {
		trainingTypeDAO = new TrainingTypeDAOImpl();
	}

	//@Test
	@DatabaseSetup("TrainingTypeCreateSampleData.xml")
	@ExpectedDatabase("TrainingTypeCreateExpData.xml")
	public void testCreateTraingType() {
		TrainingType trainingType = new TrainingType("Name", "levelNo", "description");
		trainingTypeDAO.create(trainingType);
	}
	
	//@Test
	public void test() {
		
	}
	
}

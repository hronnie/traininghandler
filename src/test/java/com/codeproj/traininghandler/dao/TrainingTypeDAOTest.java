package com.codeproj.traininghandler.dao;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.impl.TrainingTypeDAOImpl;
import com.codeproj.traininghandler.model.TrainingType;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
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

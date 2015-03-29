package com.codeproj.traininghandler.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TrainingTypeIT.class, 
	ShowEligibleUsersIT.class,
	UserServiceIT.class})
public class AllTests {

}

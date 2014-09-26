package com.codeproj.traininghandler.common;

import java.util.ArrayList;
import java.util.List;

import com.codeproj.traininghandler.model.TrainingType;

public class TrainingTypeTestBase {
	public TrainingType one = null;
	public TrainingType two = null;
	public TrainingType three = null;
	public List<TrainingType> threeTraingType = null;
	
	public List<TrainingType> oneTraingType = new ArrayList<>();
	
	{
		one = new TrainingType(1l, "name1", "levelNo1", "description1");
		two = new TrainingType(2l, "name2", "levelNo2", "description2");
		three = new TrainingType(3l, "name3", "levelNo3", "description3");
		oneTraingType = new ArrayList<>();
		oneTraingType.add(one);
		threeTraingType = new ArrayList<>();
		threeTraingType.add(one);
		threeTraingType.add(two);
		threeTraingType.add(three);
	}
}

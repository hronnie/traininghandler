thShowEligibleTraineesModule.controller('thShowEligibleTraineesController', 
		function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	
	var trainingTypeResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	trainingTypeResource.getList().then(function(trainingTypes) {
		$scope.trainingTypeWrapperArray = [];
		$scope.trainingTypes = trainingTypes;
		for (i = 0; i < $scope.trainingTypes.length; i++) {
			var trainingTypeWrapper = {};
			trainingTypeWrapper.id = $scope.trainingTypes[i].trainingTypeId;
			trainingTypeWrapper.name = $scope.trainingTypes[i].name;
			trainingTypeWrapper.completedDate = null;
			
			$scope.trainingTypeWrapperArray.push(trainingTypeWrapper);
		}
	});
	
	//trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	$scope.getEligibleTrainees = function(trainingTypeId) {
		if (!trainingTypeId) {
			return;
		}
		var eligibleTraineesResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/manageTraining/getEligibleTraineesByTrainingTypeId/' + trainingTypeId.id);
		eligibleTraineesResource.get().then(function(eligibleTrinees) {
			$scope.hasEmailUsers = eligibleTrinees.hasEmailUsers;
			$scope.onlyPhoneUsers = eligibleTrinees.onlyPhoneUsers;
		});
	}
	
	
//    $scope.hasEmailUsersGrid = { 
//    	      data: 'hasEmailUsers',
//    	      columnDefs: [{ field: 'name', displayName: 'Név' },
//    	                   { field: 'email', displayName: 'Email' },
//    	                   { field: 'phoneNo', displayName: 'Telefonszám' }],
//    	      jqueryUITheme: false
//    };
//    
//    $scope.onlyPhoneUsersGrid = { 
//    		data: 'onlyPhoneUsers',
//    		columnDefs: [{ field: 'name', displayName: 'Név' },
//    		             { field: 'email', displayName: 'Email' },
//    		             { field: 'phoneNo', displayName: 'Telefonszám' }],
//    		jqueryUITheme: true
//    };
	
});

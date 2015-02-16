thShowEligibleTraineesModule.controller('thShowEligibleTraineesController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	
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
		debugger;
		var eligibleTraineesResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/manageTraining/getEligibleTraineesByTrainingTypeId/' + trainingTypeId.id);
		eligibleTraineesResource.get().then(function(eligibleTrinees) {
			$scope.hasEmailUsers = eligibleTrinees.hasEmailUsers;
			$scope.onlyPhoneUsers = eligibleTrinees.onlyPhoneUsers;
		});
	}
	
    $scope.hasEmailUsersGrid = { 
    	      data: 'hasEmailUsers',
    	      jqueryUITheme: false
    };
    
    $scope.onlyPhoneUsersGrid = { 
    		data: 'onlyPhoneUsers',
    		jqueryUITheme: true
    };
	
});

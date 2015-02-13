thShowEligibleTraineesModule.controller('thShowEligibleTraineesController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	
	var trainingTypeResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	trainingTypeResource.getList().then(function(trainingTypes){
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
	
	trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	
});

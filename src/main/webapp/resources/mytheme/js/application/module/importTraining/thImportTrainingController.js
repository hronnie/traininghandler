thImportTrainingModule.controller('thImportTrainingController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	$scope.trainingDateYear = "";
	$scope.trainingDateMonth = "";
	$scope.trainingDateDay = "";
	$scope.selectedTrainingType = "";
	
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.getList().then(function(trainingTypes){
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

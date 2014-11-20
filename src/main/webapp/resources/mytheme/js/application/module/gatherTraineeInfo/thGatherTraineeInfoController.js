gatherTraineeInfoModule.controller('thGatherTraineeInfoController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	$scope.locale = document.getElementById("localeValue").value;
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.getList().then(function(trainingTypes){
		$scope.trainingTypes = trainingTypes;
	});
	
	$scope.trainingTypeWrapperArray = [];
	for (i = 0; i < $scope.trainingTypes.length; i++) {
		trainingTypeWrapper.id = $scope.trainingTypes[i].id;
		trainingTypeWrapper.name = $scope.trainingTypes[i].name;
		trainingTypeWrapper.levelNo = $scope.trainingTypes[i].;
		trainingTypeWrapper.description = $scope.trainingTypes[i].;
		trainingTypeWrapper.completedDate = '';
		
		$scope.trainingTypeWrapperArray.push(trainingTypeWrapper);
	}

}

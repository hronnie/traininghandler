thGatherTraineeInfoModule.controller('thGatherTraineeInfoController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	$scope.locale = document.getElementById("localeValue").value;
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.getList().then(function(trainingTypes){
		$scope.trainingTypeWrapperArray = [];
		$scope.trainingTypes = trainingTypes;
		for (i = 0; i < $scope.trainingTypes.length; i++) {
			var trainingTypeWrapper = {};
			trainingTypeWrapper.id = $scope.trainingTypes[i].id;
			trainingTypeWrapper.name = $scope.trainingTypes[i].name;
			trainingTypeWrapper.levelNo = $scope.trainingTypes[i].levelNo;
			trainingTypeWrapper.description = $scope.trainingTypes[i].description;
			trainingTypeWrapper.completedDate = '';
			
			$scope.trainingTypeWrapperArray.push(trainingTypeWrapper);
		}
	});

});

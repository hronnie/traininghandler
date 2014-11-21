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
			trainingTypeWrapper.completedDate = null;
			trainingTypeWrapper.isOpen = false;
			
			$scope.trainingTypeWrapperArray.push(trainingTypeWrapper);
		}
	});
	
	// date picker
	$scope.toggleMin = function() {
		$scope.minDate = $scope.minDate ? null : new Date();
	};
	$scope.toggleMin();
	
	$scope.open = function($event, trainingTypeWrapper) {
		$event.preventDefault();
		$event.stopPropagation();
	
		trainingTypeWrapper.isOpen = true;
	};
	
	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
	};
	
	$scope.format = 'yyyy-mm-dd';
	// ****
});

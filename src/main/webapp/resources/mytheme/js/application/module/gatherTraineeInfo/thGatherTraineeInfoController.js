thGatherTraineeInfoModule.controller('thGatherTraineeInfoController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, datepickerPopupConfig) {
	
	$scope.locale = document.getElementById("localeValue").value;
	$scope.defaultCountry = thGlobalConstants.DEFAULT_COUNTRY;
	
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.getList().then(function(trainingTypes){
		$scope.trainingTypeWrapperArray = [];
		$scope.trainingTypes = trainingTypes;
		for (i = 0; i < $scope.trainingTypes.length; i++) {
			var trainingTypeWrapper = {};
			trainingTypeWrapper.id = $scope.trainingTypes[i].trainingTypeId;
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
	
	$scope.openDob = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		
		$scope.isDobOpen = true;
	};
	
	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
	};
	
	$scope.format = 'yyyy-mm-dd';
	$scope.dob=null;
	
	$scope.isDobOpen = false;
	// ****
	
	 // TRANSLATION
	datepickerPopupConfig.currentText = 'Ma';
	datepickerPopupConfig.clearText = 'Törles';
	datepickerPopupConfig.closeText = 'Kész';
});

thImportTrainingModule.controller('thImportTrainingController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	$scope.trainingDateYear = "";
	$scope.trainingDateMonth = "";
	$scope.trainingDateDay = "";
	$scope.selectedTrainingType = "";
	$scope.showInfoFlag = false;
	
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.get().then(function(trainingTypes){
		$scope.trainingTypeWrapperArray = [];
		$scope.trainingTypes = trainingTypes.trainingTypeDtoList;
		for (i = 0; i < $scope.trainingTypes.length; i++) {
			var trainingTypeWrapper = {};
			trainingTypeWrapper.id = $scope.trainingTypes[i].trainingTypeId;
			trainingTypeWrapper.name = $scope.trainingTypes[i].name;
			trainingTypeWrapper.completedDate = null;
			
			$scope.trainingTypeWrapperArray.push(trainingTypeWrapper);
		}
	});
	
	$scope.jumpToNextFromYear = function() {
		if ($scope.trainingDateYear.length >= 4) {
			document.importForm.elements[2].focus();
		}
	}
	
	$scope.jumpToNextFromMonth = function() {
		if ($scope.trainingDateMonth.length >= 2) {
			document.importForm.elements[3].focus();
		}
	}
	
	$scope.showInfo = function() {
		if ($scope.showInfoFlag) {
			$scope.showInfoFlag = false;
		} else {
			$scope.showInfoFlag = true;
		}
	}
	
	trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	
});

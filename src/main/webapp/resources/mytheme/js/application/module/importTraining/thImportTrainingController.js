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
	
//	$scope.jumpToNext = function(element)	{
//		if (content.length == element.maxLength) {
//			next = element.tabIndex;
//			if (next<document.maxform.elements.length) {
//				document.maxform.elements[next].focus();
//			}
//		}
//	}
	
	trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	
});

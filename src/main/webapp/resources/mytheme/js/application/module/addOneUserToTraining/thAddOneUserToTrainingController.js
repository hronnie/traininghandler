thAddOneUserToTrainingModule.controller('thAddOneUserToTrainingController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	$scope.isAddOneUserSuccess = false;
	
	$scope.resetFields = function () {
		$scope.trainingDateYear = "";
		$scope.trainingDateMonth = "";
		$scope.trainingDateDay = "";
		$scope.selectedTrainingType = "";
		$scope.validationMsg = "";
		$scope.name = "";
		$scope.postCode = "";
		$scope.address = "";
		$scope.phoneNo = "";
		$scope.email = "";
	}
	
	$scope.resetFields();
	
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
	
	
	trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	
});

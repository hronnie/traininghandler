thAddOneUserToTrainingModule.controller('thAddOneUserToTrainingController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	$scope.isAddOneUserSuccess = false;
	$scope.format = 'yyyy-mm-dd';
	
	$scope.resetFields = function () {
		$scope.trainingDateYear = "";
		$scope.trainingDateMonth = "";
		$scope.trainingDateDay = "";
		$scope.selectedTrainingType = "";
		$scope.validationMsg = "";
		$scope.trainingExcelDto = {};
		$scope.trainingExcelDto.name = "";
		$scope.trainingExcelDto.postCode = "";
		$scope.trainingExcelDto.address = "";
		$scope.trainingExcelDto.phoneNo = "";
		$scope.trainingExcelDto.email = "";
	}
	
	$scope.validationNeeded = false;
	
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
			document.addOneUserToTrainingForm.elements[2].focus();
		}
	}
	
	$scope.jumpToNextFromMonth = function() {
		if ($scope.trainingDateMonth.length >= 2) {
			document.addOneUserToTrainingForm.elements[3].focus();
		}
	}
	
	$scope.jumpToNextFromDay = function() {
		if ($scope.trainingDateDay.length >= 2) {
			document.addOneUserToTrainingForm.elements[4].focus();
		}
	}
	
	$scope.onSubmit = function() {
		$scope.validationNeeded = true;
	    if(!$scope.addOneUserToTrainingForm.$valid) {
	         return;
	    }
		var saveResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/user/createUserWithAddress');
		saveResource.customPOST($scope.trainingExcelDto).then(function() {
			$scope.isAddOneUserSuccess = true;
			$scope.trainingExcelDto = {};
			$scope.validationMsg = "";
			$scope.validationNeeded = false;
		}, function() {
			validationMsg = "Hiba történt a hozzáadás közben!";
		}
		);
	}
	
	trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	
});

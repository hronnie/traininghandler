thAddOneUserToTrainingModule.controller('thAddOneUserToTrainingController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	$scope.isAddOneUserSuccess = false;
	$scope.format = 'yyyy-mm-dd';
	
	$scope.userId = "";
	$scope.complatedUserTrainingDto = {};
	
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
		var saveUserResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/user/createUserWithAddress');
		saveUserResource.customPOST($scope.trainingExcelDto).then(function(userIdResult) {
			$scope.validationMsg = "";
			$scope.userId = userIdResult;
			$scope.complatedUserTrainingDto = {
					userId: $scope.userId,
					trainingTypeId: $scope.selectedTrainingType.id,
					completedDate: moment({ year: $scope.trainingDateYear, month: $scope.trainingDateMonth, day: $scope.trainingDateDay}).format()
			}
			var saveCompletedTrainingResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/completedTraining/createOne');
			saveCompletedTrainingResource.customPOST($scope.complatedUserTrainingDto).then(function(complResult) {
				$scope.trainingExcelDto = {};
				$scope.validationMsg = "";
				$scope.validationNeeded = false;
				if (complResult.value === -1) {
					$scope.validationMsg = "A megadott tanítványt nem lehetett hozzáadni a tanfolyamhoz, mert még nem végezte el az előfeltételeket.";
				} else {
					$scope.isAddOneUserSuccess = true;
				}
			}, function(err) {
				$scope.validationMsg = "A megadott tanítványt nem lehetett hozzáadni a tanfolyamhoz, mert még nem végezte el az előfeltételeket!";
			});
		}, function(err) {
			$scope.validationMsg = "Hiba történt a hozzáadás közben!";
			return;
		});
		

		
	}
	
	trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	
});

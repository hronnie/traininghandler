trainingTypeModule.controller('thTrainingTypeController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	
	$scope.locale = document.getElementById("localeValue").value;
	$scope.isEditSuccess = false;
	$scope.isAddSuccess = false;
	$scope.validationMsg = "";
	
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.get().then(function(trainingTypes){
		$scope.trainingTypes = trainingTypes.trainingTypeDtoList;
	});

	$scope.validateName = function(data) {
		return thValidationService.validateText(data, 'Name', 'Név', 100, $scope.locale);
	};
	
	$scope.validateLevelNo = function(data) {
		return thValidationService.validateText(data, 'Level Number', 'Fokozat', 10, $scope.locale);
	};
	
	
	$scope.validateDescription = function(data) {
		return thValidationService.validateText(data, 'Description', 'Leírás', 100, $scope.locale);
	};
	
	$scope.saveTrainingType = function(trainingType) {
		var trainingTypeIdString = '0';
		if (!angular.isUndefined(trainingType.trainingTypeId)) {
			trainingTypeIdString = trainingType.trainingTypeId.toString();
		}
		var traningTypePostObj = {
				trainingTypeId: trainingTypeIdString, 
				name: trainingType.name,
				levelNo: trainingType.levelNo,
				description: trainingType.description
		}
		if (angular.isUndefined(trainingType.trainingTypeId)) {
			$scope.saveNewTrainingType(traningTypePostObj);
			return;
		}
		var saveResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/update');
		saveResource.customPOST(traningTypePostObj).then(function(saveResult) {
			$scope.validationMsg = "";
			if (!saveResult.success) {
				$scope.validationMsg = saveResult.message;
			} else {
				$scope.isEditSuccess = true;
			}
		}, function(err) {
			$scope.validationMsg = "A megadott tanfolyam típust nem lehetett szerkeszteni, mert végrehajtás miatt valami hiba történt!";
		});
		$scope.isEditSuccess = true;
	};

	// remove TrainingType - temporarily switched off
//	$scope.removeTrainingType = function(index, id) {
//		var removeMsg = '';
//		if ($scope.locale.indexOf('en') == 0) {
//			removeMsg = 'Are you absolutely sure you want to delete?';
//		} else {
//			removeMsg = 'Teljesen biztos vagy benne, hogy törölni szeretnéd ezt az elemet?';
//		}
//		var deleteTrainingType = window.confirm(removeMsg);
//		if (!deleteTrainingType) {
//			return;
//		}
//		$scope.trainingTypes.splice(index, 1);
//		var deleteResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/delete');
//		var traningTypePostObj = {
//				trainingTypeId: id, 
//				name: '',
//				levelNo: '',
//				description: ''
//		}
//		
//		deleteResource.customPOST(traningTypePostObj);
//	};

	// add TrainingType
	$scope.addTrainingType = function() {
		$scope.inserted = {
			name: '',
			levelNo: '',
			description: '' 
		};
		$scope.trainingTypes.push($scope.inserted);
	};
	
	$scope.saveNewTrainingType = function(traningTypePostObj) {
		var saveResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/create');
		saveResource.customPOST(traningTypePostObj).then(function(addResult) {
			$scope.validationMsg = "";
			if (!addResult.success) {
				$scope.validationMsg = addResult.message;
			} else {
				$scope.isAddSuccess = true;
			}
		}, function(err) {
			$scope.validationMsg = "A megadott tanfolyam típust nem lehetett hozzáadni, mert végrehajtás miatt valami hiba történt!";
		});
	};
});


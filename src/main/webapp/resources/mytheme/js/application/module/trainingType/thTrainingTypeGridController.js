trainingTypeModule.controller('thTrainingTypeController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	
	$scope.locale = document.getElementById("localeValue").value;
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	resource.getList().then(function(trainingTypes){
		$scope.trainingTypes = trainingTypes;
	});

	$scope.validateName = function(data) {
		return thValidationService.validateText(data, 'Name', 'Név', 20);
	};
	
	$scope.validateLevelNo = function(data) {
		return thValidationService.validateText(data, 'Level Number', 'Fokozat', 10);
	};
	
	
	$scope.validateDescription = function(data) {
		return thValidationService.validateText(data, 'Description', 'Leírás', 100);
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
		saveResource.customPOST(traningTypePostObj);
	};

	// remove TrainingType
	$scope.removeTrainingType = function(index, id) {
		var removeMsg = '';
		if ($scope.locale.indexOf('en') == 0) {
			removeMsg = 'Are you absolutely sure you want to delete?';
		} else {
			removeMsg = 'Teljesen biztos vagy benne, hogy törölni szeretnéd ezt az elemet?';
		}
		var deleteTrainingType = window.confirm(removeMsg);
		if (!deleteTrainingType) {
			return;
		}
		$scope.trainingTypes.splice(index, 1);
		var deleteResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/delete');
		var traningTypePostObj = {
				trainingTypeId: id, 
				name: '',
				levelNo: '',
				description: ''
		}
		deleteResource.customPOST(traningTypePostObj);
	};

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
		saveResource.customPOST(traningTypePostObj);
	};
});


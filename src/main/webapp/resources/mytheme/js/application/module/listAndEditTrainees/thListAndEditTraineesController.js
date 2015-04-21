listAndEditTraineesModule.controller('listAndEditTraineesController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	
	$scope.locale = document.getElementById("localeValue").value;
	$scope.isEditSuccess = false;
	$scope.isRemoveSuccess = false;
	
	$scope.filteredTrainees = [];
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    $scope.traineeList = [];
  
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainee/getAll');
	resource.get().then(function(tranees) {
		$scope.traineeList = tranees.trainees;
		$scope.reinitPagination();
	});

	$scope.validateName = function(data) {
		return thValidationService.validateText(data, 'Name', 'Név', 100, $scope.locale);
	};
	
	$scope.validatePostCode = function(data) {
		return thValidationService.validateText(data, 'Post Code', 'Irányítószám', 15, $scope.locale);
	};
	
	$scope.validateFullAddress = function(data) {
		return thValidationService.validateText(data, 'Address', 'Cím', 100, $scope.locale);
	};
	
	$scope.validatePhone = function(data) {
		return thValidationService.validateText(data, 'Phone', 'Telefonszám', 50, $scope.locale);
	};
	
	$scope.validateEmail = function(data) {
		return thValidationService.validateText(data, 'Email', 'Email', 80, $scope.locale);
	};
	
	
	$scope.saveTrainee = function(trainee) {
		var saveResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/');
		var listAndEditTraineesPostObj = {};
		saveResource.customPOST(listAndEditTraineesPostObj);
		$scope.isEditSuccess = true;
	};

	$scope.removeTrainee = function(userId) {
		var removeResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/');
		var listAndEditTraineesPostObj = {};
		saveResource.customPOST();
		$scope.isRemoveSuccess = true;
	};
	
	$scope.reinitPagination = function() {
		  var begin = (($scope.currentPage - 1) * $scope.numPerPage)
		  , end = begin + $scope.numPerPage;
		    
		  $scope.filteredTrainees = $scope.traineeList.slice(begin, end);
	}
	
	$scope.$watch('currentPage + numPerPage', function() {
		$scope.reinitPagination();
	});
	
	
	
});


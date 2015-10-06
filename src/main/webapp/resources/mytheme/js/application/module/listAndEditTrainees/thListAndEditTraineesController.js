listAndEditTraineesModule.controller('listAndEditTraineesController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	
	$scope.locale = document.getElementById("localeValue").value;
	$scope.isEditSuccess = false;
	$scope.isRemoveSuccess = false;
	
	$scope.filteredTrainees = [];
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    $scope.traineeList = [];
  
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/getAll');
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
	
	
	$scope.saveTrainee = function(trainee, userId, addressId) {
		var saveResource = Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/edit');
		var listAndEditTraineesPostObj = {
				userId: userId,
				addressId: addressId,
				name: trainee.name,
				postCode: trainee.postCode,
				address: trainee.fullAddress,
				phone: trainee.phone,
				email: trainee.email,
				completedTrainings: ''
				
		};
		saveResource.customPOST(listAndEditTraineesPostObj);
		$scope.isEditSuccess = true;
	};

	$scope.removeTrainee = function(index, userId, addressId) {
		var removeMsg = 'Biztos törölni szeretnéd ezt a tanítványt? Ez véglegesen törli a hozzá tartozó minden adatot, beleértve az elvégzett tanfolyamokra vonatkozó adatokat is.';
		var deleteTrainee = window.confirm(removeMsg);
		if (!deleteTrainee) {
			return;
		}
		var deleteResource = Restangular.all(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/delete');
		Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/delete').one("userId", userId).one("addressId", addressId).remove();
		$scope.filteredTrainees.splice(index, 1);
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


listAndEditTraineesModule.controller('listAndEditTraineesController', function($scope, $filter, Restangular, thValidationService, thGlobalConstants) {
	
	$scope.locale = document.getElementById("localeValue").value;
	$scope.isEditSuccess = false;
	$scope.isRemoveSuccess = false;
	
	$scope.currentPageTraineeList = [];
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    $scope.traineeList = [];
	$scope.validationMsg = "";
  
	var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/getAll');
	resource.get().then(function(tranees) {
		$scope.traineeList = $filter('orderBy')(tranees.trainees, 'name', false);
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
		$scope.validationMsg = "";
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
		saveResource.customPOST(listAndEditTraineesPostObj).then(function(editResult) {
			$scope.validationMsg = "";
			if (!editResult.success) {
				$scope.validationMsg = editResult.message;
			} else {
				$scope.isEditSuccess = true;
			}
		}, function(err) {
			$scope.validationMsg = "A megadott tanítványt nem lehetett szerkeszteni, mert végrehajtás miatt valami hiba történt!";
		});
	};

	$scope.removeTrainee = function(trainee) {
		$scope.validationMsg = "";
		var removeMsg = 'Biztos törölni szeretnéd ezt a tanítványt? Ez véglegesen törli a hozzá tartozó minden adatot, beleértve az elvégzett tanfolyamokra vonatkozó adatokat is.';
		var deleteTrainee = window.confirm(removeMsg);
		if (!deleteTrainee) {
			return;
		}
		
		var deleteResource = Restangular.all(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/delete');
		Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/delete')
			.one("userId", trainee.userId).one("addressId", trainee.addressId).remove().then(function(delResult) {
				$scope.validationMsg = "";
				if (!delResult.success) {
					
					$scope.validationMsg = delResult.message;
				} else {
					$scope.isRemoveSuccess = true;
				}
			}, function(err) {
				$scope.validationMsg = "A megadott tanítványt nem lehetett hozzáadni a tanfolyamhoz, mert még nem végezte el az előfeltételeket!";
			});
	      
		var indexFilt = $scope.filteredTraineeList.indexOf(trainee);
		var indexCur = $scope.currentPageTraineeList.indexOf(trainee);
		var indexOrig = $scope.traineeList.indexOf(trainee);
        if (indexFilt != -1) {
        	$scope.filteredTraineeList.splice(indexFilt, 1);
        }
        if (indexCur != -1) {
        	$scope.currentPageTraineeList.splice(indexCur, 1);
        }
        if (indexOrig != -1) {
        	$scope.traineeList.splice(indexOrig, 1);
        }
		$scope.reinitPagination();
	};
	
	$scope.reinitPagination = function() {
	  var begin = (($scope.currentPage - 1) * $scope.numPerPage)
	  , end = begin + $scope.numPerPage;
	    
	  $scope.currentPageTraineeList = $scope.traineeList.slice(begin, end);
	}
	
	$scope.$watch('currentPage + numPerPage', function() {
		$scope.reinitPagination();
	});
	
	
	
});


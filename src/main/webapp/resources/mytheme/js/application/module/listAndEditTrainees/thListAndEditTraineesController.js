listAndEditTraineesModule.controller('listAndEditTraineesController', function($scope, $filter, $window, Restangular, thValidationService, thGlobalConstants) {
	
	$scope.locale = document.getElementById("localeValue").value;
	$scope.isEditSuccess = false;
	$scope.isRemoveSuccess = false;
	$scope.isEditComplTrSuccess = false;
	$scope.isRemoveComplTrSuccess = false;
	$scope.complTrValidationMsg = "";
	
	$scope.currentPageTraineeList = [];
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    $scope.traineeList = [];
	$scope.validationMsg = "";
	$scope.isComplEdit = false;
	$scope.completedTrList = [];
  
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
		
		//var deleteResource = Restangular.all(thGlobalConstants.BASE_WS_URL + thGlobalConstants.TRAINEE_URL + '/delete');
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
	
	$scope.goBackFromComplTrainingPage = function() {
		$scope.toogleEditView();
		$window.location.reload();
	}
	
	$scope.showEditComplTrainingsPage = function(userId) {
		$scope.toogleEditView();
		$scope.completedTrList = [];
		var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.COMPL_USER_TRAINING_SERVICE_URL + '/getAllVieawable/' + userId);
		resource.get().then(function(complTrainings) {
			for (i = 0; i < complTrainings.completedUserTrainingDtoList.length; i++) {
				var completedUserTrainingDto = {};
				completedUserTrainingDto.completedUserTrainingId = complTrainings.completedUserTrainingDtoList[i].completedUserTrainingId;
				completedUserTrainingDto.userId = complTrainings.completedUserTrainingDtoList[i].userId;
				completedUserTrainingDto.trainingTypeId = complTrainings.completedUserTrainingDtoList[i].trainingTypeId;
				completedUserTrainingDto.completedDate = complTrainings.completedUserTrainingDtoList[i].completedDate;
				completedUserTrainingDto.trainingTypeName = complTrainings.completedUserTrainingDtoList[i].trainingTypeName;
				
				$scope.completedTrList.push(completedUserTrainingDto);
			}
		});
	}
	
	angular.isUndefinedOrNull = function(val) {
	    return angular.isUndefined(val) || val === null 
	}
	
	$scope.toogleEditView = function() {
		$scope.isComplEdit = !$scope.isComplEdit;
		$scope.validationMsg = '';
		$scope.isEditComplTrSuccess = false;
		$scope.isRemoveComplTrSuccess = false;
		$scope.complTrValidationMsg = '';
	}
	
	$scope.removeCompletedTraining = function(complTr) {
		$scope.complTrValidationMsg = "";
		var removeMsg = 'Biztos törölni szeretnéd ezt az elvégzett tanfolyamot?';
		var deleteCompletedTraining = window.confirm(removeMsg);
		if (!deleteCompletedTraining) {
			return;
		}
		Restangular.one(thGlobalConstants.BASE_WS_URL + thGlobalConstants.COMPL_USER_TRAINING_SERVICE_URL + '/delete')
			.one("completedUserTrainingId", complTr.completedUserTrainingId).remove().then(function(delResult) {
				$scope.complTrValidationMsg = "";
				if (!delResult.success) {
					$scope.complTrValidationMsg = delResult.message;
				} else {
					$scope.isRemoveComplTrSuccess = true;
				}
			}, function(err) {
				$scope.complTrValidationMsg = "A megadott elvégzett tanfolyamot nem lehetett törölni ismeretlen hiba miat! Kérlek írj a karbantartónak!";
			});
		
		var index = $scope.completedTrList.indexOf(complTr);
        if (index != -1) {
        	$scope.completedTrList.splice(index, 1);
        }
	}
	
	$scope.editCompletedTraining = function(userId, trainingTypeId) {
		
	}
});


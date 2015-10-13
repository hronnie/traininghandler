thShowEligibleTraineesModule.controller('thShowEligibleTraineesController', 
		function($scope, $filter, Restangular, thValidationService, thGlobalConstants, thTrainingTypeService) {
	
	var trainingTypeResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
	trainingTypeResource.getList().then(function(trainingTypes) {
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
	
	$scope.emailList = "";
	$scope.isResultEmpty = false;
	$scope.traineeSelection = [];
	
	//trainingTypeArray = thTrainingTypeService.getAllTrainingType();
	$scope.getEligibleTrainees = function(trainingType) {
		$scope.isResultEmpty = false;
		if (!trainingType) {
			return;
		}
		var eligibleTraineesResource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/manageTraining/getEligibleTraineesByTrainingTypeId/' + trainingType.id);
		eligibleTraineesResource.get().then(function(eligibleTrinees) {
			$scope.hasEmailUsers = eligibleTrinees.hasEmailUsers;
			$scope.onlyPhoneUsers = eligibleTrinees.onlyPhoneUsers;
			
			if ((!$scope.hasEmailUsers || $scope.hasEmailUsers.length < 1) && (!$scope.onlyPhoneUsers || $scope.onlyPhoneUsers.length < 1)) {
				$scope.isResultEmpty = true; 
			}
			
			$scope.emailList = '';
			
			if (!$scope.hasEmailUsers || $scope.hasEmailUsers.length < 1) {
				return;
			}
			$scope.initEmailUsers()
            $scope.emailList = $scope.emailList.substring(0, $scope.emailList.length - 2);
		});
	}
	
	$scope.initEmailUsers = function() {
		$scope.emailList = "";
		$scope.traineeSelection = [];
        _($scope.hasEmailUsers).forEach(function(user) {
        	$scope.emailList += user.email;
        	$scope.emailList += ", ";
        	$scope.traineeSelection.push(user);
        });
	}
	
	$scope.toggleSelection = function(user) {
	    var idx = $scope.traineeSelection.indexOf(user);

	    if (idx > -1) {
	      $scope.traineeSelection.splice(idx, 1);
	      updateEmailList();
	    } else {
	      $scope.traineeSelection.push(user);
	      updateEmailList();
	    }
	};
	
	$scope.noEmailUsersToggle = function() {
		$scope.emailList = "";
		$scope.traineeSelection = [];
	};
	
	function updateEmailList() {
		$scope.emailList = "";
        _($scope.traineeSelection).forEach(function(user) {
        	$scope.emailList += user.email;
        	$scope.emailList += ", ";
        });
	}
	
	
//    $scope.hasEmailUsersGrid = { 
//    	      data: 'hasEmailUsers',
//    	      columnDefs: [{ field: 'name', displayName: 'Név' },
//    	                   { field: 'email', displayName: 'Email' },
//    	                   { field: 'phoneNo', displayName: 'Telefonszám' }]
//    };
//    
//    $scope.onlyPhoneUsersGrid = { 
//    		data: 'onlyPhoneUsers',
//    		columnDefs: [{ field: 'name', displayName: 'Név' },
//    		             { field: 'email', displayName: 'Email' },
//    		             { field: 'phoneNo', displayName: 'Telefonszám' }]
//    };
	
});

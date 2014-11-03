var app = angular.module("app", ["xeditable", "restangular"]);

app.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})
/*
app.run(function(editableOptions) {
  editableOptions.theme = 'bs3';
});
*/

app.controller('thTrainingTypeController', function($scope, $filter, Restangular) {
	var resource = Restangular.one('traininghandler/rest/trainingtype/getAll');
	resource.getList().then(function(trainingTypes){
		$scope.trainingTypes = trainingTypes;
	});

  $scope.validateName = function(data) {
	  if($.trim(data) == '') {
		  return 'This field is required';
	  }
  };
  
  $scope.validateLevelNo = function(data) {
	  debugger;
	  if($.trim(data) == '') {
		  return 'This field is required';
	  }
  };
  
  $scope.validateDescription = function(data) {
	  if($.trim(data) == '') {
		  return 'This field is required';
	  }
  };

  // TODO: create a common validation service for the fields
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
	  var saveResource = Restangular.one('traininghandler/rest/trainingtype/update');
	  saveResource.customPOST(traningTypePostObj);
  };

  // remove TrainingType
  $scope.removeTrainingType = function(index, id) {
	  $scope.trainingTypes.splice(index, 1);
	  var deleteResource = Restangular.one('traininghandler/rest/trainingtype/delete');
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
	  var saveResource = Restangular.one('traininghandler/rest/trainingtype/create');
	  saveResource.customPOST(traningTypePostObj);
  };
});


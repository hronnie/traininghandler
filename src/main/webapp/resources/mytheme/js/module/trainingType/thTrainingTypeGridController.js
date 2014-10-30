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

  $scope.checkName = function(data) {
	  if (data == 'Aron') {
		  return "This is not a valid name I'm afraid";
	  }
  };

  $scope.saveTrainingType = function(trainingType) {
	  var traningTypePostObj = {
			  trainingTypeId: trainingType.trainingTypeId.toString(), 
			  name: trainingType.name,
			  levelNo: trainingType.levelNo,
			  description: trainingType.description
			  
	  }
	  console.log(trainingType);
	  console.log(trainingType.name);
	  var saveResource = Restangular.one('traininghandler/rest/trainingtype/update');
	  saveResource.customPOST(traningTypePostObj);
		//$scope.vegetables.push(newResource);
    //$scope.user not updated yet
//    angular.extend(data, {id: id});
//    return $http.post('/saveTrainingType', data);
  };

  // remove TrainingType
  $scope.removeTrainingType = function(index, id) {
//      alert(id);
//    $scope.trainingTypes.splice(index, 1);
  };

  // add TrainingType
  $scope.addTrainingType = function() {
//    $scope.inserted = {
//      id: $scope.trainingTypes.length+1,
//      name: '',
//      levelNo: null,
//      description: null 
//    };
//    $scope.trainingTypes.push($scope.inserted);
  };
});


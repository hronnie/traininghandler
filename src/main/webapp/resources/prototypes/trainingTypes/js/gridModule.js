var app = angular.module("app", ["xeditable", "restangular"]);
/*
app.run(function(editableOptions) {
  editableOptions.theme = 'bs3';
});
*/
app.controller('thTrainingTypeController', function($scope, $filter, Restangular) {
	
	var trainingTypesRest = Restangular.allUrl('t', 'http://localhost:8080/traininghandler/rest/trainingtype/getAll');
	$scope.trainingTypes = trainingTypesRest.getList();
	 
//	 [
//    {id: 1, name: '1 as/es tanfolyam', levelNo: '1', description: 'teszt leiras'},
//    {id: 2, name: '2 as/es tanfolyam', levelNo: '2', description: 'teszt leiras'},
//    {id: 3, name: '3 as/es tanfolyam', levelNo: '3', description: 'teszt leiras'},
//    {id: 4, name: '4 as/es tanfolyam', levelNo: '4', description: 'teszt leiras'},
//    {id: 5, name: '5 as/es tanfolyam', levelNo: '4', description: 'teszt leiras'},
//    {id: 6, name: '6 as/es tanfolyam', levelNo: '5', description: 'teszt leiras'},
//    {id: 7, name: '7 as/es tanfolyam', levelNo: '6', description: 'teszt leiras'},
//    {id: 8, name: '8 as/es tanfolyam', levelNo: '7', description: 'teszt leiras'},
//    {id: 9, name: '9 as/es tanfolyam', levelNo: '8', description: 'teszt leiras'},
//    {id: 10, name: '10 as/es tanfolyam', levelNo: '9', description: 'teszt leiras'},
//    {id: 11, name: '11 as/es tanfolyam', levelNo: '10', description: 'teszt leiras'},
//    {id: 12, name: '12 as/es tanfolyam', levelNo: '11', description: 'teszt leiras'},
//    {id: 13, name: '13 as/es tanfolyam', levelNo: '12', description: 'teszt leiras'},
//    {id: 14, name: '14 as/es tanfolyam', levelNo: '13', description: 'teszt leiras'},
//  ]; 


  $scope.checkName = function(data) {
	  if (data == 'Aron') {
		  return "This is not a valid name I'm afraid";
	  }
  };

  $scope.saveTrainingType = function(data, id) {
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


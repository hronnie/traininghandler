var trainingTypeModule = angular.module("trainingTypeModule", ["xeditable", "restangular", "thValidationService"]);

trainingTypeModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})

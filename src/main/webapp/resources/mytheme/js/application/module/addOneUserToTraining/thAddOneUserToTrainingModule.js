var thAddOneUserToTrainingModule = angular.module("thAddOneUserToTrainingModule", ["restangular", "thValidationService", "thGlobalConstants", "thTrainingTypeService"]);

thAddOneUserToTrainingModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})
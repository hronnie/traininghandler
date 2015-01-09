var thImportTrainingModule = angular.module("thImportTrainingModule", ["restangular", "thValidationService", "thGlobalConstants", "thTrainingTypeService"]);

thImportTrainingModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})
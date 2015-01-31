var thShowEligibleTraineesModule = angular.module("thShowEligibleTraineesModule", ["restangular", "thValidationService", "thGlobalConstants", "thTrainingTypeService"]);

thShowEligibleTraineesModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})
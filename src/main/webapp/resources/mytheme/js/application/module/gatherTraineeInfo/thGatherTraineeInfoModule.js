var thGatherTraineeInfoModule = angular.module("thGatherTraineeInfoModule", ["restangular", "thValidationService", "thGlobalConstants", "ui.bootstrap"]);

thGatherTraineeInfoModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})

//thGatherTraineeInfoModule.run(function(editableOptions) {
//	editableOptions.theme = 'bs3';
//});
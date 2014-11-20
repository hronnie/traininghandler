var gatherTraineeInfoModule = angular.module("gatherTraineeInfo", ["restangular", "thValidationService", "thGlobalConstants"]);

gatherTraineeInfoModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})

gatherTraineeInfoModule.run(function(editableOptions) {
	editableOptions.theme = 'bs3';
});
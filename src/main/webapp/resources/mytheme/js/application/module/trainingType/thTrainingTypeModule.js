var trainingTypeModule = angular.module("trainingTypeModule", ["xeditable", "restangular", "thValidationService", "thGlobalConstants", "'ui.bootstrap'"]);

trainingTypeModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})

trainingTypeModule.run(function(editableOptions) {
	editableOptions.theme = 'bs3';
});
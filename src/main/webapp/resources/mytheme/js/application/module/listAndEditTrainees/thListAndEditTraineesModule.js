var listAndEditTraineesModule = angular.module("listAndEditTraineesModule", ["xeditable", "restangular", "thValidationService", "thGlobalConstants"]);

listAndEditTraineesModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})

listAndEditTraineesModule.run(function(editableOptions) {
	editableOptions.theme = 'bs3';
});
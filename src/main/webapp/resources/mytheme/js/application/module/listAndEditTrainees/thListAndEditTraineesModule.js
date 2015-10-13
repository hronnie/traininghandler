var listAndEditTraineesModule = angular.module("listAndEditTraineesModule", ["xeditable", "restangular", "thValidationService", "thGlobalConstants", "ui.bootstrap"]);

listAndEditTraineesModule.config(function(RestangularProvider){
	RestangularProvider.setDefaultHeaders({ "Content-Type": "application/json" });
})

listAndEditTraineesModule.run(function(editableOptions) {
	editableOptions.theme = 'bs3';
});

listAndEditTraineesModule.filter('to_trusted', ['$sce', function($sce){
    return function(text) {
        return $sce.trustAsHtml(text);
    };
}]);
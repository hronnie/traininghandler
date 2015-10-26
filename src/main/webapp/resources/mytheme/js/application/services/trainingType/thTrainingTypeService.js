var thTrainingTypeService = angular.module('thTrainingTypeService', ["restangular", "thGlobalConstants"]);
thTrainingTypeService.factory('thTrainingTypeService', function(Restangular, thGlobalConstants) {
	return {
		getAllTrainingType : function() {
//			var resource = Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll');
			
			return Restangular.one(thGlobalConstants.BASE_WS_URL + '/trainingtype/getAll').get().then(function(trainingTypes){
				return trainingTypes;
			});
		}
	}
});

//FIXME: won't return with training types outside of service'
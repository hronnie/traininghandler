var thGlobalConstants = angular.module('thGlobalConstants', []);
thGlobalConstants.factory('thGlobalConstants', function() {
	return {
		BASE_WS_URL : 'traininghandler/rest',
		TRAINEE_URL : '/trainee',
		COMPL_USER_TRAINING_SERVICE_URL: '/completedTraining',
		DEFAULT_COUNTRY: "Magyarország"
	}
});

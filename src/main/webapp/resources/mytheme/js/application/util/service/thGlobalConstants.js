var thGlobalConstants = angular.module('thGlobalConstants', []);
thGlobalConstants.factory('thGlobalConstants', function() {
	return {
		BASE_WS_URL : 'traininghandler/rest',
		TRAINEE_URL : '/trainee',
		DEFAULT_COUNTRY: "Magyarország"
	}
});

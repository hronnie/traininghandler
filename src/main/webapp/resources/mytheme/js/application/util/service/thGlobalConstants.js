var thGlobalConstants = angular.module('thGlobalConstants', []);
thGlobalConstants.factory('thGlobalConstants', function() {
	return {
		BASE_WS_URL : 'traininghandler/rest',
		DEFAULT_COUNTRY: "Magyarország"
	}
});

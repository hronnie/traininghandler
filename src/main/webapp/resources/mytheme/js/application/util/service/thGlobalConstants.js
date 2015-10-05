var thGlobalConstants = angular.module('thGlobalConstants', []);
thGlobalConstants.factory('thGlobalConstants', function() {
	return {
		BASE_WS_URL : 'vacikezelo/rest',
		DEFAULT_COUNTRY: "Magyarország"
	}
});

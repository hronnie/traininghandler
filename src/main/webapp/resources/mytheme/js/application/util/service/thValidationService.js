var thValidationService = angular.module('thValidationService', []);
thValidationService.factory('thValidationService', function() {
	return {
		validateText : function(value, fieldNameEn, fieldNameHu, maxLength) { 
			var inputValue = $.trim(value);
			if(!inputValue || inputValue.length > maxLength) {
				if ($scope.locale.indexOf('en') == 0) {
					return fieldNameEn + " can't be empty or its size more than " + maxLength + ".";
				} 
				return "A(z) " + fieldNameHu + " mező nem lehet üres, vagy hosszabb, mint " + maxLength + " karakter.";
			}
		}
	}
});

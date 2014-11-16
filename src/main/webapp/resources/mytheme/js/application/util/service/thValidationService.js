var thValidationService = angular.module('thValidationService', []);
thValidationService.factory('thValidationService', function() {
	return {
		validateText : function(value, fieldNameEn, fieldNameHu, maxLength, locale) { 
			var inputValue = $.trim(value);
			if(!inputValue || inputValue.length > maxLength) {
				if (locale.indexOf('en') == 0) {
					return fieldNameEn + " can't be empty or its size \nmore than " + maxLength + ".";
				} 
				return "A(z) " + fieldNameHu + " mező nem lehet üres, \nvagy hosszabb, mint " + maxLength + " karakter.";
			}
		}
	}
});

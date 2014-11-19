$(document).ready(function() {
	setLanguage(false);
});

function toogleLanguage() {
	setLanguage(false);
}

function setLanguage(toogle) {
	var locValue = document.getElementById("localeValue").value;
	var changeLangLink = document.getElementById("changeLanguageLinkId");
	if (locValue.match("^en")) {
		if (!toogle) {
			changeLangLink.href = "?language=hu_HU";
		} else {
			changeLangLink.href = "?language=en";
		}
	} else {
		if (!toogle) {
			changeLangLink.href = "?language=en";
		} else {
			changeLangLink.href = "?language=hu_HU";
		}
	}
}
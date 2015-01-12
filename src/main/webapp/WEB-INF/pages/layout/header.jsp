<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="icon" href="../../favicon.ico"> -->
<script src="<c:url value="/resources/js/application/lib/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/angular.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/angular-mocks.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/xeditable.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/restangular.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/lodash.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/modernizr.custom.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/moment-with-locales.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/ui-utils-ieshiv.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/ui-utils.min.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/ui-bootstrap-tpls-0.12.0.js" />"></script>
<script src="<c:url value="/resources/js/application/lib/bootstrap-filestyle.js" />"></script>
<c:if test="${fn:startsWith(pageContext.response.locale, 'hu')}">
<script src="http://code.angularjs.org/1.0.8/i18n/angular-locale_hu-hu.js"></script>
</c:if>
<script src="<c:url value="/resources/js/application/common/header.js" />"></script>

<script	src="<c:url value="/resources/js/application/util/service/thValidationService.js" />"></script>
<script	src="<c:url value="/resources/js/application/util/service/thGlobalConstants.js" />"></script>
<script	src="<c:url value="/resources/js/application/services/trainingType/thTrainingTypeService.js" />"></script>

<link href="<c:url value="/resources/style/lib/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/lib/bootstrap-theme.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/lib/xeditable.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/lib/font-awesome.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/style/buttons/default.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/buttons/component.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/help.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/style/grid.css" />" rel="stylesheet" />




<script>
$(document).ready(function() {
	$('#homeLinkId').hover(function () {
		$('img#homeIconId').attr("src", "<c:url value="/resources/images/home_hover.jpg" />");
	});
	
	$('#homeLinkId').mouseout(function () {
		$('img#homeIconId').attr("src", "<c:url value="/resources/images/home.jpg" />");
	});

});

</script>
<spring:url value="/" var="homeUrl" htmlEscape="true" />
</head>
<body>
<input type="hidden" id="localeValue" value="${pageContext.response.locale}">
	<div id="wrapper">
		
		<div id="header" align="right">
			<header data-scroll-header="" role="banner" class="header header--page" id="js-header">
			<c:if test="${!isPublicPage}">
				<span class="splitter">|</span>
				<a href="${homeUrl}" class="headerLink" id="homeLinkId"><i class="fa fa-home fa-fw fa-2x"></i></a>
			</c:if>
<!-- 				<span class="splitter">|</span> -->
<!-- 				<a href="#" class="headerLink" id="loginHeadLinkId"><spring:message code="header.link.login" /></a> -->
<!-- 				<span class="splitter">|</span> -->
<!-- 				<a href="#" class="headerLink" id="registerHeadLinkId"><spring:message code="header.link.register" /></a> -->
				<span class="splitter">|</span>
				<a href="javascript: toogleLanguage();" class="headerLink" id="changeLanguageLinkId"><spring:message code="header.link.change.language" /></a>
				<span class="splitter">|</span>
				<a href="#" class="headerLink" id="trigger-overlay"><i class="fa fa-question fa-fw fa-2x"></i></a>
				<span class="splitter">|</span>
			
			</header>

		</div><!-- #header -->
		
		<div id="content" >
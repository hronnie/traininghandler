<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="/resources/js/jquery/jquery-2.1.1.js" />"></script>
<!-- <script src="<c:url value="/resources/js/jquery/jquery-2.1.1.min.js" />"></script>-->
<script src="<c:url value="/resources/js/header.js" />"></script>

<link href="<c:url value="/resources/style/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/buttons/component.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style/buttons/default.css" />" rel="stylesheet">

<script>
$(document).ready(function() {
	$('#homeLinkId').hover(function () {
		$('img#homeIcon').attr("src", "<c:url value="/resources/images/home_hover.jpg" />");
	});
	
	$('#homeLinkId').mouseout(function () {
		$('img#homeIcon').attr("src", "<c:url value="/resources/images/home.jpg" />");
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
				<span class="splitter">|</span>
				<a href="${homeUrl}" class="headerLink" id="homeLinkId"><img id="homeIcon" src="<c:url value="/resources/images/home.jpg" />"></a>
				<span class="splitter">|</span>
				<a href="#" class="headerLink" id="loginHeadLinkId"><spring:message code="header.link.login" /></a>
				<span class="splitter">|</span>
				<a href="#" class="headerLink" id="registerHeadLinkId"><spring:message code="header.link.register" /></a>
				<span class="splitter">|</span>
				<a href="javascript: toogleLanguage();" class="headerLink" id="changeLanguageLinkId"><spring:message code="header.link.change.language" /></a>
				<span class="splitter">|</span>
			
			</header>

		</div><!-- #header -->
		
		<div id="content">
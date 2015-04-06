<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="icon" href="../../favicon.ico"> -->
<link rel="shortcut icon" href="/resources/images/favicon-edit.ico" />

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
<script src="<c:url value="/resources/js/application/lib/ng-grid-2.0.14.min.js" />"></script>
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
<link href="<c:url value="/resources/style/linkStyles.css" />" rel="stylesheet" />

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="username" />
</sec:authorize>

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
<spring:url value="${backUrl}" var="backUrlPath" htmlEscape="true" />

</head>
<body>
<input type="hidden" id="localeValue" value="${pageContext.response.locale}">








<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle headerLink" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="index.button.manage.trainings" /> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href='<c:url value="/manageTraining/importTraining" />'><spring:message code="manageTraining.button.importTraining" /></a></li>
            <li><a href='<c:url value="/manageTraining/addOneUserToTraining" />'><spring:message code="manageTraining.button.addOneUserToTraining" /></a></li>
            <li><a href='<c:url value="/manageTraining/showEligibleTrainees" />''><spring:message code="manageTraining.button.showEligibleTrainees" /></a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle headerLink" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="index.button.manage.training.types" /> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href='<c:url value="/trainingType" />'>Szerkesztés</a></li>
            <li class="divider"></li>
          </ul>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>








	<div id="wrapper">
		
		
		<div id="header" align="right">
		
			<header data-scroll-header="" role="banner" class="header header--page" id="js-header">
<!-- 				menu -->
			    
				<c:if test="${!isPublicPage && isNotMainPage}">
					<span class="splitter">|</span>
					<a href="${backUrlPath}" class="headerLink" id="backLinkId"><i class="fa fa-backward fa-lg"></i></a>
				</c:if>
				<c:if test="${!isPublicPage}">
					<span class="splitter">|</span>
					<a href="${homeUrl}" class="headerLink" id="homeLinkId"><i class="fa fa-home fa-fw fa-2x"></i></a>
				</c:if>
				<span class="splitter">|</span>
				<a href="javascript: toogleLanguage();" class="headerLink" id="changeLanguageLinkId"><spring:message code="header.link.change.language" /></a>
				<span class="splitter">|</span>
				<a href="#" class="headerLink">${username}</i></a>
				<span class="splitter">|</span>
				<a href="<c:url value="/logout" />" class="headerLink"><spring:message code="header.link.logout" /></a>
				<span class="splitter">|</span>
				<a href="#" class="headerLink" id="trigger-overlay"><i class="fa fa-question fa-fw fa-2x"></i></a>
				<span class="splitter">|</span>
			
			</header>

		</div><!-- #header -->
		
		<div id="content" >
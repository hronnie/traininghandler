<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

</head>
<body>
<input type="hidden" id="localeValue" value="${pageContext.response.locale}">


	<div id="wrapper">

<nav class="navbar navbar-xs">
  <div class="container-fluid">
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="headerLinkIcon"><a href='<c:url value="/" />' class="headerLink" id="homeLinkId"><i class="fa fa-home fa-fw fa-2x"></i></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle headerLink" data-toggle="dropdown" role="button" aria-expanded="false">Tanfolyamok <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href='<c:url value="/manageTraining/importTraining" />'>Import�l�s</a></li>
            <li><a href='<c:url value="/manageTraining/addOneUserToTraining" />'>Egyenk�nt hozz�ad�s</a></li>
            <li class="divider"></li>
            <li><a href='<c:url value="/manageTraining/showEligibleTrainees" />'>Megh�v�k</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle headerLink" data-toggle="dropdown" role="button" aria-expanded="false">Tan�tv�nyok <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href='<c:url value="/listAndEditTrainees" />'>Szerkeszt�s</a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle headerLink" data-toggle="dropdown" role="button" aria-expanded="false">Be�ll�t�sok <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href='<c:url value="/trainingType" />'>Tanfolyam t�pusok szerkeszt�se</a></li>
          </ul>
        </li>
      </ul>
      
       <ul class="nav navbar-nav navbar-right">
        <li><a href="#" class="headerLink">${username}</a></li>
        <li><a href="<c:url value="/logout" />" class="headerLink">Kijelentkez�s</a></li>
        <li class="headerLinkIcon"><a href="#" class="headerLink" id="trigger-overlay"><i class="fa fa-question fa-fw fa-2x"></i></a></li>
      </ul>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
		
		<div id="content" >
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title><spring:message code="showEligibleUsers.title" /></title>
<script
	src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesModule.js" />"></script>
<script
	src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesController.js" />"></script>

</head>
<body ng-app="thShowEligibleTraineesModule"
	ng-controller="thShowEligibleTraineesController as eligibleCtrl">
	<div class="container" id="containerId">
		<h4 class="mainPageTitle">
			<spring:message code="showEligibleUsers.title" />
		</h4>
		<select ng-model="selectedTrainingType"
			ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id"
			name="trainingTypeId" class="form-control selectwidthauto">
			<option value="">--
				<spring:message code="importTraining.trainingType.choose" /> --
			</option>
		</select> <br>
		<nav class="cl-effect-7">
			<a ng-click="getEligibleTrainees(selectedTrainingType)"><spring:message
					code="showEligibleUsers.get.users.link" /></a>
		</nav>
		<br>
		<div class="span12">
			<div ng-show="emailList.length">
				<h3>
					<spring:message code="showEligibleUsers.has.email.table.title" />
				</h3>
				<table class="table table-bordered table-striped">
					<tr>
						<th title="Név"><spring:message code="showEligibleUsers.grid.name" /></th>
						<th title="Email"><spring:message code="showEligibleUsers.grid.email" /></th>
						<th title="phoneNo"><spring:message code="showEligibleUsers.grid.phoneNo" /></th>
					</tr>
					<tr ng-repeat="user in hasEmailUsers">
						<td title="Név">{{user.name}}</td>
						<td title="Email">{{user.email}}</td>
						<td title="phoneNo">{{user.phoneNo}}</td>
					</tr>
				</table>
				<textarea class="form-control custom-textbox" rows="3"
					ng-model="emailList"></textarea>
			</div>
<!-- 							<div class="table table-bordered table-striped" ng-grid="hasEmailUsersGrid"></div> -->

			<div ng-show="onlyPhoneUsers.length">
				<h3>
					<spring:message code="showEligibleUsers.only.phoneno.table.title" />
				</h3>
				<table class="table table-bordered table-striped">
					<tr>
						<th title="Név"><spring:message code="showEligibleUsers.grid.name" /></th>
						<th title="phoneNo"><spring:message code="showEligibleUsers.grid.phoneNo" /></th>
					</tr>
					<tr ng-repeat="user in onlyPhoneUsers">
						<td title="Név">{{user.name}}</td>
						<td title="phoneNo">{{user.phoneNo}}</td>
					</tr>
				</table>
			</div>
			<!-- 				<div class="table table-bordered table-striped" ng-grid="onlyPhoneUsersGrid"></div>     -->
		</div>
	</div>

	<!-- /container -->

	<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
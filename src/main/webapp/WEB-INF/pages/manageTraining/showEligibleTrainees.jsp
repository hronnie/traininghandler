<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title>Meghívók küldése</title>
<script
	src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesModule.js" />"></script>
<script
	src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesController.js" />"></script>

</head>
<body ng-app="thShowEligibleTraineesModule"
	ng-controller="thShowEligibleTraineesController as eligibleCtrl">
	<div class="container" id="containerId">
		<h4 class="mainPageTitle">
			Meghívók küldése
		</h4>
		<select ng-model="selectedTrainingType"
			ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id"
			name="trainingTypeId" class="form-control selectwidthauto">
			<option value="">-- Válassz egyet --
			</option>
		</select> <br>
		<nav class="cl-effect-7">
			<a ng-click="getEligibleTrainees(selectedTrainingType)">Mutasd kik jöhetnek</a>
		</nav>
		<br>
		<div class="span12">
			<div ng-show="isResultEmpty">
				<h3>
					Erre a tanfolyamra nem találtam megfelelő embereket
				</h3>
			</div>
			<div ng-show="emailList.length">
				<h3>
					Email címmel rendelkezők listája
				</h3>
				<table class="table table-bordered table-striped">
					<tr>
						<th title="Név">Név</th>
						<th title="Email">Email</th>
						<th title="phoneNo">Telefonszám</th>
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
					Csak telefonszámmal rendelkezők listája
				</h3>
				<table class="table table-bordered table-striped">
					<tr>
						<th title="Név">Név</th>
						<th title="phoneNo">Telefonszám</th>
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
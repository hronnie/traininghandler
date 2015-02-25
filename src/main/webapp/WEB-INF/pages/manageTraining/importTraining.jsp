<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title><spring:message code="importTraining.main.title" /></title>
<script
	src="<c:url value="/resources/js/application/module/importTraining/thImportTrainingModule.js" />"></script>
<script
	src="<c:url value="/resources/js/application/module/importTraining/thImportTrainingController.js" />"></script>

</head>
<body ng-app="thImportTrainingModule"
	ng-controller="thImportTrainingController as importCtrl">

	<div class="container" id="containerId">

		<form action="<c:url value="/manageTraining/importTraining" />"
			method="post" enctype="multipart/form-data" autocomplete="off"
			name="importForm" class="form-horizontal">
			<h4 class="mainPageTitle">
				<spring:message code="importTraining.main.title" />
			</h4>
			<c:choose>
				<c:when test="${isImportSuccess == 'true'}">
					<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a>
						<spring:message code="importTraining.success.message" />
					</div>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${not empty validationMsg}">
					<div class="alert alert-danger alert-error">
						<a href="#" class="close" data-dismiss="alert">&times;</a>
						<c:out value="${validationMsg}" />
					</div>
				</c:when>
			</c:choose>

			<div class="control-group">
				<label class="col-sm-2 control-label">Tanfolyam:</label>
				<div class="controls">
					<div class="form-inline">
						<select ng-model="selectedTrainingType"
							ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id"
							name="trainingTypeId" class="form-control selectwidthauto">
							<option value="">--
								<spring:message code="importTraining.trainingType.choose" /> --
							</option>
						</select> <br>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="col-sm-2 control-label">Tanfolyam dátuma:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingDateYear" tabindex="1"
							maxlength="4" name="year" class="form-control w-2"
							ng-keyup="jumpToNextFromYear()"
							placeholder="<spring:message code="importTraining.dateinput.year" />">

						<input type="text" ng-model="trainingDateMonth" tabindex="2"
							maxlength="2" name="month" class="form-control w-2"
							ng-keyup="jumpToNextFromMonth()"
							placeholder="<spring:message code="importTraining.dateinput.month" />">

						<input type="text" ng-model="trainingDateDay" tabindex="3"
							maxlength="2" name="day" class="form-control w-2"
							placeholder="<spring:message code="importTraining.dateinput.day" />">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message
						code="importTraining.fileChoose.title" /></label>
				<div class="controls">
					<input type="file" name="importFile"><br />
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-lg active">
				<spring:message code="importTraining.button.import" />
			</button>
		</form>
	</div>
	<!-- /container -->

	<!-- help -->
	<jsp:include page="/WEB-INF/pages/help/gatherTraineeInfoHelp.jsp" />

	<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title>Tanfolyam importálása</title>
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
				Tanfolyam importálása
			</h4>
			<c:choose>
				<c:when test="${isImportSuccess == 'true'}">
					<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a>
						Az importálás sikeres volt a következő excel fájlból:
						<c:out value="${excelFileName}" />
					</div>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${not empty validationMsg}">
					<div class="alert alert-danger alert-error">
						<a href="#" class="close" data-dismiss="alert">&times;</a>
						<c:out value="${validationMsg}" /><br>
						<c:out value="${excelFileName}" />
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
								Kérlek válassz --
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
							placeholder="Év">

						<input type="text" ng-model="trainingDateMonth" tabindex="2"
							maxlength="2" name="month" class="form-control w-2"
							ng-keyup="jumpToNextFromMonth()"
							placeholder="Hónap">

						<input type="text" ng-model="trainingDateDay" tabindex="3"
							maxlength="2" name="day" class="form-control w-2"
							placeholder="Nap">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Excel fájl feltöltése</label>
				<div class="controls">
					<input type="file" name="importFile"><br />
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-lg active">
				Importálás
			</button>
			<div class="control-group excelLink">
			    <a href="<c:url value="/resources/tanfolyam.xlsx" />"> <i class="fa fa-file-excel-o">Minta excel file letöltése</i> </a>
			</div>
		</form>
	</div>
	<!-- /container -->

	<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
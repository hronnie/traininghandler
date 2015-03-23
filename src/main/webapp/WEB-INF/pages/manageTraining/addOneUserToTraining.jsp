<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title>Tanítványok hozzáadása tanfolyamokhoz</title>
<script
	src="<c:url value="/resources/js/application/module/addOneUserToTraining/thAddOneUserToTrainingModule.js" />"></script>
<script
	src="<c:url value="/resources/js/application/module/addOneUserToTraining/thAddOneUserToTrainingController.js" />"></script>

</head>
<body ng-app="thAddOneUserToTrainingModule"
	ng-controller="thAddOneUserToTrainingController as importCtrl">

	<div class="container" id="containerId">
		<form action="#"
			autocomplete="off"
			name="addOneUserToTrainingForm" class="form-horizontal">
			<h4 class="mainPageTitle">
				Tanítványok hozzáadása tanfolyamokhoz
			</h4>
			<div ng-show="isAddOneUserSuccess" class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				<spring:message code="importTraining.success.message" />
			</div>
			<div ng-show="validationMsg" class="alert alert-danger alert-error">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				{{validationMsg}}
			</div>

			<div class="control-group">
				<label class="col-sm-2 control-label">Tanfolyam:</label>
				<div class="controls">
					<div class="form-inline">
						<select ng-model="selectedTrainingType" tabindex="0"
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
							placeholder="<spring:message code="importTraining.dateinput.year" />">

						<input type="text" ng-model="trainingDateMonth" tabindex="2"
							maxlength="2" name="month" class="form-control w-2"
							ng-keyup="jumpToNextFromMonth()"
							placeholder="<spring:message code="importTraining.dateinput.month" />">

						<input type="text" ng-model="trainingDateDay" tabindex="3"
							maxlength="2" name="day" class="form-control w-2"
							ng-keyup="jumpToNextFromDay()"
							placeholder="<spring:message code="importTraining.dateinput.day" />">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Név:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.name" tabindex="4"
							ng-maxlength="100" name="name" class="form-control w-3"
							placeholder="Név" required>
						<span class="control-label validationMsg" ng-show="addOneUserToTrainingForm.name.$error.required && validationNeeded" >Username is required.</span>
					</div>
				</div>
			</div>
<!-- 			TODO: put min, max and required check. put the necessary error messages. handle drop down also. change hard coded messages with spring:code -->
			<div class="control-group">
				<label class="col-sm-2 control-label">Irányítószám:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.postCode" tabindex="5" ng-minlength="3" ng-maxlength="15"
							 name="postCode" class="form-control w-3"
							placeholder="Irányítószám" required>
						<span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.postCode.$error.required ||
						addOneUserToTrainingForm.postCode.$error.minlength || addOneUserToTrainingForm.postCode.$error.maxlength) && validationNeeded" >Username is required.</span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Cím:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.address" tabindex="6"
							maxlength="100" name="address" class="form-control w-3"
							placeholder="Cím">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Telefonszám:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.phoneNo" tabindex="7"
							maxlength="50" name="phoneNo" class="form-control w-3"
							placeholder="Telefonszám">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Email:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.email" tabindex="8"
							maxlength="80" name="email" class="form-control w-3"
							placeholder="Email">
					</div>
				</div>
			</div>

			<button type="button" class="btn btn-primary btn-lg active" ng-click="onSubmit()">
				Hozzáadás
			</button>
			<button type="button" class="btn btn-primary btn-lg active" ng-click="resetFields()">
				Mezők törlése
			</button>

		</form>
	</div>
	<!-- /container -->

	<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
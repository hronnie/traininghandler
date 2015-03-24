<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title><spring:message code="addOneUserToTraining.main.title" /></title>
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
				<spring:message code="addOneUserToTraining.main.title" />
			</h4>
			<div ng-show="isAddOneUserSuccess" class="alert alert-success">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				<spring:message code="addOneUserToTraining.validation.message.success" />
			</div>
			<div ng-show="validationMsg" class="alert alert-danger alert-error">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				{{validationMsg}}
			</div>

			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.trainingtype.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<select ng-model="selectedTrainingType" tabindex="0"
							ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id"
							name="trainingTypeId" class="form-control selectwidthauto" required>
							<option value="">--
								<spring:message code="addOneUserToTraining.training.choose" /> --
							</option>
						</select> <br>
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.trainingTypeId.$error.required) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.trainingtype" /></span>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.trainingtype.date.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingDateYear" tabindex="1"
							ng-minlength="4" ng-maxlength="4" name="year" class="form-control w-2"
							ng-keyup="jumpToNextFromYear()"
							placeholder="<spring:message code="importTraining.dateinput.year" />" required>

						<input type="text" ng-model="trainingDateMonth" tabindex="2"
							ng-minlength="1" ng-maxlength="2" name="month" class="form-control w-2"
							ng-keyup="jumpToNextFromMonth()"
							placeholder="<spring:message code="importTraining.dateinput.month" />" required>

						<input type="text" ng-model="trainingDateDay" tabindex="3"
							ng-minlength="1" ng-maxlength="2 name="day" class="form-control w-2"
							ng-keyup="jumpToNextFromDay()"
							placeholder="<spring:message code="importTraining.dateinput.day" />" required>
						<span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.year.$error.required ||
                        addOneUserToTrainingForm.year.$error.minlength || 
                        addOneUserToTrainingForm.year.$error.maxlength ||
                        addOneUserToTrainingForm.month.$error.required ||
                        addOneUserToTrainingForm.month.$error.minlength || 
                        addOneUserToTrainingForm..month$error.maxlength ||
                        addOneUserToTrainingForm.day.$error.required ||
                        addOneUserToTrainingForm.day.$error.minlength || 
                        addOneUserToTrainingForm.day.$error.maxlength
                        ) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.traniningtype.date" /></span>
							
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.name.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.name" tabindex="4"
							ng-minlength="3" ng-maxlength="100" name="name" class="form-control w-3"
							placeholder="<spring:message code="addOneUserToTraining.name.label" />" required>
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.name.$error.required ||
                        addOneUserToTrainingForm.name.$error.minlength || addOneUserToTrainingForm.name.$error.maxlength) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.name" /> </span>
					</div>
				</div>
			</div>
<!-- 			TODO: put min, max and required check. put the necessary error messages. handle drop down also. change hard coded messages with spring:code -->
			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.postcode.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.postCode" tabindex="5" ng-minlength="3" ng-maxlength="15"
							 name="postCode" class="form-control w-3"
							placeholder="<spring:message code="addOneUserToTraining.postcode.label" />" required>
						<span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.postCode.$error.required ||
						addOneUserToTrainingForm.postCode.$error.minlength || addOneUserToTrainingForm.postCode.$error.maxlength) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.postcode" /></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.address.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.address" tabindex="6"
							ng-minlength="3" ng-maxlength="100" name="address" class="form-control w-3"
							placeholder="<spring:message code="addOneUserToTraining.address.label" />" required>
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.address.$error.required ||
                        addOneUserToTrainingForm.address.$error.minlength || addOneUserToTrainingForm.address.$error.maxlength) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.address" /></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.phonenumber.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.phoneNo" tabindex="7"
							ng-minlength="3" ng-maxlength="50" name="phoneNo" class="form-control w-3"
							placeholder="<spring:message code="addOneUserToTraining.phonenumber.label" />" required>
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.phoneNo.$error.required ||
                        addOneUserToTrainingForm.phoneNo.$error.minlength || addOneUserToTrainingForm.phoneNo.$error.maxlength) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.phone" /></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label"><spring:message code="addOneUserToTraining.email.label" />:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.email" tabindex="8"
							ng-maxlength="80" name="email" class="form-control w-3"
							placeholder="<spring:message code="addOneUserToTraining.email.label" />">
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.email.$error.maxlength) && validationNeeded" ><spring:message code="addOneUserToTraining.validation.email" /></span>
					</div>
				</div>
			</div>

			<button type="button" class="btn btn-primary btn-lg active" ng-click="onSubmit()">
				<spring:message code="addOneUserToTraining.button.add" />
			</button>
			<button type="button" class="btn btn-primary btn-lg active" ng-click="resetFields()">
				<spring:message code="addOneUserToTraining.button.reset" />
			</button>

		</form>
	</div>
	<!-- /container -->

	<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
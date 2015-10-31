<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				Sikeres volt a tanítvány hozzáadása a tanfolyamhoz
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
							name="trainingTypeId" class="form-control selectwidthauto" required>
							<option value="">--
								Kérlek válassz --
							</option>
						</select> <br>
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.trainingTypeId.$error.required) && validationNeeded" >Kérlek válaszd ki a tanfolyamot</span>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="col-sm-2 control-label">Tanfolyam dátuma:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingDateYear" tabindex="1"
							ng-minlength="4" ng-maxlength="4" name="year" class="form-control w-2"
							ng-keyup="jumpToNextFromYear()"
							placeholder="Év" required>

						<input type="text" ng-model="trainingDateMonth" tabindex="2"
							ng-minlength="1" ng-maxlength="2" name="month" class="form-control w-2"
							ng-keyup="jumpToNextFromMonth()"
							placeholder="Hónap" required>

						<input type="text" ng-model="trainingDateDay" tabindex="3"
							ng-minlength="1" ng-maxlength="2" name="day" class="form-control w-2"
							ng-keyup="jumpToNextFromDay()"
							placeholder="Nap" required>
						<span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.year.$error.required ||
                        addOneUserToTrainingForm.year.$error.minlength || 
                        addOneUserToTrainingForm.year.$error.maxlength ||
                        addOneUserToTrainingForm.month.$error.required ||
                        addOneUserToTrainingForm.month.$error.minlength || 
                        addOneUserToTrainingForm.month.$error.maxlength ||
                        addOneUserToTrainingForm.day.$error.required ||
                        addOneUserToTrainingForm.day.$error.minlength || 
                        addOneUserToTrainingForm.day.$error.maxlength
                        ) && validationNeeded" >A tanfolyam dátumának kiválasztása szükséges. Év: 4 karakter. Hónap: 1 vagy 2 karakter. Nap: 1 vagy 2 karakter</span>
							
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Név:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.name" tabindex="4"
							ng-minlength="3" ng-maxlength="100" name="name" class="form-control w-3"
							placeholder="Név" required>
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.name.$error.required ||
                        addOneUserToTrainingForm.name.$error.minlength || addOneUserToTrainingForm.name.$error.maxlength) && validationNeeded" >A név kiválasztása szükséges. Minimum 3 és maximum 100 karakter. </span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Irányítószám:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.postCode" tabindex="5" ng-minlength="3" ng-maxlength="15"
							 name="postCode" class="form-control w-3"
							placeholder="Irányítószám">
						<span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.postCode.$error.required ||
						addOneUserToTrainingForm.postCode.$error.minlength || addOneUserToTrainingForm.postCode.$error.maxlength) && validationNeeded" >Az irányítószám, ha ki van töltve, akkor minimum 3 és maximum 15 karakter lehet.</span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Cím:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.address" tabindex="6"
							ng-minlength="3" ng-maxlength="100" name="address" class="form-control w-3"
							placeholder="Cím">
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.address.$error.required ||
                        addOneUserToTrainingForm.address.$error.minlength || addOneUserToTrainingForm.address.$error.maxlength) && validationNeeded" >A cím, ha ki van töltve, akkor minimum 3 és maximum 100 karakter lehet.</span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Telefonszám:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="text" ng-model="trainingExcelDto.phoneNo" tabindex="7"
							ng-minlength="3" ng-maxlength="50" name="phoneNo" class="form-control w-3"
							placeholder="Telefonszám">
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.phoneNo.$error.required ||
                        addOneUserToTrainingForm.phoneNo.$error.minlength || addOneUserToTrainingForm.phoneNo.$error.maxlength) && validationNeeded" >A telefonszám, ha ki van töltve, akkor minimum 3 és maximum 50 karakter.</span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Email:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="email" ng-model="trainingExcelDto.email" tabindex="8"
							ng-maxlength="80" name="email" class="form-control w-3"
							placeholder="Email">
                        <span class="control-label validationMsg" ng-show="(addOneUserToTrainingForm.email.$error.maxlength || addOneUserToTrainingForm.email.$error.email) && validationNeeded" >Nem érvényes email formátum. Maximum 80 karakter.</span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="col-sm-2 control-label">Az adott tanfolyam előfeltételeinek ellőnőrzésének kihagyása:</label>
				<div class="controls">
					<div class="form-inline">
						<input type="checkbox" ng-model="skipPrereq" tabindex="9"
							name="skipPrereq" class="form-control w-3">
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
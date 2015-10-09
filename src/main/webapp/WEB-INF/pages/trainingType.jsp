<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


	<title>Tanfolyam típusok kezelése</title>
	<script	src="<c:url value="/resources/js/application/module/trainingType/thTrainingTypeModule.js" />"></script>
	<script	src="<c:url value="/resources/js/application/module/trainingType/thTrainingTypeGridController.js" />"></script>


	</head>
	<body ng-app="trainingTypeModule">
		<h4 class="gridMainTitle">Név</h4>
		<div ng-controller="thTrainingTypeController as ttCtrl">
		<div ng-show="isEditSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			A szerkesztés sikeres volt
		</div>
		<div ng-show="isAddSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			A hozzáadás sikeres volt
		</div>
		
			<div class="table-responsive">
				<div class="outerDIV">
					<div class="innerDIV">
						<input class="searchInput" ng-model="search.name" placeholder='Keresés...'> <br>
						<table class="table table-bordered table-hover">
							<thead>
								<tr style="font-weight: bold">
									<th class="gridTitle" style="width: 30%">Név</th>
									<th class="gridTitle" style="width: 10%">Fokozat</th>
									<th class="gridTitle" style="width: 40%">Leírás</th>
									<th class="gridTitle" style="width: 20%">Szerkesztés</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="trainingType in trainingTypes | filter:search:strict" class="gridRow">
									<td>
										<!-- editable trainingTypename (text with validation) --> <span
										class="gridCell" editable-text="trainingType.name"
										e-name="name" e-form="rowform" onbeforesave="validateName($data)"
										onaftersave="saveTrainingType(trainingType)"
										e-placeholder="Név"
										 > {{ trainingType.name }} </span>
									</td>
									<td>
										<!-- editable levelNo (select-local) --> <span
										class="gridCell" editable-text="trainingType.levelNo"
										e-name="levelNo" e-form="rowform" onbeforesave="validateLevelNo($data)" 
										e-placeholder="Fokozat"
										>
										 {{trainingType.levelNo }} </span>
									</td>
									<td>
										<!-- editable description (select-remote) --> <span
										class="gridCell" editable-textarea="trainingType.description"
										e-name="description" e-form="rowform" onbeforesave="validateDescription($data)" 
										e-cols="40" e-rows="3" e-placeholder="Leírás"
										> {{
											trainingType.description }} </span>
									</td>
									<td style="white-space: nowrap">
										<!-- form -->
										<form editable-form 
												name="rowform"
												onbeforesave="savetrainingType($data, trainingType.id)"
												ng-show="rowform.$visible" 
												class="form-buttons form-inline"
												shown="inserted == trainingType">
											<button type="submit" 
													ng-disabled="rowform.$waiting"
													class="btn btn-primary rowButton rowButtonSave"
													title="Mentés">
													<i class="fa fa-floppy-o fa-2x"></i>
											</button>
											<button type="button" 
													ng-disabled="rowform.$waiting"
													ng-click="rowform.$cancel()"
													class="btn btn-default rowButton rowButtonCancel"
													title="Mégse">
													<i class="fa fa-angle-double-left fa-2x"></i>
											</button>
										</form>
										<div class="buttons" ng-show="!rowform.$visible">
											<button class="btn btn-primary rowButton rowButtonEdit"
													ng-click="rowform.$show()"
													title="Szerkesztés">
													<i class="fa fa-pencil-square-o fa-2x"></i>
											</button>
											<!-- $index -->
										</div>
									</td>
								</tr>
							<tbody>
						</table>

					</div>
				</div>
			</div>

			<button class="btn btn-primary addRowButton"
				ng-click="addTrainingType()">Új tanfolyam típus</button>
		
		<div ng-show="isEditSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			A szerkesztés sikeres volt
		</div>
		<div ng-show="isAddSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			A hozzáadás sikeres volt
		</div>
		</div>

		<script
			src="<c:url value="/resources/js/application/common/stickyHeader.js" />"></script>
		<script src="<c:url value="/resources/js/application/lib/jquery.ba-throttle-debounce.min.js" />"></script>
	</body>
	</html>
</div>
<!-- /container -->

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


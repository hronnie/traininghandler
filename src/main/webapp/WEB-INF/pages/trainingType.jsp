<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


	<title><spring:message code="trainingtype.grid.main.title" /></title>
	<script	src="<c:url value="/resources/js/application/module/trainingType/thTrainingTypeModule.js" />"></script>
	<script	src="<c:url value="/resources/js/application/module/trainingType/thTrainingTypeGridController.js" />"></script>


	</head>
	<body ng-app="trainingTypeModule">
		<h4 class="gridMainTitle"><spring:message code="trainingtype.grid.main.title" /></h4>
		<div ng-controller="thTrainingTypeController as ttCtrl">
			<div class="table-responsive">
				<div class="outerDIV">
					<div class="innerDIV">
						<input class="searchInput" ng-model="search.$" placeholder='<spring:message code="grid.general.search.input.placeholder" />'> <br>
						<table class="table table-bordered table-hover">
							<thead>
								<tr style="font-weight: bold">
									<th class="gridTitle" ><spring:message code="trainingtype.grid.header.name" /></th>
									<th class="gridTitle"><spring:message code="trainingtype.grid.header.levelNo" /></th>
									<th class="gridTitle"><spring:message code="trainingtype.grid.header.description" /></th>
									<th class="gridTitle"><spring:message code="grid.general.header.edit" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="trainingType in trainingTypes | filter:search:strict" class="gridRow">
									<td>
										<!-- editable trainingTypename (text with validation) --> <span
										class="gridCell" editable-text="trainingType.name"
										e-name="name" e-form="rowform" onbeforesave="validateName($data)"
										onaftersave="saveTrainingType(trainingType)"
										e-placeholder="<spring:message code="trainingtype.grid.header.name" />"
										 > {{ trainingType.name }} </span>
									</td>
									<td>
										<!-- editable levelNo (select-local) --> <span
										class="gridCell" editable-text="trainingType.levelNo"
										e-name="levelNo" e-form="rowform" onbeforesave="validateLevelNo($data)" 
										e-placeholder="<spring:message code="trainingtype.grid.header.levelNo" />"
										>
										 {{trainingType.levelNo }} </span>
									</td>
									<td>
										<!-- editable description (select-remote) --> <span
										class="gridCell" editable-textarea="trainingType.description"
										e-name="description" e-form="rowform" onbeforesave="validateDescription($data)" 
										e-cols="40" e-rows="3" e-placeholder="<spring:message code="trainingtype.grid.header.description" />"
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
													title="<spring:message code="grid.general.button.save" />">
													<i class="fa fa-floppy-o fa-2x"></i>
											</button>
											<button type="button" 
													ng-disabled="rowform.$waiting"
													ng-click="rowform.$cancel()"
													class="btn btn-default rowButton rowButtonCancel"
													title="<spring:message code="grid.general.button.cancel" />">
													<i class="fa fa-angle-double-left fa-2x"></i>
											</button>
										</form>
										<div class="buttons" ng-show="!rowform.$visible">
											<button class="btn btn-primary rowButton rowButtonEdit"
													ng-click="rowform.$show()"
													title="<spring:message code="grid.general.button.edit" />">
													<i class="fa fa-pencil-square-o fa-2x"></i>
											</button>
											<button class="btn btn-danger rowButton rowButtonDelete"
													ng-click="removeTrainingType($index, trainingType.trainingTypeId)" 
													title="<spring:message code="grid.general.button.delete" />">
													<i class="fa fa-times fa-2x"></i>
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
				ng-click="addTrainingType()"><spring:message code="grid.general.button.add.row" /></button>
		</div>

		<script
			src="<c:url value="/resources/js/application/common/stickyHeader.js" />"></script>
		<script
			src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
	</body>
	</html>
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/trainingTypeHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


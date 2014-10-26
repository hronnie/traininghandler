<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


	<title>Training Types</title>
	<script
		src="<c:url value="/resources/js/module/trainingType/thTrainingTypeGridController.js" />"></script>


	</head>
	<body>
		<h4 class="gridMainTitle"><spring:message code="trainingtype.grid.main.title" /></h4>
		<div ng-app="app" ng-controller="thTrainingTypeController as ttCtrl">
			<div class="table-responsive">
				<div class="outerDIV">
					<div class="innerDIV">

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
								<tr ng-repeat="trainingType in trainingTypes">
									<td>
										<!-- editable trainingTypename (text with validation) --> <span
										class="gridCell" editable-text="trainingType.name"
										e-name="name" e-form="rowform" onbeforesave="checkName($data)"
										e-ng-minlength="3"> {{ trainingType.name }} </span>
									</td>
									<td>
										<!-- editable levelNo (select-local) --> <span
										class="gridCell" editable-text="trainingType.levelNo"
										e-name="levelNo" e-form="rowform" required> {{
											trainingType.levelNo }} </span>
									</td>
									<td>
										<!-- editable description (select-remote) --> <span
										class="gridCell" editable-text="trainingType.description"
										e-name="description" e-form="rowform" required> {{
											trainingType.description }} </span>
									</td>
									<td style="white-space: nowrap">
										<!-- form -->
										<form editable-form name="rowform"
											onbeforesave="savetrainingType($data, trainingType.id)"
											ng-show="rowform.$visible" class="form-buttons form-inline"
											shown="inserted == trainingType">
											<button type="submit" ng-disabled="rowform.$waiting"
												class="btn btn-primary rowButton rowButtonSave"><spring:message code="grid.general.button.save" /></button>
											<button type="button" ng-disabled="rowform.$waiting"
												ng-click="rowform.$cancel()"
												class="btn btn-default rowButton rowButtonCancel"><spring:message code="grid.general.button.cancel" /></button>
										</form>
										<div class="buttons" ng-show="!rowform.$visible">
											<button class="btn btn-primary rowButton rowButtonEdit"
												ng-click="rowform.$show()"><spring:message code="grid.general.button.edit" /></button>
											<button class="btn btn-danger rowButton rowButtonDelete"
												ng-click="removetrainingType($index, trainingType.id)"><spring:message code="grid.general.button.delete" /></button>
											<!-- $index -->
										</div>
									</td>
								</tr>
							<tbody>
						</table>

					</div>
				</div>
			</div>

			<button class="btn btn-default addRowButton"
				ng-click="addtrainingType()"><spring:message code="grid.general.button.add.row" /></button>
		</div>

		<script
			src="<c:url value="/resources/js/module/common/stickyHeader.js" />"></script>
		<script
			src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
	</body>
	</html>
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/indexHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


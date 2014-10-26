<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


<title>Training Types</title>
<script src="<c:url value="/resources/js/module/trainingType/thTrainingTypeGridController.js" />"></script>


</head>
<body>
	<h4 class="gridMainTitle">Manage Training types</h4>
	<div ng-app="app" ng-controller="thTrainingTypeController as ttCtrl">

		<div class="outerDIV">
			<div class="innerDIV">

				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<tr style="font-weight: bold">
							<th style="width: 35%" class="gridTitle">Name</th>
							<th style="width: 20%" class="gridTitle">Level Number</th>
							<th style="width: 20%" class="gridTitle">Description</th>
							<th style="width: 25%" class="gridTitle">Edit</th>
						</tr>
					 </thead>
					 <tbody>
						<tr ng-repeat="trainingType in trainingTypes">
							<td>
								<!-- editable trainingTypename (text with validation) --> <span
								class="gridCell" editable-text="trainingType.name" e-name="name"
								e-form="rowform" onbeforesave="checkName($data)"
								e-ng-minlength="3"> {{ trainingType.name }} </span>
							</td>
							<td>
								<!-- editable levelNo (select-local) --> <span class="gridCell"
								editable-text="trainingType.levelNo" e-name="levelNo"
								e-form="rowform" required> {{ trainingType.levelNo }} </span>
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
										class="btn btn-primary rowButton">save</button>
									<button type="button" ng-disabled="rowform.$waiting"
										ng-click="rowform.$cancel()" class="btn btn-default rowButton">
										cancel</button>
								</form>
								<div class="buttons" ng-show="!rowform.$visible">
									<button class="btn btn-primary rowButton"
										ng-click="rowform.$show()">edit</button>
									<button class="btn btn-danger rowButton"
										ng-click="removetrainingType($index, trainingType.id)">del</button>
									<!-- $index -->
								</div>
							</td>
						</tr>
					<tbody>
				</table>

			</div>
		</div>
		<button class="btn btn-default addRowButton"
			ng-click="addtrainingType()">Add row</button>
	</div>

<script src="<c:url value="/resources/js/module/common/stickyHeader.js" />"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>
</body>
	</html>
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/indexHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


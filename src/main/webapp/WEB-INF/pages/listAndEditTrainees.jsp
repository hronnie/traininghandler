<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


	<title><spring:message code="listAndEditTrainees.main.title" /></title>
	<script	src="<c:url value="/resources/js/application/module/listAndEditTrainees/thListAndEditTraineesModule.js" />"></script>
	<script	src="<c:url value="/resources/js/application/module/listAndEditTrainees/thListAndEditTraineesController.js" />"></script>


	</head>
	<body ng-app="listAndEditTraineesModule">
		<h4 class="gridMainTitle"><spring:message code="listAndEditTrainees.main.title" /></h4>
		<div ng-controller="listAndEditTraineesController as laetCtrl">
		<div ng-show="isEditSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<spring:message code="listAndEditTrainees.edit.success.message" />
		</div>
		<div ng-show="isRemoveSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<spring:message code="listAndEditTrainees.remove.success.message" />
		</div>
		
		
			<div class="table-responsive">
				<div class="outerDIV">
					<div class="innerDIV">
						<input class="searchInput" ng-model="search.name" placeholder='<spring:message code="grid.general.search.input.placeholder" />'> <br>
						<table class="table table-bordered table-hover">
							<thead>
								<tr style="font-weight: bold">
									<th class="gridTitle" style="width: 20%"><spring:message code="listAndEditTrainees.gridHeader.name" /></th>
									<th class="gridTitle" style="width: 10%"><spring:message code="listAndEditTrainees.gridHeader.postCode" /></th>
									<th class="gridTitle" style="width: 30%"><spring:message code="listAndEditTrainees.gridHeader.fullAddress" /></th>
									<th class="gridTitle" style="width: 15%"><spring:message code="listAndEditTrainees.gridHeader.phone" /></th>
									<th class="gridTitle" style="width: 15%"><spring:message code="listAndEditTrainees.gridHeader.email" /></th>
									<th class="gridTitle" style="width: 10%"><spring:message code="listAndEditTrainees.gridHeader.listOfCompletedTrainings" /></th>
									<th class="gridTitle" style="width: 20%"><spring:message code="grid.general.header.edit" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="trainee in filteredTrainees | filter:search:strict" class="gridRow">
									<td>
										<span
										class="gridCell" editable-text="trainee.name"
										e-name="name" e-form="rowform" onbeforesave="validateName($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="<spring:message code="listAndEditTrainees.grid.name" />"
										 > {{ trainee.name }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.postCode"
										e-name="postCode" e-form="rowform" onbeforesave="validatePostCode($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="<spring:message code="listAndEditTrainees.grid.postCode" />"
										 > {{ trainee.postCode }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.address"
										e-name="fullAddress" e-form="rowform" onbeforesave="validateFullAddress($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="<spring:message code="listAndEditTrainees.grid.fullAddress" />"
										 > {{ trainee.address }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.phone"
										e-name="phone" e-form="rowform" onbeforesave="validatePhone($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="<spring:message code="listAndEditTrainees.grid.phone" />"
										 > {{ trainee.phone }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.email"
										e-name="email" e-form="rowform" onbeforesave="validateEmail($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="<spring:message code="listAndEditTrainees.grid.email" />"
										 > {{ trainee.email }} </span>
									</td>
									<td>
										 <span class="gridCell"> {{ trainee.completedTrainings ?  trainee.completedTrainings : '<spring:message code="grid.general.content.none" />'}} </span>
									</td>
									<td style="white-space: nowrap">
										<!-- form -->
										<form editable-form 
												name="rowform"
												onbeforesave="saveTrainee($data)"
												ng-show="rowform.$visible" 
												class="form-buttons form-inline"
												shown="inserted == trainee">
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
													ng-click="removeTrainee($index, trainee)" 
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
		<div align = "center">
			<pagination 
	  			ng-model="currentPage"
				  total-items="traineeList.length"
				  max-size="maxSize"  
				  data-num-pages="noOfPages"
				  boundary-links="true"
				  class="pagination-sm nomargin" 
				  previous-text="<spring:message 
				  code="grid.general.pager.previous" />" 
				  next-text="<spring:message code="grid.general.pager.next" />" 
				  first-text="<spring:message code="grid.general.pager.first" />" 
				  last-text="<spring:message code="grid.general.pager.last" />">
			</pagination>
		</div>	
		<div ng-show="isEditSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<spring:message code="listAndEditTrainees.edit.success.message" />
		</div>
		<div ng-show="isRemoveSuccess" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<spring:message code="listAndEditTrainees.remove.success.message" />
		</div>
		</div>
<!--   		<pagination boundary-links="true" num-pages="noOfPages" total-items="totalItems" ng-model="currentPage" items-per-page="4" class="pagination-sm nomargin" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"></pagination> -->

<%-- 		<script src="<c:url value="/resources/js/application/common/stickyHeader.js" />"></script> --%>
<%-- 		<script src="<c:url value="/resources/js/application/lib/jquery.ba-throttle-debounce.min.js" />"></script> --%>
	</body>
	</html>
</div>
<!-- /container -->

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


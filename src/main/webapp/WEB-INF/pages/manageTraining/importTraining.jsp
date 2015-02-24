<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
    <title><spring:message code="importTraining.main.title" /></title>
	    <script src="<c:url value="/resources/js/application/module/importTraining/thImportTrainingModule.js" />"></script>
	    <script src="<c:url value="/resources/js/application/module/importTraining/thImportTrainingController.js" />"></script>
    
    </head>
    <body ng-app="thImportTrainingModule" ng-controller="thImportTrainingController as importCtrl">
    <h4 class="gridMainTitle"><spring:message code="importTraining.main.title" /></h4>
    
		<div class="container" id="containerId">
 			<form action="<c:url value="/manageTraining/importTraining" />" method="post" enctype="multipart/form-data" autocomplete="off" name="importForm">
 				<select ng-model="selectedTrainingType" 
 					ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id" 
 					name="trainingTypeId"
 					class="form-control">
      				<option value="">-- <spring:message code="importTraining.trainingType.choose" /> --</option>
 				</select>
 				<br>
 				<div class="row">
				  <div class="col-xs-3">
	 				<input type="text" ng-model="trainingDateYear" tabindex="1" maxlength="4" name="year" class="form-control dateCustomStyleLong" ng-keyup="jumpToNextFromYear()" placeholder="<spring:message code="importTraining.dateinput.year" />">
				  </div>
				  <div class="col-xs-2">
	 				<input type="text" ng-model="trainingDateMonth" tabindex="2" maxlength="2" name="month" class="form-control dateCustomStyleShort" ng-keyup="jumpToNextFromMonth()"  placeholder="<spring:message code="importTraining.dateinput.month" />">
				  </div>
				  <div class="col-xs-2">
	 				<input type="text" ng-model="trainingDateDay" tabindex="3" maxlength="2" name="day" class="form-control dateCustomStyleShort" placeholder="<spring:message code="importTraining.dateinput.day" />"><br>
				  </div>
				</div>
				<div class="form-group">
 				   <spring:message code="importTraining.fileChoose.title" /> <input type="file" name="importFile"><br /> 
 				</div>
 				<button type="submit" class="btn btn-primary btn-lg active"><spring:message code="importTraining.button.import" /></button>
			</form>
		</div>
		
		<div class="errorMsg">
			<c:out value="${validationMsg}" />
		</div>
		<div class="successMsg">
		<c:choose>
		    <c:when test="${isImportSuccess == 'true'}">
		      <spring:message code="importTraining.success.message" />
		    </c:when>
		</c:choose> 
		</div>

<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/gatherTraineeInfoHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
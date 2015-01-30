<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
    <title><spring:message code="importTraining.main.title" /></title>
	    <script src="<c:url value="/resources/js/application/module/importTraining/thImportTrainingModule.js" />"></script>
	    <script src="<c:url value="/resources/js/application/module/importTraining/thImportTrainingController.js" />"></script>
    
    </head>
    <body ng-app="thImportTrainingModule" ng-controller="thImportTrainingController as importCtrl">
    
		<div class="container" id="containerId">
 			<form action="<c:url value="/manageTraining/importTraining" />" method="post" enctype="multipart/form-data" autocomplete="off">
 				<select ng-model="selectedTrainingType" 
 					ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id" 
 					name="trainingTypeId">
      				<option value="">-- <spring:message code="importTraining.trainingType.choose" /> --</option>
 				</select>
 				<br>
 				<input type="text" ng-model="trainingDateYear" name="year">/
 				<input type="text" ng-model="trainingDateMonth" name="month">/
 				<input type="text" ng-model="trainingDateDay" name="day"><br>
 				
 				<spring:message code="importTraining.fileChoose.title" /> <input type="file" name="importFile"><br /> 
 				
 				<button type="submit"><spring:message code="importTraining.button.import" /></button>
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
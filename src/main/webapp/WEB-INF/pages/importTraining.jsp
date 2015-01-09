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
 <select ng-model="selectedTrainingType" ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray">
      <option value="">-- <spring:message code="importTraining.trainingType.choose" /> --</option>
 </select>
 <br>
 
 ***<input type="text" ng-model="trainingDateYear">/<input type="text" ng-model="trainingDateMonth">/<input type="text" ng-model="trainingDateDay"> 
</div>



<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/gatherTraineeInfoHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
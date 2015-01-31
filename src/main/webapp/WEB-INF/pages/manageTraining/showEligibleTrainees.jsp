<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
    <title><spring:message code="importTraining.main.title" /></title>
        <script src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesModule.js" />"></script>
        <script src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesController.js" />"></script>
    
    </head>
    <body ng-app="thShowEligibleTraineesModule" ng-controller="thShowEligibleTraineesController as eligibleCtrl">
    
        <div class="container" id="containerId">
                <select ng-model="selectedTrainingType" 
                    ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id" 
                    name="trainingTypeId">
                    <option value="">-- <spring:message code="importTraining.trainingType.choose" /> --</option>
                </select>
                <br>
                
                
             <button class="btn btn-1 btn-1e mainButton" id="trainingTypeId" ng-click="" />'">
                <spring:message code="index.button.manage.training.types" />
            </button>
                
                
        </div>
        

<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/gatherTraineeInfoHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
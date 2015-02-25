<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
    <title><spring:message code="showEligibleUsers.title" /></title>
        <script src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesModule.js" />"></script>
        <script src="<c:url value="/resources/js/application/module/showEligibleTrainees/thShowEligibleTraineesController.js" />"></script>
    
    </head>
    <body ng-app="thShowEligibleTraineesModule" ng-controller="thShowEligibleTraineesController as eligibleCtrl">
    <h4 class="gridMainTitle"><spring:message code="showEligibleUsers.title" /></h4>
        <div class="container" id="containerId">
	        <select ng-model="selectedTrainingType" 
	            ng-options="trainingTypeWrapper.name for trainingTypeWrapper in trainingTypeWrapperArray track by trainingTypeWrapper.id" 
	            name="trainingTypeId"
	            class="form-control">
	            <option value="">-- <spring:message code="importTraining.trainingType.choose" /> --</option>
	        </select>
	        <br>
	        <nav class="cl-effect-7">
	           <a ng-click="getEligibleTrainees(selectedTrainingType)"><spring:message code="showEligibleUsers.get.users.link" /></a>
	        </nav>
	        <br>
	        <div class="span12">        
		        <h3><spring:message code="showEligibleUsers.has.email.table.title" /></h3>
		            <table class="table table-bordered table-striped" >       
			            <tr ng-repeat="user in hasEmailUsers">
		                    <td title="Név">{{user.name}}</td>
		                    <td title="Email">{{user.email}}</td>
		                    <td title="phoneNo">{{user.phoneNo}}</td>                                       
	                    </tr> 
	                </table>
	                
	                <textarea class="form-control" rows="3"></textarea>
<!-- 				<div class="table table-bordered table-striped" ng-grid="hasEmailUsersGrid"></div> -->
		  
		        <h3><spring:message code="showEligibleUsers.only.phoneno.table.title" /></h3>
		        	<table class="table table-bordered table-striped" >       
			            <tr ng-repeat="user in onlyPhoneUsers">
		                    <td title="Név">{{user.name}}</td>
		                    <td title="phoneNo">{{user.phoneNo}}</td>                                       
	                    </tr> 
	                </table>
<!-- 				<div class="table table-bordered table-striped" ng-grid="onlyPhoneUsersGrid"></div>     -->
	        </div>
        </div>
        
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/gatherTraineeInfoHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
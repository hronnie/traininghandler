<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


    <title><spring:message code="gatherTraineeInfo.main.title" /></title>
    <script src="<c:url value="/resources/js/application/module/gatherTraineeInfo/thGatherTraineeInfoModule.js" />"></script>
    <script src="<c:url value="/resources/js/application/module/gatherTraineeInfo/thGatherTraineeInfoController.js" />"></script>
	
	
    </head>
    <body ng-app="thGatherTraineeInfoModule" ng-controller="thGatherTraineeInfoController as infoCtrl">
    <h4 class="gridMainTitle"><spring:message code="gatherTraineeInfo.main.title" /></h4>
    <form>
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.name.last" />'><br />
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.name.first" />'><br />
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.displayName" />'><br />
    	<input type="email" placeholder='<spring:message code="gatherTraineeInfo.personal.data.email" />'><br />
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.street" />'><br />
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.houseNo" />'><br />
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.city" />'><br />
    	<input type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.zipCode" />'><br />
    	<input type="tel" placeholder='<spring:message code="gatherTraineeInfo.personal.data.mobileNo" />'><br />
    	<input type="date" placeholder='<spring:message code="gatherTraineeInfo.personal.data.dob" />'><br />
    	<table>
    	   <tr ng-repeat="trainingTypeWrapper in trainingTypeWrapperArray">
    	       <td>{{trainingTypeWrapper.id}} - </td>
    	       <td>{{trainingTypeWrapper.name}} - </td>
    	       <td>{{trainingTypeWrapper.levelNo}} - </td>
    	       <td>
			        <div class="col-md-6">
			            <p class="input-group">
			              <input type="text" class="form-control" datepicker-popup="dd-MMMM-yyyy" ng-model="trainingTypeWrapper.completedDate" is-open="opened" min-date="minDate" max-date="'2015-06-22'" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
			              <span class="input-group-btn">
			                <button type="button" class="btn btn-default" ng-click="open($event)"><i class="glyphicon glyphicon-calendar"></i></button>
			              </span>
			            </p>
			        </div>
    	       
    	       
    	       
    	       {{trainingTypeWrapper.completedDate}}</td>
    	   </tr>
    	</table>
    	
    </form>
    </body>
    </html>
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/trainingTypeHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


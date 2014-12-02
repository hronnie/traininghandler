<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


    <title><spring:message code="gatherTraineeInfo.main.title" /></title>
    <script src="<c:url value="/resources/js/application/module/gatherTraineeInfo/thGatherTraineeInfoModule.js" />"></script>
    <script src="<c:url value="/resources/js/application/module/gatherTraineeInfo/thGatherTraineeInfoController.js" />"></script>
	
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body ng-app="thGatherTraineeInfoModule" ng-controller="thGatherTraineeInfoController as infoCtrl">
          <div id="register" class="animate form">
              <form action="/trainingType" method="post" class="me-select"> 
              <h4 class="gridMainTitle"><spring:message code="gatherTraineeInfo.main.title" /></h4>
              <h4 class="gridSubTitle"><spring:message code="gatherTraineeInfo.sub.personal.data.title" /></h4>
              <section class="ss-style-dots">
                  <p> 
                      <label for="lastName" class="uname" data-icon="u"><spring:message code="gatherTraineeInfo.personal.data.name.last" /></label><br>
                      <input id="lastName" class="gridInputPersonalData" name="lastName" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.name.last" />' />
                  </p>
                  <p> 
                      <label for="firstName" class="uname" data-icon="u"><spring:message code="gatherTraineeInfo.personal.data.name.first" /></label><br>
                      <input id="firstName" class="gridInputPersonalData" name="firstName" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.name.first" />' />
                  </p>
                  <p> 
                      <label for="displayName" class="uname" data-icon="u"><spring:message code="gatherTraineeInfo.personal.data.displayName" /></label><br>
                      <input id="displayName" class="gridInputPersonalData" name="displayName" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.displayName" />' />
                  </p>
                  <p> 
                      <label for="dob" class="uname" data-icon="u"><spring:message code="gatherTraineeInfo.personal.data.dob" /></label><br>
						     <input type="text" class="gridInputPersonalData" readonly
						      id="dob" name="dob" required="required" ng-model="dob" style="width: 150px" 
						      datepicker-popup="yyyy/MM/dd" is-open="isDobOpen" placeholder='<spring:message code="gatherTraineeInfo.personal.data.dob" />' />
						        <button type="button" class="btn btn-default" ng-click="openDob($event)"><i class="glyphicon glyphicon-calendar"></i></button>

                  </p><br>
                  <p> 
                      <label for="zipCode" class="uname" data-icon="e"><spring:message code="gatherTraineeInfo.personal.data.address.zipCode" /></label><br>
                      <input id="zipCode" class="gridInputPersonalData" name="zipCode" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.zipCode" />' />
                  </p>
                  <p> 
                      <label for="city" class="uname" data-icon="e"><spring:message code="gatherTraineeInfo.personal.data.address.city" /></label><br>
                      <input id="city" class="gridInputPersonalData" name="city" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.city" />' />
                  </p>
                  <p> 
                      <label for="street" class="uname" data-icon="e"><spring:message code="gatherTraineeInfo.personal.data.address.street" /></label><br>
                      <input id="street" class="gridInputPersonalData" name="street" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.street" />' />
                  </p>
                  <p> 
                      <label for="houseNo" class="uname" data-icon="e"><spring:message code="gatherTraineeInfo.personal.data.address.houseNo" /></label><br>
                      <input id="houseNo" class="gridInputPersonalData" name="houseNo" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.address.houseNo" />' />
                  </p>
                  <p> 
                      <label for="mobileNo" class="uname" data-icon="u"><spring:message code="gatherTraineeInfo.personal.data.mobileNo" /></label><br>
                      <input id="mobileNo" class="gridInputPersonalData" name="mobileNo" required="required" type="text" placeholder='<spring:message code="gatherTraineeInfo.personal.data.mobileNo" />' />
                  </p>
                  <p> 
                      <label for="email" class="uname" data-icon="e"><spring:message code="gatherTraineeInfo.personal.data.email" /></label><br>
                      <input id="email" class="gridInputPersonalData" name="email" required="required" type="email" placeholder='<spring:message code="gatherTraineeInfo.personal.data.email" />' />
                  </p>
                  <h4 class="gridExplanation"><spring:message code="gatherTraineeInfo.sub.personal.data.next" /></h4>
                  </section>
                  <h4 class="gridSubTitle"><spring:message code="gatherTraineeInfo.sub.training.data.title" /></h4>
                  <h4 class="gridExplanation"><spring:message code="gatherTraineeInfo.sub.training.data.explanation" /></h4>

					<table class="table table-bordered table-hover">
						<thead>
							<tr style="font-weight: bold">
								<th class="gridTitle"  style="width: 40%" ><spring:message code="gatherTraineeInfo.grid.header.name" /></th>
								<th class="gridTitle"  style="width: 20%" ><spring:message code="gatherTraineeInfo.grid.header.levelNo" /></th>
								<th class="gridTitle"  style="width: 40%" ><spring:message code="gatherTraineeInfo.grid.header.date" /></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="trainingTypeWrapper in trainingTypeWrapperArray" class="gridRow" align="center">
								<td>
									<span class="gridCell"> {{trainingTypeWrapper.name}} </span>
								</td>
								<td>
									<span class="gridCell"> {{trainingTypeWrapper.levelNo}} </span>
								</td>
								<td>
							    <input class="gridInputTrainingData" type="text" readonly
							              datepicker-popup="yyyy/MM/dd" ng-model="trainingTypeWrapper.completedDate"
							              is-open="trainingTypeWrapper.isOpen" placeholder='<spring:message code="gatherTraineeInfo.grid.header.date" />' />
							                <button type="button" class="btn btn-default" ng-click="open($event, trainingTypeWrapper)"><i class="glyphicon glyphicon-calendar"></i></button>
								</td>
							</tr>
						<tbody>
					</table>
					<button class="btn btn-primary" style="padding: 25px 80px"><spring:message code="gatherTraineeInfo.grid.submit.button.title" /></button>
 				</form>
          </div>
						
    </body>
    </html>
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/trainingTypeHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


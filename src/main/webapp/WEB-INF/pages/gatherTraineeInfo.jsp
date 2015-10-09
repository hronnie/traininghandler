<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


    <title>Tanfolyamok adatainak es szemelyes adatok bevitele</title>
    <script src="<c:url value="/resources/js/application/module/gatherTraineeInfo/thGatherTraineeInfoModule.js" />"></script>
    <script src="<c:url value="/resources/js/application/module/gatherTraineeInfo/thGatherTraineeInfoController.js" />"></script>
	
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body ng-app="thGatherTraineeInfoModule" ng-controller="thGatherTraineeInfoController as infoCtrl">
          <div id="register" class="animate form">
              <form  method="post" class="me-select"> 
              <h4 class="gridMainTitle">Tanfolyamok adatainak es szemelyes adatok bevitele</h4>
              <h4 class="gridSubTitle">Személyes adatok</h4>
              <section class="ss-style-dots">
                  <p> 
                      <label for="lastName" class="uname" data-icon="u">Család név</label><br>
                      <input id="lastName" ng-model="traineeInfoDto.user.lastName" class="gridInputPersonalData" name="lastName" required="required" type="text" placeholder='Család név' />
                  </p>
                  <p> 
                      <label for="firstName" class="uname" data-icon="u">Keresztnév</label><br>
                      <input id="firstName" ng-model="traineeInfoDto.user.firstName" class="gridInputPersonalData" name="firstName" required="required" type="text" placeholder='Keresztnév' />
                  </p>
                  <p> 
                      <label for="displayName" class="uname" data-icon="u">Megszólítás</label><br>
                      <input id="displayName" ng-model="traineeInfoDto.user.displayName" class="gridInputPersonalData" name="displayName" required="required" type="text" placeholder='Megszólítás' />
                  </p>
                  <p> 
                      <label for="dob" class="uname" data-icon="u">Születési idő</label><br>
						     <input type="text" class="gridInputPersonalData" readonly
						     title='Kattints a mellette l\u00E9v\u0151 gombra, ahol lehet\u0151s\u00E9g van t\u00F6rl\u00E9sre is'
						      id="dob" name="dob" required="required"  ng-model="traineeInfoDto.user.dob" style="width: 150px" 
						      datepicker-popup="yyyy/MM/dd" is-open="isDobOpen" placeholder='Születési idő' />
						        <button type="button" class="btn btn-default" ng-click="openDob($event)"><i class="glyphicon glyphicon-calendar"></i></button>

                  </p><br>
                  <p> 
                      <label for="zipCode" class="uname" data-icon="e">Irányítószám</label><br>
                      <input id="zipCode" ng-model="traineeInfoDto.address.postCode" class="gridInputPersonalData" name="zipCode" required="required" type="text" placeholder='Irányítószám' />
                  </p>
                  <p> 
                      <label for="city" class="uname" data-icon="e">Város</label><br>
                      <input id="city" ng-model="traineeInfoDto.address.city" class="gridInputPersonalData" name="city" required="required" type="text" placeholder='Város' />
                  </p>
                  <p> 
                      <label for="street" class="uname" data-icon="e">Utca</label><br>
                      <input id="street" ng-model="traineeInfoDto.address.street" class="gridInputPersonalData" name="street" required="required" type="text" placeholder='Utca' />
                  </p>
                  <p> 
                      <label for="houseNo" class="uname" data-icon="e">Házszám</label><br>
                      <input id="houseNo" ng-model="traineeInfoDto.address.houseNo" class="gridInputPersonalData" name="houseNo" required="required" type="text" placeholder='Házszám' />
                  </p>
                  <p> 
                      <label for="country" class="uname" data-icon="e">Ország</label><br>
                      <input id="country" ng-model="traineeInfoDto.address.country" class="gridInputPersonalData" name="country" required="required" type="text" placeholder='Ország' />
                  </p>
                  <p> 
                      <label for="mobileNo" class="uname" data-icon="u">Telefonszám</label><br>
                      <input id="mobileNo" ng-model="traineeInfoDto.user.phoneNo" class="gridInputPersonalData" name="mobileNo" required="required" type="text" placeholder='Telefonszám' />
                  </p>
                  <p> 
                      <label for="email" class="uname" data-icon="e">Email</label><br>
                      <input id="email" ng-model="traineeInfoDto.user.email" class="gridInputPersonalData" name="email" required="required" type="email" placeholder='Email' />
                  </p>
                  <h4 class="gridExplanation">Köszönöm a személyes adatokat, és már csak a tanfolyamokat kell megadni. </h4>
                  </section>
                  <h4 class="gridSubTitle">Elvégzett tanfolyamok</h4>
                  <h4 class="gridExplanation">Kérlek add meg az elvégzett tanfolyamok dátumát, a többit pedig hagyd üresen</h4>

					<table class="table table-bordered table-hover">
						<thead>
							<tr style="font-weight: bold">
								<th class="gridTitle"  style="width: 40%" >Név</th>
								<th class="gridTitle"  style="width: 20%" >Fokozat</th>
								<th class="gridTitle"  style="width: 40%" >Mikor végezted?</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="trainingTypeWrapper in trainingTypeWrapperArray" class="gridRow" align="center">
								<td>
									<span class="gridCell"> {{trainingTypeWrapper.name}} </span>
									<input ng-show="false">
								</td>
								<td>
									<span class="gridCell"> {{trainingTypeWrapper.levelNo}} </span>
								</td>
								<td>
							    <input class="gridInputTrainingData" type="text" readonly
							    		  title='Kattints a mellette l\u00E9v\u0151 gombra, ahol lehet\u0151s\u00E9g van t\u00F6rl\u00E9sre is'
							    		  datepicker-popup="yyyy/MM/dd" ng-model="trainingTypeWrapper.completedDate"
							              is-open="trainingTypeWrapper.isOpen" placeholder='Mikor végezted?' />
							                <button type="button" class="btn btn-default" ng-click="open($event, trainingTypeWrapper)"><i class="glyphicon glyphicon-calendar"></i></button>
								</td>
							</tr>
						<tbody>
					</table>
					<button class="btn btn-primary" style="padding: 25px 80px" ng-click="saveTraineeData()">Küldés</button>
 				</form>
          </div>
						
    </body>
    </html>
</div>
<!-- /container -->

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


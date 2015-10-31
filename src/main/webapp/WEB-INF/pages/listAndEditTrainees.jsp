<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


	<title>Tanítványok listázása és szerkesztése</title>
	<script	src="<c:url value="/resources/js/application/module/listAndEditTrainees/thListAndEditTraineesModule.js" />"></script>
	<script	src="<c:url value="/resources/js/application/module/listAndEditTrainees/thListAndEditTraineesController.js" />"></script>


	</head>
	<body ng-app="listAndEditTraineesModule">
		<h4 class="gridMainTitle">Tanítványok listázása és szerkesztése</h4>
		<div ng-controller="listAndEditTraineesController as laetCtrl">
			<div class="alert alert-info" data-ng-show="!currentPageTraineeList.length">
				Jelenleg nincs tanítvány az adatbázisban, amit szerkeszteni tudnál!			
			</div>
		
			<div class="table-responsive" data-ng-show="currentPageTraineeList.length">
				<div ng-show="isEditSuccess" class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert">&times;</a>
					A tanítvány szerkesztése sikeres volt!
				</div>
				<div ng-show="isRemoveSuccess" class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert">&times;</a>
					A tanítvány törlése sikeres volt!
				</div>
				<div ng-show="validationMsg" class="alert alert-danger alert-error">
					<a href="#" class="close" data-dismiss="alert">&times;</a>
					{{validationMsg}}
				</div>				
				<div class="outerDIV">
					<div class="innerDIV">
						<input class="searchInput" ng-model="search.name" placeholder='Keresés...'> <br>
						<table class="table table-bordered table-hover">
							<thead>
								<tr style="font-weight: bold">
									<th class="gridTitle" style="width: 20%">Név</th>
									<th class="gridTitle" style="width: 5%">Irányító - szám</th>
									<th class="gridTitle" style="width: 30%">Cím</th>
									<th class="gridTitle" style="width: 11%">Telefon - szám</th>
									<th class="gridTitle" style="width: 15%">Email</th>
									<th class="gridTitle" style="width: 19%">Elvégzett tanfolyamok</th>
									<th class="gridTitle" style="width: 20%">Szerkesztés</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="trainee in (filteredTraineeList = (currentPageTraineeList | filter:search:strict | orderBy:'name'))" 
								ng-bind-html-unsafe="trainee.completedTrainings" class="gridRow">
									<td>
										<span
										class="gridCell" editable-text="trainee.name"
										e-name="name" e-form="rowform" onbeforesave="validateName($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="Név"
										 > {{ trainee.name }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.postCode"
										e-name="postCode" e-form="rowform" onbeforesave="validatePostCode($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="Irányítószám"
										 > {{ trainee.postCode }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.address"
										e-name="fullAddress" e-form="rowform" onbeforesave="validateFullAddress($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="Cím"
										 > {{ trainee.address }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.phone"
										e-name="phone" e-form="rowform" onbeforesave="validatePhone($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="Telefonszám"
										 > {{ trainee.phone }} </span>
									</td>
									<td>
										<span
										class="gridCell" editable-text="trainee.email"
										e-name="email" e-form="rowform" onbeforesave="validateEmail($data)"
										onaftersave="saveTrainee(trainee)"
										e-placeholder="Email"
										 > {{ trainee.email }} </span>
									</td>
									<td>
										 <span ng-bind-html="trainee.completedTrainings | to_trusted" class="gridCell" escape=false> {{ trainee.completedTrainings ?  trainee.completedTrainings : '- Nincs -'}} </span>
									</td>
									<td style="white-space: nowrap">
										<!-- form -->
										<form editable-form 
												name="rowform"
												onbeforesave="saveTrainee($data, trainee.userId, trainee.addressId)"
												ng-show="rowform.$visible" 
												class="form-buttons form-inline"
												shown="inserted == trainee">
											<button type="submit" 
													ng-disabled="rowform.$waiting"
													class="btn btn-primary rowButton rowButtonSave"
													title="Mentés">
													<i class="fa fa-floppy-o fa-2x"></i>
											</button>
											<button type="button" 
													ng-disabled="rowform.$waiting"
													ng-click="rowform.$cancel()"
													class="btn btn-default rowButton rowButtonCancel"
													title="Mégse">
													<i class="fa fa-angle-double-left fa-2x"></i>
											</button>
										</form>
										<div class="buttons" ng-show="!rowform.$visible">
											<button class="btn btn-primary rowButton rowButtonEdit"
													ng-click="rowform.$show()"
													title="Szerkesztés">
													<i class="fa fa-pencil-square-o fa-2x"></i>
											</button>
											<button class="btn btn-danger rowButton rowButtonDelete"
													ng-click="removeTrainee(trainee)" 
													title="Törlés">
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
					<div align = "center">
						<pagination 
				  			ng-model="currentPage"
							  total-items="traineeList.length"
							  max-size="maxSize"  
							  data-num-pages="noOfPages"
							  boundary-links="true"
							  class="pagination-sm nomargin" 
							  previous-text="Előző" 
							  next-text="Következő" 
							  first-text="Első" 
							  last-text="Utolsó">
						</pagination>
					</div>	
					<div ng-show="isEditSuccess" class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a>
						A tanítvány szerkesztése sikeres volt!
					</div>
					<div ng-show="isRemoveSuccess" class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert">&times;</a>
						A tanítvány törlése sikeres volt!
					</div>
			</div>
		</div>
	</body>
	</html>
</div>
<!-- /container -->

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


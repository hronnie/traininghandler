<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="outerDIV">
		<div class="innerDIV">
		<a ng-click="goBackFromComplTrainingPage()">Vissza a tanítványokhoz</a>
<!-- 	MESSAGES -->
	<div ng-show="isEditComplTrSuccess" class="alert alert-success">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		Az elvégzett tanfolyam szerkesztése sikeres volt! 
	</div>
	<div ng-show="isRemoveComplTrSuccess" class="alert alert-success">
		<a href="#" class="close" data-dismiss="alert">&times;</a> 
		Az elvégzett tanfolyam törlése sikeres volt!
	</div>
	<div ng-show="complTrValidationMsg" class="alert alert-danger alert-error">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		{{complTrValidationMsg}}
	</div>
	<div class="alert alert-info" data-ng-show="!currentPageTraineeList.length">
		Jelenleg nincs tanítvány az adatbázisban, amit szerkeszteni tudnál!			
	</div>
<!-- 	MESSAGES END -->
		
			<table class="table table-bordered table-hover">
				<thead>
					<tr style="font-weight: bold">
						<th class="gridTitle" style="width: 40%">Elvégzett tanfolyam</th>
						<th class="gridTitle" style="width: 40%">Mikor végezte</th>
						<th class="gridTitle" style="width: 20%">Szerkesztés</th>
					</tr>
				</thead>
				
				<tbody>
					<tr ng-repeat="completedTr in completedTrList" class="gridRow">
						<td class="gridCell">{{completedTr.trainingTypeName}}</td>
						<td class="gridCell">{{completedTr.completedDate  | date:'yyyy-MM-dd'}}</td>
						<td class="gridCell">
						  <button class="btn btn-primary rowButton rowButtonEdit"
                                    ng-click="editCompletedTraining(completedTr.userId, completedTr.trainingTypeId, completedTr.completedDate)" title="Szerkesztés">
                                    <i class="fa fa-pencil-square-o fa-2x"></i>
                          </button>
						                              
                         <button class="btn btn-danger rowButton rowButtonDelete"
                                 ng-click="removeCompletedTraining(completedTr)" title="Törlés">
                                 <i class="fa fa-times fa-2x"></i>
                         </button>
                         </td>
					</tr>
				<tbody>
			</table>

		</div>
	</div>
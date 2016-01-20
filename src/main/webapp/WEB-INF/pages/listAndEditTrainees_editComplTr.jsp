<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="outerDIV">
		<div class="innerDIV">
		<a ng-click="toogleEditView()">Vissza a tanítványokhoz</a>
			<table class="table table-bordered table-hover">
				<thead>
					<tr style="font-weight: bold">
						<th class="gridTitle" style="width: 50%">Elvégzett tanfolyam</th>
						<th class="gridTitle" style="width: 50%">Mikor végezte</th>
					</tr>
				</thead>
				
				<tbody>
					<tr ng-repeat="completedTr in completedTrList" class="gridRow">
						<td class="gridCell">{{completedTr.trainingTypeName}}</td>
						<td class="gridCell">{{completedTr.completedDate  | date:'yyyy-MM-dd'}}</td>
					</tr>
				<tbody>
			</table>

		</div>
	</div>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Training Types</title>
<script src="<c:url value="/resources/js/module/trainingType/thTrainingTypeGridController.js" />"></script>


</head>
<body>
<h4>Manage Training types</h4>
<div ng-app="app" ng-controller="thTrainingTypeController as ttCtrl">
   <table class="table table-bordered table-hover table-condensed">
    <tr style="font-weight: bold">
      <td style="width:35%">Name</td>
      <td style="width:20%">Level Number</td>
      <td style="width:20%">Description</td>
      <td style="width:25%">Edit</td>
    </tr>
    <tr ng-repeat="trainingType in trainingTypes">
      <td>
        <!-- editable trainingTypename (text with validation) -->
        <span editable-text="trainingType.name" e-name="name" e-form="rowform" onbeforesave="checkName($data)" e-ng-minlength="3">
          {{ trainingType.name }}
        </span>
      </td>
      <td>
        <!-- editable levelNo (select-local) -->
        <span editable-text="trainingType.levelNo" e-name="levelNo" e-form="rowform" required>
          {{ trainingType.levelNo }}
        </span>
      </td>
      <td>
        <!-- editable description (select-remote) -->
        <span editable-text="trainingType.description" e-name="description" e-form="rowform" required>
          {{ trainingType.description }}
        </span>
      </td>
      <td style="white-space: nowrap">
        <!-- form -->
       <form editable-form name="rowform" onbeforesave="savetrainingType($data, trainingType.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == trainingType">
          <button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary">
            save
          </button>
          <button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default">
            cancel
          </button>
        </form>
        <div class="buttons" ng-show="!rowform.$visible">
          <button class="btn btn-primary" ng-click="rowform.$show()">edit</button>
          <button class="btn btn-danger" ng-click="removetrainingType($index, trainingType.id)">del</button>
            <!-- $index -->
          </div>  
      </td>
    </tr>
  </table>

  <button class="btn btn-default" ng-click="addtrainingType()">Add row</button>
</div>

</body>
</html>
</div><!-- /container -->
		
<!-- help -->
<jsp:include page="/WEB-INF/pages/help/indexHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


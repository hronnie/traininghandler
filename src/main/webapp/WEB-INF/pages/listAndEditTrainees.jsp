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

			<div ng-show="!isComplEdit">
		      <jsp:include page="/WEB-INF/pages/listAndEditTrainees_listTrainees.jsp" />
		    </div>
			<div ng-show="isComplEdit">
		      <jsp:include page="/WEB-INF/pages/listAndEditTrainees_editComplTr.jsp" />
		    </div>
		    
		</div>
	</body>
	</html>
</div>
<!-- /container -->

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


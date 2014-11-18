<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">


    <title><spring:message code="gatherTraineeInfo.main.title" /></title>
    <script src="<c:url value="/resources/js/application/module/trainingType/thTrainingTypeModule.js" />"></script>
    <script src="<c:url value="/resources/js/application/module/trainingType/thTrainingTypeGridController.js" />"></script>


    </head>
    <body ng-app="trainingTypeModule">
    <h1>adatgyujtes oldal:)</h1>
<!--     <body ng-app="trainingTypeModule"> -->
    </body>
    </html>
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/trainingTypeHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


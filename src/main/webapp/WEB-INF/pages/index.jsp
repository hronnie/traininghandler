<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<title><spring:message code="index.main.page.title" /></title>
<div class="container" id="containerId">
<div id="over">
    <span class="centerer"></span>
    <img class="centered img-rounded homeImg" src="<c:url value="/resources/images/home_wall.jpg" />">
</div>	
	
</div><!-- /container -->
		

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


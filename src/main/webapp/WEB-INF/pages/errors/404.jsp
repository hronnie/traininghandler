<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />

<title><spring:message code="error.main.page.title" /></title>
<h4 class="mainPageTitleCentered"><spring:message code="error.404.page.headline" /></h4>
<div id="over">
    <span class="centerer"></span>
    <img class="centered img-rounded errorImg" src="<c:url value="/resources/images/spongebob-imagination.jpg" />">
</div>	
</body>
</HTML>

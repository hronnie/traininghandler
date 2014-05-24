<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<html>
<head>
<title>Import trainees from CSV page</title>
</head>
<body>
	<p class="type--emphasis type--lrg type--textured">Import trainees from CSV page</p>
	<p>Test message is <c:out value="${testMessage}" />
	<p>Locale : ${pageContext.response.locale}
	Language : <a href="?language=en">English</a>|
<a href="?language=hu_HU">Hungarian</a>
	
	<p>welcome.springmvc : <spring:message code="welcome.message" text="default text" />
</body>
</html>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

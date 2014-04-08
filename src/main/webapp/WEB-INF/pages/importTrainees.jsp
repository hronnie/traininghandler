<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Import trainees from CSV page</title>
</head>
<body>
	<h1>Import trainees from CSV page</h1>
	<p>Test message is <c:out value="${testMessage}" />
	<p>Locale : ${pageContext.response.locale}
	Language : <a href="?language=en">English</a>|
<a href="?language=hu_HU">Hungarian</a>
	
	<p>welcome.springmvc : <spring:message code="welcome.message" text="default text" />
</body>
</html>
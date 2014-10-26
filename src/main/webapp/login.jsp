<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link href="<c:url value="/resources/style/lib/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/style/signin.css" />" rel="stylesheet" />
    <title><spring:message code="login.title" /></title>

  </head>

  <body>
  	<c:if test="${not empty param.error}">
		<font color="red"> Login error. <br /> Reason :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</font>
	</c:if>
  

    <div class="container">

      <form class="form-signin" role="form" method="POST"
			action="<c:url value="/j_spring_security_check" />">
        <h2 class="form-signin-heading"><spring:message code="login.head.title" /></h2>
        <input type="text" class="form-control" name="j_username" placeholder="<spring:message code="login.username.text" />" required autofocus>
        <input type="password" class="form-control" name="j_password" placeholder="<spring:message code="login.password.text" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">placeholder="<spring:message code="login.login.button" />"</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>

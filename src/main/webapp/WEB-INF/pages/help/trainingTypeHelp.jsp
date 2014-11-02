<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

		<!-- open/close -->
		<div class="overlay overlay-door">
			<button type="button" class="overlay-close">Close</button>
			<nav>
			  <h1 class="helpStyle"><spring:message code="trainingType.help.main.title" /></h1>
			  <p class="helpStyle"><spring:message code="trainingType.help.summary" /></p>
			  <h2 class="helpStyle"><spring:message code="trainingType.help.create.title" /></h2>
			  <p class="helpStyle"><spring:message code="trainingType.help.create.paragraph" /></p>
			  <h2 class="helpStyle"><spring:message code="trainingType.help.modify.title" /></h2>
			  <p class="helpStyle"><spring:message code="trainingType.help.modify.paragraph" /></p>
			  <h2 class="helpStyle"><spring:message code="trainingType.help.delete.title" /></h2>
			  <p class="helpStyle"><spring:message code="trainingType.help.delete.paragraph" /></p>
			</nav>
		</div>

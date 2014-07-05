<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<div class="container" id="containerId">
	<section>
		<p>
			<button class="btn btn-1 btn-1e">
				<spring:message code="index.button.manage.trainings" />
			</button>
			<button class="btn btn-1 btn-1e" id="trainingTypeId">
				<spring:message code="index.button.manage.training.types" />
			</button>
			<button class="btn btn-1 btn-1e">
				<spring:message code="index.button.manage.users" />
			</button>
		</p>
		<p>
			<button class="btn btn-1 btn-1e">
				<spring:message code="index.button.imports" />
			</button>
			<button class="btn btn-1 btn-1e" style="display: none;">
				<spring:message code="index.button.imports" />
			</button>
			<button class="btn btn-1 btn-1e" style="display: none;">
				<spring:message code="index.button.imports" />
			</button>
		</p>
	</section>
					
	
</div><!-- /container -->
		<!-- open/close -->
		<div class="overlay overlay-door">
			<button type="button" class="overlay-close">Close</button>
			<nav>
			  <h1 class="helpStyle"><spring:message code="index.help.main.title" /></h1>
			  <h2 class="helpStyle"><spring:message code="index.help.training.title" /></h2>
			  <p class="helpStyle"><spring:message code="index.help.training.paragraph" /></p>
			  <h2 class="helpStyle"><spring:message code="index.help.training.type.title" /></h2>
			  <p class="helpStyle"><spring:message code="index.help.training.type.paragraph" /></p>
			  <h2 class="helpStyle"><spring:message code="index.help.users.title" /></h2>
			  <p class="helpStyle"><spring:message code="index.help.users.paragraph" /></p>
			  <h2 class="helpStyle"><spring:message code="index.help.imports.title" /></h2>
			  <p class="helpStyle"><spring:message code="index.help.imports.paragraph" /></p>
			</nav>
		</div>
		

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


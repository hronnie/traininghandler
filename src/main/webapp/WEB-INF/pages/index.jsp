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
		
<!-- help -->
<jsp:include page="/WEB-INF/pages/help/indexHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


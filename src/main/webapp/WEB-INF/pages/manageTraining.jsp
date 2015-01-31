<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
    <title><spring:message code="importTraining.main.title" /></title>
<div class="container" id="containerId">
    <section>
        <p><nobr>
            <button class="btn btn-1 btn-1e mainButton" onclick="window.location.href='<c:url value="/manageTraining/importTraining" />'">
                <spring:message code="manageTraining.button.importTraining" />
            </button>
            <button class="btn btn-1 btn-1e mainButton" onclick="window.location.href='<c:url value="/manageTraining/showEligibleTrainees" />'">
                <spring:message code="manageTraining.button.showEligibleTrainees" />
            </button>
        </nobr></p>
    </section>
    
</div>
<!-- /container -->

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/gatherTraineeInfoHelp.jsp" />

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
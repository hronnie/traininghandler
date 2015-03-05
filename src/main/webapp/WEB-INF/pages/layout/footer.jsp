<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>	

<!-- help -->
<jsp:include page="/WEB-INF/pages/help/indexHelp.jsp" />		

		</div><!-- #content -->
		
		
		<div id="footer">

			<footer class="footer--page text-align-center">
			          <p class="footer__meta">
					  	<spring:message code="footer.copyright" />
					  </p>
			</footer>

		</div><!-- #footer -->
	</div><!-- #wrapper -->
	
		<script src="<c:url value="/resources/js/application/help/classie.js" />"></script>
		<script src="<c:url value="/resources/js/application/help/door_overlay.js" />"></script>
</body>
</html>

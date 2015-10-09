<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/pages/layout/header.jsp" />

<title>Hiba oldal</title>
<h4 class="mainPageTitleCentered">
	Sajnos valami hiba történt az oldalon. Kérlek írjál az oldal karbantartójának az aron.harsfalvi@gmail.com-ra, 
	vagy próbáld újra a kezdőlapon. <br> Írd le kérlek, hogy előtt mit csináltál, és hogy mikor történt.
	(dátum, óra, perc).
</h4>
<div id="over">
    <span class="centerer"></span>
    <img class="centered img-rounded errorImg" src="<c:url value="/resources/images/spongebob-imagination.jpg" />">
</div>	
</body>
</HTML>

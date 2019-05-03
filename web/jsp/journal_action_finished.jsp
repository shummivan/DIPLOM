<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>
<fmt:setBundle basename="resources.messages" var="msg"/>



<div id="mainmiddle">

    <h1><fmt:message key="content.journal" bundle="${txt}"/>${course.name}</h1>
    <fmt:message key="${actionresult}" bundle="${msg}"/> <a href="/controller?command=journal&id=${course.id}"><fmt:message key="misc.actioncontinue" bundle="${txt}"/></a>
    <%@ include file="inc/footer.inc.jsp" %>

</div>
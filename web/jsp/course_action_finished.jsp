<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<%@ include file="inc/header.inc.jsp" %>

<fmt:setBundle basename="resources.pagecontent" var="txt"/>
<fmt:setBundle basename="resources.messages" var="msg"/>


<div id="mainmiddle">

    <h1><fmt:message key="content.courses" bundle="${txt}"/></h1>
    <%--User ${userName} registered. Go to <a href="/controller">Main page</a> for Sign In.--%>
    <fmt:message key="${actionresult}" bundle="${msg}"/> <a href="/controller?command=courses"><fmt:message key="misc.actioncontinue" bundle="${txt}"/></a>
    <%@ include file="inc/footer.inc.jsp" %>

</div>
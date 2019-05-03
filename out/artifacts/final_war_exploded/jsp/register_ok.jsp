<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>
<%@ include file="inc/header.inc.jsp" %>
<h1><fmt:message key="content.register" bundle="${txt}"/></h1>
<%--User ${userName} registered. Go to <a href="/controller">Main page</a> for Sign In.--%>
<fmt:message key="misc.register_user_ok" bundle="${txt}"/>
<%@ include file="inc/footer.inc.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${localeString}"/>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<html><head><title>Error Page</title></head>
<body>
<h1>Error</h1>
<div>${errorMsg}</div>
<div><a href="/controller"><fmt:message key="content.backtomainpage" bundle="${txt}"/></a></div>
</body></html>

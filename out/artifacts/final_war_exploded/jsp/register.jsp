<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>
<%@ include file="inc/header.inc.jsp" %>


<form class="form-signin" style="max-width:330px; padding:15px;margin: auto;text-align: center" name="loginForm" method="post" action="controller">
    <i class="fa fa-registered" aria-hidden="true" style="font-size: 12em;"></i>

    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="content.register" bundle="${txt}"/></h1>

    <input type="hidden" name="command" value="register">
    <input type="hidden" name="action" value="add">

    <input class="form-control" type="text" name="login" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.login" bundle="${txt}"/>">
    <input class="form-control" type="text" name="first_name" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.firstname" bundle="${txt}"/>">
    <input class="form-control" type="text" name="last_name" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.lastname" bundle="${txt}"/>">
    <input class="form-control" type="password" name="password" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.password" bundle="${txt}"/>">
    <input class="form-control" type="password" name="confpass" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.passwordconfirm" bundle="${txt}"/>">

    <input type="submit" class="btn btn-lg btn-outline-success btn-block" value="<fmt:message key="content.register" bundle="${txt}"/>">

</form>


<%@ include file="inc/footer.inc.jsp" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>



<form class="form-signin" style="max-width:330px; padding:15px;margin: auto;text-align: center" name="loginForm" method="post" action="controller">
    <i class="fa fa-user fa-5" aria-hidden="true" style="font-size: 12em;"></i>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="content.profile" bundle="${txt}"/></h1>
    <fmt:message key="content.newpass" bundle="${txt}"/>: <input class="form-control" type="password" name="password" size="20" maxlength="15" value="">
    <fmt:message key="content.passconfirmation" bundle="${txt}"/>: <input class="form-control" type="password" name="confpass" size="20" maxlength="15" value="">
    <fmt:message key="content.firstname" bundle="${txt}"/>: <input class="form-control" type="text" name="first_name" value="${_USER_DATA.firstName}" placeholder="<fmt:message key="content.firstname" bundle="${txt}"/>">
    <fmt:message key="content.lastname" bundle="${txt}"/>: <input style="margin-bottom: 15px" class="form-control" type="text" name="last_name" value="${_USER_DATA.lastName}" placeholder="<fmt:message key="content.lastname" bundle="${txt}"/>">

    <input class="btn btn-lg btn-primary btn-block" type="submit" value="<fmt:message key="content.action.save" bundle="${txt}"/>">
</form>


<%@ include file="inc/footer.inc.jsp" %>
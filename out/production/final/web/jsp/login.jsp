<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>

<form class="form-signin" style="max-width:330px; padding:15px;margin: auto;text-align: center" name="loginForm" method="post" action="controller">
    <i class="fa fa-sign-in fa-5" aria-hidden="true" style="font-size: 12em;"></i>

    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="content.signin" bundle="${txt}"/></h1>

    <input type="hidden" name="command" value="login">

    <input class="form-control" required autofocus name="login" type="text" value="" pattern="[A-Za-z0-9]{4,20}" placeholder="<fmt:message key="content.login" bundle="${txt}"/>">

    <input class="form-control" name="password" type="password" required value="" pattern="[A-Za-z0-9]{4,20}" placeholder="<fmt:message key="content.password" bundle="${txt}"/>">

    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>

    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="content.signin" bundle="${txt}"/></button>



    <%--<button class="btn btn-lg btn-outline-success btn-block btn-sm" onclick="location.href='/controller?command=register';"><fmt:message key="content.register" bundle="${txt}"/></button>
--%>

</form>

<%@ include file="inc/footer.inc.jsp" %>



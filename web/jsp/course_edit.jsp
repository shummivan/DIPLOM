<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>

<div id="left">

    <%@ include file="inc/menu.inc.jsp" %>

</div>

<div id="mainmiddle">

    <h1><fmt:message key="content.courses" bundle="${txt}"/></h1>

    <div class="block" style="min-width: 50%;">
        <table width="100%">
            <form method="post" action="/controller">
                <input type="hidden" name="command" value="coursesedit">
                <tr class="header"><td colspan="2"><fmt:message key="content.editcourses" bundle="${txt}"/></td></tr>
                <tr>
                    <input type="hidden" name="id" value="${edititem.id}">
                    <td width="100%"><input type="text" name="name" pattern="[A-Za-z0-9]{2,20}" placeholder="<fmt:message key="content.coursename" bundle="${txt}"/>" style="width: 100%;" value="${edititem.name}"></td>
                    <td><input type="submit" value="<fmt:message key="content.action.save" bundle="${txt}"/>"></td>
                </tr>
            </form>
        </table>
    </div>



</div>

<%@ include file="inc/footer.inc.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>


<form name="missForm" method="post" action="controller">
    <input type="hidden" name="command" value="journaladmin">
    <div class="container"  style="background: rgb(225,225,225);border-radius: 17px;margin-top: 16px;">
        <div class="input-group mb-3" style="padding-top: 10px;padding-bottom: 10px;">

            <h4 style="margin-top: 3px;"><fmt:message key="content.subunit" bundle="${txt}"/> </h4>

            <input name="subunit" type="text" class="form-control" style="margin-left: 7px;" value="" placeholder="<fmt:message key="content.subunit" bundle="${txt}"/>">

            <div class="input-group-append">
                <input type="submit" class="btn btn-outline-secondary" value="<fmt:message key="content.finde" bundle="${txt}"/>">
            </div>
        </div>
    </div>
</form>

<div id="mainmiddle">

    <h1><fmt:message key="content.courses" bundle="${txt}"/></h1>

        <table class="table table-hover" style="text-align: center;">
            <tbody>
            <tr>
                <th><fmt:message key="content.addeddate" bundle="${txt}"/></th>
                <th><fmt:message key="content.coursename" bundle="${txt}"/></th>
                <th><fmt:message key="content.teacher" bundle="${txt}"/></th>

            </tr>
            <c:forEach items="${requestScope.courses}" var="item">
                <tr>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.dateAdd}" /></td>
                    <td>${item.name}</td>
                    <td>${item.teacherFirstName} ${item.teacherLastName}</td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
</div>


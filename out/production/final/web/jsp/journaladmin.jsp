<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>

<div id="mainmiddle">

    <table class="table table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th><fmt:message key="content.lastname" bundle="${txt}"/></th>
            <th><fmt:message key="content.firstname" bundle="${txt}"/></th>
            <th><fmt:message key="content.mark" bundle="${txt}"/></th>
            <th><fmt:message key="content.miss" bundle="${txt}"/></th>
            <th><fmt:message key="content.studentcource" bundle="${txt}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.students}" var="item">
            <tr>
                <td>${item.studentLastName}</td>
                <td>${item.studentFirstName}</td>
                <td>
                    <c:if test="${item.mark ne 0}">
                        ${item.mark}
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.miss ne 0}">
                        ${item.miss}
                    </c:if>
                </td>
                <td>
                        ${item.studentcource}
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="inc/footer.inc.jsp" %>
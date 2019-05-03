<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>



<div id="mainmiddle">
    123

    <!--<h1><fmt:message key="content.users" bundle="${txt}"/></h1>-->

    <fmt:message key="mainpage.username" bundle="${txt}"/>: <b>${edititem.login}</b>, <fmt:message key="mainpage.role" bundle="${txt}"/>: <b>${edititem.roleName}</b><br>

    <div align="center">

        <div class="block" style="min-width: 50%;">

            <h3><fmt:message key="content.edituser" bundle="${txt}"/></h3>

            <table border="0" cellspacing="0" cellpadding="0" width="100%">

                <form method="post">
                <tbody>
                <tr><td align="right"><fmt:message key="content.login" bundle="${txt}"/>: </td><td><input type="text" name="login" size="20" pattern="[A-Za-z0-9]{4,20}" maxlength="15" value="${edititem.login}"></td></tr>
                <tr><td align="right"><fmt:message key="content.password" bundle="${txt}"/>: </td><td><input type="password" name="password" pattern="[A-Za-z0-9]{2,20}" size="20" maxlength="15" value=""></td></tr>
                <%--<tr><td align="right"><fmt:message key="content.userrole" bundle="${txt}"/>: </td><td><input type="text" name="role" value="${edititem.roleId}" placeholder="<fmt:message key="content.firstname" bundle="${txt}"/>"></td></tr>--%>
                <tr>
                    <td align="right"><fmt:message key="content.userrole" bundle="${txt}"/>: </td>
                    <td>
                        <select name="role">
                            <c:forEach items="${role_list}" var="current_role">
                                <option value="${current_role.id}" <c:if test="${current_role.id==edititem.roleId}">selected </c:if>/>${current_role.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr><td align="right"><fmt:message key="content.firstname" bundle="${txt}"/>: </td><td><input type="text" name="first_name" pattern="[A-Za-z0-9]{4,20}" value="${edititem.firstName}" placeholder="<fmt:message key="content.firstname" bundle="${txt}"/>"></td></tr>
                <tr><td align="right"><fmt:message key="content.lastname" bundle="${txt}"/>: </td><td><input type="text" name="last_name" pattern="[A-Za-z0-9]{4,20}" value="${edititem.lastName}" placeholder="<fmt:message key="content.lastname" bundle="${txt}"/>"></td></tr>
                <%--<tr><td align="right" valign="top">Email:</td><td valign="top"><input type="text" name="email" size="20" maxlength="40" value="${_USER_DATA.email}"></td></tr>--%>
                <tr><td colspan="2" align="right"><input type="submit" value="<fmt:message key="content.action.save" bundle="${txt}"/>"></td></tr>

            </tbody></form>
            </table>
        </div>
    </div>

</div>

<%@ include file="inc/footer.inc.jsp" %>
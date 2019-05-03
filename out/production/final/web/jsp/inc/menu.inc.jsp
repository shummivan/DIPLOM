<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${localeString}"/>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<div class="menu">

    <div class="block">
        <div class="content">
            <ul style="padding-left: 0px;margin-bottom: 2px;">
                <c:forEach items="${sessionScope._USER_ACCESS}" var="command">
                    <c:if test="${command.menuItem}">
                        <a class="dropdown-item" href="/controller?command=${command.name}"><fmt:message key="menuitem.${command.name}" bundle="${txt}"/></a>
                    </c:if>
                </c:forEach>

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/controller?command=logout" ><fmt:message key="content.signout" bundle="${txt}"/>[${sessionScope._USER_DATA.login}]</a>
            </ul>
        </div>
    </div>

</div>

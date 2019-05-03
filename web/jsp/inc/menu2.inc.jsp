<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${localeString}"/>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>



<li>
    <a title="Info"><i class="fas fa-user-cog"></i>
        <i class="fa fa-pencil" aria-hidden="true"></i>
        <span class="link-text">Custom</span>
    </a>
    <ul class="submenu" style="padding-left: 0px;">
    <c:forEach items="${sessionScope._USER_ACCESS}" var="command">
    <c:if test="${command.menuItem}">
        <ol><a href="/controller?command=${command.name}"><fmt:message key="menuitem.${command.name}" bundle="${txt}"/></a></ol>
    </c:if>
    </c:forEach>
    </ul>
</li>






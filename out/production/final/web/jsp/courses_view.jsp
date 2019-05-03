<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>

<div id="accordion" style="text-align: center;">
    <div class="card">
        <div class="card-header" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    <fmt:message key="content.courses" bundle="${txt}"/>
                </button>
            </h5>


        </div>

        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
            <div class="card-body" >

                <table class="table table-hover" style="text-align: center;">
                    <thead>
                    <tr>
                        <th><fmt:message key="content.addeddate" bundle="${txt}"/></th>
                        <th><fmt:message key="content.coursename" bundle="${txt}"/></th>
                        <th><fmt:message key="content.teacher" bundle="${txt}"/></th>
                    </tr>
                    </thead>
                    <tbody>
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
        </div>
    </div>
</div>

<%@ include file="inc/footer.inc.jsp" %>
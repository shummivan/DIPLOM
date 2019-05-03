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
                    <fmt:message key="content.users" bundle="${txt}"/>
                </button>

            </h5>
        </div>

        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
            <div class="card-body" >

                <table class="table table-hover" style="text-align: center;">
                    <thead>
                    <tr>
                        <th><fmt:message key="content.addeddate" bundle="${txt}"/></th>
                        <th><fmt:message key="content.login" bundle="${txt}"/></th>
                        <th><fmt:message key="content.firstname" bundle="${txt}"/></th>
                        <th><fmt:message key="content.lastname" bundle="${txt}"/></th>
                        <th><fmt:message key="content.userrole" bundle="${txt}"/></th>
                        <th><fmt:message key="content.lastlogin" bundle="${txt}"/></th>
                        <th><fmt:message key="content.actions" bundle="${txt}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.items}" var="item">
                        <tr>
                            <td><%--<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${item.dateAdd}" />--%><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.dateAdd}" /></td>
                            <td>${item.login}</td>
                            <td>${item.firstName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.roleName}</td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.lastLogin}" /></td>
                            <td>
                                <input type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#${item.id}" value="<fmt:message key="content.action.delete" bundle="${txt}"/>">
                                <input type="button" class="btn btn-outline-info"В onclick="location.href='/controller?command=useredit&id=${item.id}';" value="<fmt:message key="content.action.edit" bundle="${txt}"/>">
                            </td>
                        </tr>


                        <div class="modal fade" id="${item.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Подтвердите действие</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>

                                        <input type="button" class="btn btn-primary" onclick="location.href='/controller?command=userdel&id=${item.id}';" value="<fmt:message key="content.action.delete" bundle="${txt}"/>">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<%@ include file="inc/footer.inc.jsp" %>
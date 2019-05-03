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
                        <th><fmt:message key="content.subscribeflag" bundle="${txt}"/></th>
                        <th><fmt:message key="content.mark" bundle="${txt}"/></th>
                        <th><fmt:message key="content.actions" bundle="${txt}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.courses}" var="item">
                        <td>${item.dateAdd}</td>
                        <td>${item.name}</td>
                        <td>${item.teacherFirstName} ${item.teacherLastName}</td>
                        <td>${item.subscribe}</td>
                        <td>
                            <c:if test="${item.mark ne 0}">
                                ${item.mark}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not item.subscribe}">
                                <input type="button" class="btn btn-outline-info" onclick="location.href='/controller?command=selectcoursessubscribe&id=${item.id}';" value="<fmt:message key="content.action.subsctibe" bundle="${txt}"/>">
                            </c:if>
                            <c:if test="${item.subscribe}">
                                <c:if test="${item.mark eq 0}">
                                    <input type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#exampleModal"  value="<fmt:message key="content.action.unsubsctibe" bundle="${txt}"/>">

                                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

                                                    <input type="button" class="btn btn-primary" onclick="location.href='/controller?command=selectcoursesunsubscribe&id=${item.id}';" value="<fmt:message key="content.action.delete" bundle="${txt}"/>">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </c:if>
                            </c:if>
                            <c:if test="${not empty item.note}">
                                <input type="button" class="btn btn-outline-info" onclick="showHideTr('row${item.id}');" value="<fmt:message key="content.action.viewnote" bundle="${txt}"/>">
                            </c:if>
                        </td>
                        </tr>
                        <c:if test="${not empty item.note}">
                            <tr style="display: none;" id="row${item.id}">
                                <td colspan="6" class="note">${item.note}</td>
                            </tr>
                        </c:if>

                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>



<%@ include file="inc/footer.inc.jsp" %>
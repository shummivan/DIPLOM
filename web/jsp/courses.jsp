<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>

<div id="mainmiddle">

    <div class="container"  style="background: rgb(225,225,225);border-radius: 17px;margin-top: 16px;">
        <form method="post" action="/controller">
            <input type="hidden" name="command" value="coursesadd">

            <div class="input-group mb-3" style="padding-top: 10px;padding-bottom: 10px;">
                <h4 style="margin-top: 3px;"><fmt:message key="content.course.addnewcourse" bundle="${txt}"/></h4>

                <input type="text" name="name" pattern="[A-Za-z0-9]{2,20}" class="form-control" style="margin-left: 7px;" placeholder="<fmt:message key="content.coursename" bundle="${txt}"/>">

                <div class="input-group-append">

                    <input type="submit" class="btn btn-outline-secondary" value="<fmt:message key="content.action.add" bundle="${txt}"/>">

                </div>

            </div>
        </form>
        </div>

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

                        <c:forEach items="${requestScope.courses}" var="item">
                            <tr>
                                <c:if test="${sessionScope._USER_DATA.id eq item.teacherId}">
                                <td onclick="location.href='/controller?command=journal&id=${item.id}';"><fmt:formatDate pattern="dd/MM/yyyy" value="${item.dateAdd}" /></td>
                                <td onclick="location.href='/controller?command=journal&id=${item.id}';">${item.name}</td>
                                <td onclick="location.href='/controller?command=journal&id=${item.id}';">${item.teacherFirstName} ${item.teacherLastName}</td>
                                <td onclick="location.href='/controller?command=journal&id=${item.id}';">${item.studentsCount}</td>
                                <td>
                                    <c:if test="${sessionScope._USER_DATA.id eq item.teacherId}">
                                        <input type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#${item.id}" value="<fmt:message key="content.action.delete" bundle="${txt}"/>">
                                        <input type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#${item.id}2" value="<fmt:message key="content.action.edit" bundle="${txt}"/>">
                                   </c:if>
                                </td>
                                </c:if>
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
                                        <h5>Вы действительно хотите удалить ${item.name}?</h5>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>
                                        <input type="button" class="btn btn-danger" onclick="location.href='/controller?command=coursesdelete&id=${item.id}';" value="<fmt:message key="content.action.delete" bundle="${txt}"/>">
                                    </div>
                                </div>
                            </div>
                            </div>


                        <div class="modal fade" id="${item.id}2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel2"><fmt:message key="content.editcourses" bundle="${txt}"/> ${item.name}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form method="post" action="/controller">
                                        <div class="modal-footer">
                                            <input type="hidden" name="command" value="coursesedit">
                                            <input type="hidden" name="id" value="${item.id}">
                                            <input type="text" class="form-control" name="name" pattern="[A-Za-z0-9]{2,20}" placeholder="<fmt:message key="content.coursename" bundle="${txt}"/>" style="width: 100%;" value="${edititem.name}">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>
                                            <input type="submit" class="btn btn-success" value="<fmt:message key="content.action.save" bundle="${txt}"/>">
                                        </div>
                                    </form>
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
</div>

<%@ include file="inc/footer.inc.jsp" %>
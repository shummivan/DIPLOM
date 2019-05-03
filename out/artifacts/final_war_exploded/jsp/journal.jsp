<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>
<div class="card z-depth-0 bordered">

    <input class="btn btn-outline-success" type="button" data-toggle="modal" data-target="#miss" value="<fmt:message key="content.action.add" bundle="${txt}"/>">

    <div class="modal fade" id="miss" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel3" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form method="post" action="${contextPath}/controller">
                    <input type="hidden" name="command" value="journallessonadd">
                    <input type="hidden" name="id" value="${course.id}">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel3">Создать занятие</h5>
                    <input name="mdata" class="form-control" type="date">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>
                    <input type="submit"  class="btn btn-sucess" value="<fmt:message key="content.action.add" bundle="${txt}"/>">
                </div>
                </form>
            </div>
        </div>
    </div>
<!-- Editable table -->
<div class="card">
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th><fmt:message key="content.lastname" bundle="${txt}"/></th>
                    <th><fmt:message key="content.firstname" bundle="${txt}"/></th>
                    <c:forEach items="${requestScope.days}" var="item">
                        <c:if test="${not empty item.mdata}">
                            <th contenteditable="true">${item.mdata}</th>
                        </c:if>
                    </c:forEach>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${requestScope.students}" var="item">
                        <tr>
                            <td>
                                ${item.studentLastName}
                            </td>
                            <td>
                                ${item.studentFirstName}
                            </td>

                            <%--<c:forEach items="${requestScope.days}" var="item2">--%>
                                <%--<c:set var="tmp" value="1" />--%>
                                <%--<c:set var="tmp2" value="${requestScope.marks}" />--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${item2.mdata != null}">--%>
                                        <%--<td>0</td>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${item2.mdata == null}">--%>
                                        <%--<td>1</td>--%>
                                    <%--</c:when>--%>
                                <%--</c:choose>--%>
                            <%--</c:forEach>--%>


                    <c:forEach items="${requestScope.marks}" var="item2">
                        <c:if test="${item.studentId == item2.studentId}">
                            <td contenteditable="true">${item2.mark}</td>
                        </c:if>
                    </c:forEach>


                        </tr>
                </c:forEach>
                </tbody>


            </table>
        </div>
    </div>
</div>
</div>



<c:forEach items="${requestScope.students}" var="item2">
    <div class="card z-depth-0 bordered">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#${item2.studentId}5"
                aria-expanded="true" aria-controls="collapseOne2">
                ${item2.studentLastName} ${item2.studentFirstName}
        </button>

        <div id="${item2.studentId}5" class="collapse" aria-labelledby="headingOne2" data-parent="#accordionExample275">
            <div class="card-body">
                <table class="table table-hover" style="text-align: center;">
                    <thead>
                    <tr>
                        <th><fmt:message key="content.lastname" bundle="${txt}"/></th>
                        <th><fmt:message key="content.firstname" bundle="${txt}"/></th>
                        <th><fmt:message key="content.mark" bundle="${txt}"/></th>
                        <th><fmt:message key="content.miss" bundle="${txt}"/></th>
                        <th><fmt:message key="content.notice" bundle="${txt}"/></th>
                        <th><fmt:message key="content.mdata" bundle="${txt}"/></th>
                        <th><fmt:message key="content.actions" bundle="${txt}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.marks}" var="item">
                        <c:if test="${item.studentId == item2.studentId}">
                    <tr>
                        <td>
                                ${item.studentLastName}
                        </td>
                        <td>
                                ${item.studentFirstName}
                        </td>
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
                            <c:if test="${not empty item.note}">
                                ${item.note}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty item.mdata}">
                                ${item.mdata}
                            </c:if>
                        </td>
                        <td>
                            <input class="btn btn-outline-success" type="button" data-toggle="modal" data-target="#${item.id}" value="<fmt:message key="content.action.edit" bundle="${txt}"/>">

                            <div class="modal fade" id="${item.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel2"><fmt:message key="content.editstudentmark" bundle="${txt}"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">

                                            <table>
                                                <form method="post" action="${contextPath}/controller">
                                                    <input type="hidden" name="command" value="journaledit">
                                                    <input type="hidden" name="id" value="${item.id}">
                                                    <tr>
                                                        <td style="width: 20%;">
                                                            <fmt:message key="content.student" bundle="${txt}"/>
                                                        </td>
                                                        <td>
                                                                ${item.studentLastName}
                                                                ${item.studentFirstName}
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 20%;">
                                                            <fmt:message key="content.mark" bundle="${txt}"/>
                                                        </td>
                                                        <td style="width: 80%;">
                                                            <select name="mark">
                                                                <option value="0"> - </option>
                                                                <option value="1"> 1 </option>
                                                                <option value="2"> 2 </option>
                                                                <option value="3"> 3 </option>
                                                                <option value="4"> 4 </option>
                                                                <option value="5"> 5 </option>
                                                                <option value="6"> 6 </option>
                                                                <option value="7"> 7 </option>
                                                                <option value="8"> 8 </option>
                                                                <option value="9"> 9 </option>
                                                                <option value="10"> 10 </option>
                                                            </select>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td style="width: 20%;">
                                                            <fmt:message key="content.miss" bundle="${txt}"/>
                                                        </td>
                                                        <td style="width: 80%;">
                                                            <select name="miss">
                                                                <option value="0"> - </option>
                                                                <option value="1"> 1 </option>
                                                                <option value="2"> 2 </option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <fmt:message key="content.mdata" bundle="${txt}"/>
                                                        </td>
                                                        <td>
                                                            <input name="mdata" class="form-control" type="date" value="${edititem.mdata}">
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>
                                                            <fmt:message key="content.notice" bundle="${txt}"/>
                                                        </td>
                                                        <td>
                                                            <textarea name="note" style="width: 100%; height: 100px;"><c:out value="${edititem.note}"/></textarea>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>
                                                        </td>
                                                        <td>
                                                            <input type="submit" class="btn btn-success" value="<fmt:message key="content.action.save" bundle="${txt}"/>">
                                                        </td>
                                                    </tr>
                                                </form>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

    <div class="accordion" id="accordionExample275"></div>
</c:forEach>

    <%--<div id="accordion" style="text-align: center;">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        <fmt:message key="content.journal" bundle="${txt}"/> ${course.name}
                    </button>
                </h5>
            </div>


            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body" >

                    <table class="table table-hover" style="text-align: center;">
                        <thead>
                        <tr>
                            <th><fmt:message key="content.lastname" bundle="${txt}"/></th>
                            <th><fmt:message key="content.firstname" bundle="${txt}"/></th>
                            <th><fmt:message key="content.mark" bundle="${txt}"/></th>
                            <th><fmt:message key="content.miss" bundle="${txt}"/></th>
                            <th><fmt:message key="content.notice" bundle="${txt}"/></th>
                            <th><fmt:message key="content.mdata" bundle="${txt}"/></th>
                            <th><fmt:message key="content.actions" bundle="${txt}"/></th>
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
                                    <c:if test="${not empty item.note}">
                                        ${item.note}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${not empty item.mdata}">
                                        ${item.mdata}
                                    </c:if>
                                </td>
                                <td>
                                    <input class="btn btn-outline-success" type="button" data-toggle="modal" data-target="#${item.studentId}5" value="<fmt:message key="content.action.edit" bundle="${txt}"/>">

                                    <div class="modal fade" id="${item.studentId}5" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="content.editstudentmark" bundle="${txt}"/></h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">

                                                    <table>
                                                        <form method="post" action="${contextPath}/controller">
                                                            <input type="hidden" name="command" value="journaledit">
                                                            <input type="hidden" name="id" value="${item.id}">
                                                            <tr>
                                                                <td style="width: 20%;">
                                                                    <fmt:message key="content.student" bundle="${txt}"/>
                                                                </td>
                                                                <td>
                                                                        ${item.studentLastName}
                                                                        ${item.studentFirstName}
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="width: 20%;">
                                                                    <fmt:message key="content.mark" bundle="${txt}"/>
                                                                </td>
                                                                <td style="width: 80%;">
                                                                    <select name="mark">
                                                                        <option value="0"> - </option>
                                                                        <option value="1"> 1 </option>
                                                                        <option value="2"> 2 </option>
                                                                        <option value="3"> 3 </option>
                                                                        <option value="4"> 4 </option>
                                                                        <option value="5"> 5 </option>
                                                                        <option value="6"> 6 </option>
                                                                        <option value="7"> 7 </option>
                                                                        <option value="8"> 8 </option>
                                                                        <option value="9"> 9 </option>
                                                                        <option value="10"> 10 </option>
                                                                    </select>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td style="width: 20%;">
                                                                    <fmt:message key="content.miss" bundle="${txt}"/>
                                                                </td>
                                                                <td style="width: 80%;">
                                                                    <select name="miss">
                                                                        <option value="0"> - </option>
                                                                        <option value="1"> 1 </option>
                                                                        <option value="2"> 2 </option>

                                                                    </select>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <fmt:message key="content.mdata" bundle="${txt}"/>
                                                                </td>
                                                                <td>
                                                                    <input name="mdata" class="form-control" type="date" value="${edititem.mdata}">
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td>
                                                                    <fmt:message key="content.notice" bundle="${txt}"/>
                                                                </td>
                                                                <td>
                                                                    <textarea name="note" style="width: 100%; height: 100px;"><c:out value="${edititem.note}"/></textarea>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>
                                                                </td>
                                                                <td>
                                                                    <input type="submit" class="btn btn-success" value="<fmt:message key="content.action.save" bundle="${txt}"/>">
                                                                </td>
                                                            </tr>
                                                        </form>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </td>
                            </tr>


                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>--%>

<%@ include file="inc/footer.inc.jsp" %>


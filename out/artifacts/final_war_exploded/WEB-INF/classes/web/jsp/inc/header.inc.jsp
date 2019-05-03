<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/mytaglib.tld" prefix="mytag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="${localeString}"/>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>
<html>
<header>

    <title><fmt:message key="header.title" bundle="${txt}"/></title>
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <link href="../css/default.css" rel="stylesheet" />
    <link href="../css/modal.css" rel="stylesheet" />

    <link rel="stylesheet" href="../css/main.css">

    <link rel="stylesheet" href="../css/drop_menu.css">

</header>
<body>
<nav class="navbar navbar-light teal lighten-4 dark-teal-text">
    <button class="navbar-toggler toggler-example" style="border-color:#ffffff" type="button" data-toggle="collapse" data-target="#navbarSupportedContent1"
            aria-controls="navbarSupportedContent1" aria-expanded="false" aria-label="Toggle navigation"><span class="dark-white-text"><i
            class="fa fa-bars fa-1x"></i></span></button>

    <a class="navbar-brand" style="font-size: 25pt;color:#ffffff;" href="/controller">БНТУ</a>

    <c:if test="${empty sessionScope._USER_DATA}">
        <input type="button" style="color: #ffffff; border-color: #ffffff" class="modal-btn btn btn-outline-success my-2 my-sm-0" value="<fmt:message key="content.signin" bundle="${txt}"/>">
        <div class='modal avtoriz'>
            <div class='close'>
                <span></span>
                <span></span>
            </div>
            <div class='options'>
                <a href='#' class='active'><fmt:message key="content.signin" bundle="${txt}"/></a>
                <a href='#'><fmt:message key="content.register" bundle="${txt}"/></a>
            </div>
            <div class='wrapper'>
                <div class='content'>
                    <div class='inner'>
                        <div class='side'>
                            <form>
                                <input type="hidden" name="command" value="login">
                                <div class='field'>
                                    <input class="form-control" required autofocus name="login" type="text" value="" placeholder="<fmt:message key="content.login" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <input class="form-control" name="password" type="password" required value="" pattern="[A-Za-z0-9]{4,20}" placeholder="<fmt:message key="content.password" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <button class="btn btn-lg btn-success btn-block" type="submit"><fmt:message key="content.signin" bundle="${txt}"/></button>
                                </div>
                            </form>
                        </div>
                        <div class='side'>
                            <form>
                                <input type="hidden" name="command" value="register">
                                <input type="hidden" name="action" value="add">
                                <div class='field'>
                                    <input class="form-control" type="text" name="login" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.login" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <input class="form-control" type="text" name="first_name" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.firstname" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <input class="form-control" type="text" name="last_name" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.lastname" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <input class="form-control" type="password" name="password" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.password" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <input class="form-control" type="password" name="confpass" value="" pattern="[A-Za-z0-9]{4,20}" style="margin-bottom: 5px;" placeholder="<fmt:message key="content.passwordconfirm" bundle="${txt}"/>">
                                </div>
                                <div class='field'>
                                    <button class="btn btn-lg btn-success btn-block" type="submit"><fmt:message key="content.register" bundle="${txt}"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class='overlay'></div>


    </c:if>
    <c:if test="${not empty sessionScope._USER_DATA}">
    <div class="btn-group">
            <button type="button" style="color: #ffffff; border-color: #ffffff" class="btn btn-outline-success my-2 my-sm-0 dropdown-toggle" aria-haspopup="true" aria-expanded="false" data-toggle="dropdown">
            ${sessionScope._USER_DATA.login}
            </button>

            <div class="dropdown-menu dropdown-menu-right">
            <%@ include file="menu.inc.jsp" %>
            </div>
    </div>
    </c:if>

    <div class="collapse navbar-collapse" id="navbarSupportedContent1">
        <nav>
            <div class="menu">
                <ul class="topmenu" style="padding-left: 0px;">
                    <c:if test="${not empty sessionScope._USER_DATA}">
                        <%@ include file="menu2.inc.jsp" %>
                    </c:if>
                    <li>
                        <a title="Jornal" href="/controller?command=coursesview">
                            <i class="fa fa-address-book" aria-hidden="true"></i>
                            <span class="link-text">Jornal</span>
                        </a>
                        <ul class="submenu" style="padding-left: 0px;">
                            <ol><a href="#" style="text-decoration: none;">ФИТР</a></ol>
                            <ol><a href="#" style="text-decoration: none;">СФ</a></ol>
                            <ol><a href="#" style="text-decoration: none;">ФТУГ</a></ol>
                        </ul>
                    </li>
                    <li>
                        <a title="Info">
                            <i class="fa fa-lightbulb-o" aria-hidden="true"></i>
                            <span class="link-text">Info</span>
                        </a>
                        <ul class="submenu" style="padding-left: 0px;">
                            <ol><a href="#" style="text-decoration: none;">О нас</a></ol>
                            <ol><a href="#" style="text-decoration: none;">Фотогалерея</a></ol>
                            <ol><a href="#" style="text-decoration: none;">Газета БНТУ</a></ol>
                        </ul>
                    </li>
                    <li>
                        <a title="Info">
                            <i class="fa fa-lightbulb-o" aria-hidden="true"></i>
                            <span class="link-text">Courses</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</nav>

<%--<div class="pos-f-t">--%>
    <%--<div class="collapse" id="navbarToggleExternalContent">--%>
        <%--<div class="bg-dark p-4">--%>

            <%--<span class="text-muted"></span>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<nav class="navbar navbar-dark" style="text-align: center; background-color: #2c802c;">--%>

        <%--<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">--%>
            <%--<span class="navbar-toggler-icon"></span>--%>
        <%--</button>--%>

            <%--<a class="navbar-brand" style="font-size: 22pt;margin-left: 17px;text-decoration: none" href="/controller"><fmt:message key="content.courses" bundle="${txt}"/></a>--%>

        <%--<div style="width: 800px">--%>
        <%--</div>--%>


        <%--<c:if test="${empty sessionScope._USER_DATA}">--%>
            <%--<input type="button" style="color: #ffe484; border-color: #ffe484" class="modal-btn btn btn-outline-success my-2 my-sm-0" value="<fmt:message key="content.signin" bundle="${txt}"/>">--%>
        <%--</c:if>--%>

        <%--<c:if test="${not empty sessionScope._USER_DATA}">--%>
            <%--<div class="btn-group">--%>
                <%--<button type="button" style="color: #ffe484; border-color: #ffe484" class="btn btn-outline-info my-2 my-sm-0 dropdown-toggle" aria-haspopup="true" aria-expanded="false" data-toggle="dropdown">--%>
                    <%--Пользователь:${sessionScope._USER_DATA.login}--%>
                <%--</button>--%>

                <%--<div class="dropdown-menu dropdown-menu-right">--%>
                    <%--<%@ include file="menu.inc.jsp" %>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>

    <%--</nav>--%>
<%--</div>--%>
<script  src="../js/modal.js"></script>
<script  src="../js/index.js"></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.20.2/TweenMax.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>



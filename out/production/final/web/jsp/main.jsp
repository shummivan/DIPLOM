<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%--fmt:setLocale value="${sessionScope._CURRENT_LOCALE}"/--%>
<fmt:setBundle basename="resources.pagecontent" var="txt"/>

<%@ include file="inc/header.inc.jsp" %>


<%--<div id="headerwrap"></div>--%>
<div style="display: flex; margin: 1rem;border-bottom: #a39d49">
    <div><img itemprop="image" src="http://www.bntu.by/images/images/bntu-logo2.gif" alt="Логотип БНТУ"></div>
    <div><h1 style="margin-top: 0.5rem; color: #2c802c; font-weight: 1000;">Белорусский национальный технический университет</h1></div>
</div>


<div class="container w">
    <h3 style="background-position: center"><span>кафедры</span></h3>
    <div class="row centered">
        <br>
        <div class="col-lg-3" style="margin-top: 7px">
            <b class="fa fa-desktop tilt" style="font-size: 35pt"></b>
            <h5>Программное обеспечение вычислительной техники и автоматизированных систем</h5>
        </div>
        <div class="col-lg-3" style="margin-top: 7px;">
            <b class="fa fa-cogs tilt" style="font-size: 35pt"></b>
            <h5 style="padding-top: 15px;">Системы автоматизированного проектирования</h5>
        </div>
        <div class="col-lg-3" style="margin-top: 7px">
            <b class="fa fa-microchip tilt" aria-hidden="true" style="font-size: 35pt"></b>
            <h5 style="padding-top: 15px;">Робототехнические системы</h5>
        </div>
        <div class="col-lg-3" style="margin-top: 7px">
            <b class="fa fa-sitemap tilt" aria-hidden="true" style="font-size: 35pt"></b>
            <h5  style="padding-top: 15px;">Электропривод и автоматизация промышленных установок и технологических комплексов</h5>
        </div>
    </div>
</div>

<%@ include file="inc/footer.inc.jsp" %>
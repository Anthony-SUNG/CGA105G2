<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.point.model.Point.pojo.Point" %>
<%@ page import="com.point.model.service.PointService" %>
<%
    PointService pointSvc = new PointService();
    List<Point> list = pointSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>π°point</title>
    <style>
        /* εεεη¨± */
        .fw-bolder {
            font-size: 1.3rem;
        }

        /* εζι»ζΈ */
        .lrp_text_count {
            font-weight: bold;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        }

        /* εεεη */
        .card-img-top {
            height: 330px;
            width: 230px;
        }

        .col mb-5::after {
            position: absolute;
            content: "";
            top: 50%;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #444444;
            transform: translateY(-50%);
        }
    </style>
</head>

<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin01.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <section class="py-5">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">πι»ζΈζ₯θ©’</h1>
                </div>
                <div class="table-responsive ">
                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>η°εεε </th>
                            <th>η°ει»ζΈ</th>
                        </tr>
                        </thead>
                        <tbody class="code_tbody">
                        <c:forEach var="Point" items="${list}">
                            <tr>
                                <td>${Point.pointChange}</td>
                                <td>${Point.pointNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <canvas class="my-4 w-100" id="myChart" width="900" height="150"></canvas>
            </section>
            <br>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <nav aria-label="Page navigation example   justify-content-center" class="m-5 ">
                    <ul class="pagination">
                    </ul>
                </nav>
            </div>
        </main>
    </div>

</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(π°point)").closest("a").addClass("active disabled topage");
    $("a:contains(π°ι»ζΈεε)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu1").removeClass("collapse");
    $("#pageSubmenu1 a:contains(πι»ζΈζ₯θ©’)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    $(document).ready(function () {
        $("#pointgooditem").click(function () {
            //θΎε₯ε¦δΈδΈͺι‘΅ι’ηιΎζ₯
            //ζηθ·³θ½¬ε°controllerδΈ­ηtoIntroductionθΏδΈͺζΉζ³δΈ­θΏθ‘ηι‘΅ι’θ·³θ½¬
            window.location.href = "pointItemPage.jsp";
        });
    });
</script>
</body>
</html>
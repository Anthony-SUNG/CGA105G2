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

    <title>💰point</title>
    <style>
        body {
            height: 100%;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }

        /* 商品名稱 */
        .fw-bolder {
            font-size: 1.3rem;
        }

        /* 兌換點數 */
        .lrp_text_count {
            font-weight: bold;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        }

        /* 商品圖片 */
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
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin01.jsp" %>
        <!-- nav end -->
        <main role="main" class="main-content  col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <section class="section jarallax text-white" data-jarallax data-speed="0.2">
                <!-- <img class="section-background-image jarallax-img" src="./images/tenor.gif" alt="background image" /> -->
                <div class="section-background-color"
                     style="background: linear-gradient(to right top, rgb(25, 182, 143), rgb(68, 100, 148)) rgb(25, 182, 143); padding-top: 120px; padding-bottom: 40px;">
                </div>
                <div class="section-content container d-flex flex-column align-items-center">
            <span
                    class="badge badge-pill badge-ghost fs-2 font-family-dark text-uppercase font-weight-bold letter-spacing-caption">
              FoodMap
            </span>
                    <h1 class="mt-5 mb-17 fs-10 fs-md-10">點數商城</h1>
                </div>
            </section>

            <section class="py-5">
                <div
                        class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">💰點數查詢</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2">
                            <!--                             <button type="button" class="btn btn-sm btn-outline-info">Share</button> -->
                            <!--                             <button type="button" class="btn btn-sm btn-outline-info">Export</button> -->
                        </div>
                    </div>
                </div>
                <div class="table-responsive ">
                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>編號</th>
                            <th>會員編號</th>
                            <th>異動原因</th>
                            <th>異動點數</th>
                        </tr>
                        </thead>
                        <tbody class="code_tbody">
                        <c:forEach var="Point" items="${list}">
                            <tr>
                                <td>${Point.pointId}</td>
                                <td>${Point.memId}</td>
                                <td>${Point.pointChange}</td>
                                <td>${Point.pointNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <canvas class="my-4 w-100" id="myChart" width="900" height="150"></canvas>
            </section>

        </main>
    </div>

</div>

<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sticky-sidebar -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
    $("a:contains(💰point)").closest("a").addClass("active disabled topage");
</script>
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 30,
        bottomSpacing: 20,
        leftSpacing: 30,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });

</script>

</body>

</html>
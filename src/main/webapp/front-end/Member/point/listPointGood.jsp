<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.point.model.PointGoods.pojo.PointGoods" %>
<%@ page import="com.point.model.service.PointGoodsService" %>
<%
    PointGoodsService pointgoodsSvc = new PointGoodsService();
    List<PointGoods> list = pointgoodsSvc.getAll();
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
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
                <div class="container px-4 px-lg-5 mt-5">
                    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

                        <c:forEach var="PointGoods" items="${list}">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Product image-->
                                    <a href="http://127.0.0.1:5502/CGA105G2/home/PointGood_item1.html">
                                        <img class="card-img-top"
                                             src="https://images.pexels.com/photos/3768323/pexels-photo-3768323.jpeg?auto=compress&cs=tinysrgb&w=600"
                                             alt="..."/>
                                    </a>
                                    <!-- Product details-->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <h5 class="fw-bolder">${PointGoods.pdName}</h5>
                                            <!-- Product price-->
                                            <span class="lrp_text_count">${PointGoods.pdPrice} <dfn>points</dfn></span>
                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center p-1"><a class="btn btn-outline-dark mt-auto fs-4"
                                                                        href="#">立即兌換</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <nav aria-label="Page navigation example   justify-content-center" class="m-5 ">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="#">上一頁</a></li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">下一頁</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
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
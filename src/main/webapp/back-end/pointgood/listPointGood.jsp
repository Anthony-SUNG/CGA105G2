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
</head>

<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin02.jsp" %>
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
                                    <a
                                            href="http://127.0.0.1:5502/CGA105G2/home/PointGood_item1.html">
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
                                        <div class="text-center p-1">
                                            <td>
                                                <FORM METHOD="post"
                                                      ACTION="<%=request.getContextPath()%>/back-end/pointgood/point.do"
                                                      style="margin-bottom: 0px;">
                                                    <input class="btn btn-outline-dark mt-auto fs-4"
                                                           type="submit" value="修改商品"> <input type="hidden"
                                                                                                  name="pdId"
                                                                                                  value="${PointGoods.pdId}">
                                                    <input
                                                            type="hidden" name="action" value="getOne_For_Update">
                                                </FORM>
                                            </td>
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
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(📔商城)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻商品管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆商品總覽)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    const list=[];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list){
        switch (e){
            case 1:
                $("#a2").removeClass("disabled");
                $("#a3").removeClass("disabled");
                $("#a4").removeClass("disabled");
                $("#a5").removeClass("disabled");
                break;
            case 2:
                $("#a2").removeClass("disabled");
                break;
            case 3:
                $("#a3").removeClass("disabled");
                break;
            case 4:
                $("#a4").removeClass("disabled");
                break;
            case 5:
                $("#a5").removeClass("disabled");
                break;
        }
    }
</script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.point.model.PointGoods.pojo.PointGoods" %>
<%@ page import="com.point.model.service.PointGoodsService" %>
<%
    PointGoodsService pointgoodsSvc = new PointGoodsService();
    List<PointGoods> list = pointgoodsSvc.getAlready();
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
                <div class="container px-4 px-lg-5 mt-5">
                    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                        <%@ include file="/back-end/pointgood/page1.jsp" %>
                        <c:forEach var="PointGoods" items="${list}" begin="<%=pageIndex%>"
                                   end="<%=pageIndex+rowsPerPage-1%>">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!--Product image -->
                                    <img src="${pageContext.request.contextPath}/back-end/pointgood/point.do?action=getPdImg&pdId=${PointGoods.pdId}"
                                         width=260px height=300px>
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
                    </div>
                </div>
            </section>
            <br>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <nav aria-label="Page navigation example   justify-content-center" class="m-5 ">
                    <ul class="pagination">
                        <%@ include file="/back-end/pointgood/page2.jsp" %>
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
    $("a:contains(💰point)").closest("a").addClass("active disabled topage");
    $("a:contains(💰點數商城)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu1").removeClass("collapse");
    $("#pageSubmenu1 a:contains(🔆點數商品)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>

    $(document).ready(function () {

        $("#pointgooditem").click(function () {
            //输入另一个页面的链接
            //我的跳转到controller中的toIntroduction这个方法中进行的页面跳转
            window.location.href = "pointItemPage.jsp";
        });
    });

</script>
</body>

</html>
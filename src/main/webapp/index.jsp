<%@page import="com.store.model.Store.pojo.Store" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Store store = (Store) request.getAttribute("store");
%>

<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>訪客首頁</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/CGA105G2/assets/css/carousel.css"/>


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
    </style>
</head>

<body>
<c:if test="${empId > 0}">
    <%@ include file="/back-end/01h/headerin.jsp" %>
</c:if>
<c:if test="${storeId > 0}">
    <%@ include file="/front-end/store/01h/headeronly.jsp" %>
</c:if>
<c:if test="${memId > 0}">
    <%@ include file="/front-end/Member/01h/headeronlyin.jsp" %>
</c:if>

<c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
    <%@ include file="/front-end/Member/01h/headeronlyout.jsp" %>
</c:if>
<!-- main -->
<section class="hero hero-fullscreen jarallax py-5" data-speed="0.2">
    <div class="hero-background ">
        <img src="/CGA105G2/assets/images/resterant.jpg" alt="hero background" class="jarallax-img"/>
        <div class="d-flex align-items-center justify-content-center py-md-10 h-100">

            <div class="card border-0 text-center mx-10 mx-lg-0 bg-white-90 w-50  mb-10 h-100 ">
                <!-- 搜尋 -->
                <div class="card-body text-black h-25 m-5 mb-14">
                    <h2 class="text-black fs-7 m-1">FoodMap</h2>
                    <c:if test="${empId > 0}">
                        <h1 style="color:#ef0000;"> ${empId}新年快樂 </h1>
                        <P>本日宣達事項:</P>
                        <ul>
                            <li>好好過年<3</li>
                        </ul>

                    </c:if>
                    <c:if test="${storeId > 0}">
                        <h1 style="color:#ef0000;"> ${StoreName}新年快樂 </h1>
                        寫專題484很辛苦ˊˋ加油!加油!
                    </c:if>
                    <c:if test="${memId > 0}">
                        <h4 style="color:#ef0000;"> ${memberName}~新年快樂，寫專題辛苦啦!</h4>
                    </c:if>

                    <c:if test="${storeId <=0&&empId<=0}">
                    <!-- ===========================form表單開始========================== -->
                    <form METHOD="post" ACTION="/CGA105G2/LonginServlet"
                          class="form-inline  justify-content-center mb-5">
                        <input name="storeName" class="form-control mr-sm-2 mb-1" type="text" placeholder="搜尋. ."
                               aria-label="Search" style="width: 440px;">
                        <select name="action"
                                style="height: 44px;padding: 10px 0px;margin-bottom: 3px; border: 1px solid sandybrown; font-weight: 600;color: rgb(86, 86, 86);">
                            <option value="byStoreName">店家</option>
                            <option value="byMemName">會員</option>
                        </select>
                        <button class="btn btn-outline-warning  my-sm-0 my-5 ml-5" type="submit">開始瀏覽</button>
                    </form>

                    <div>
                        <c:if test="${not empty errorMsgs}">
                            <ul>
                                <li style="color: red"> ${errorMsgs.error1} </li>
                                <li style="color: red"> ${errorMsgs.error2} </li>
                            </ul>
                        </c:if>
                    </div>
                    </c:if>
                </div>
                <c:if test="${storeId <=0&&empId<=0}">
                <!-- 廣告牆 -->
                <div id="carouselExampleCaptions" class="carousel slide card-body hero m-5 h-75"
                     data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="3"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="4"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="5"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="6"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="7"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="8"></li>
                    </ol>
                     <jsp:useBean id="adSvc" scope="page" class="com.advertise.model.service.AdvertiseService" /> 
                    
                    <div class="carousel-inner" style="height: 100%">
                    <c:forEach var="ad" items="${adSvc.status}" >
                        <div class="carousel-item  h-100 p-auto">
                            <img src="<%=request.getContextPath()%>/adServlet?action=getPhoto&adId=${ad.advId}" class="d-block  h-100 w-auto "
                                 style="min-width: auto ;margin:0 auto ;position: static !important" alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                            </div>           
                        </div>
                </c:forEach> 
                       
                    </div>

                    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button"
                       data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button"
                       data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- page content end -->
<c:if test="${empId > 0}">
    <%@ include file="/back-end/01h/footerin.jsp" %>
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
</c:if>

<c:if test="${empId ==0}">
<!-- footer start -->
    <%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
</c:if>

<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->

<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>

</html>
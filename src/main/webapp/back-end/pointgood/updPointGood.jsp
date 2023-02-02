<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>💰point</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"/>
    <!-- jquery 3.4.1  css -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">

    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


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

        .bg-warning {
            background-color: pink !important;
        }
    </style>
</head>

<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="p-4 pt-5">
                <ul class="list-unstyled components mb-5">
                    <li class="mb-5 mt-5">
                        <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">
                            <h3>🔻訂單管理</h3>
                        </a>
                        <ul class="collapse list-unstyled " id="pageSubmenu2">
                            <li>
                                <hr>
                                <a href="/CGA105G2/back-end/pointgood/backPointOrder.jsp"
                                   class="nav-link">🔆待出貨訂單</a>
                            </li>
                            <li>
                                <a href="/CGA105G2/back-end/pointgood/listPointOrder.jsp" class="nav-link">🔆訂單總覽</a>
                            </li>
                            <hr>
                            </li>
                        </ul>
                    </li>
                    <li class="mb-5 mt-5">
                        <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown">
                            <h3>🔻商品管理</h3>
                        </a>
                        <ul class="list-unstyled" id="pageSubmenu3">
                            <li>
                                <hr>
                                <a href="/CGA105G2/back-end/pointgood/addPointGood.jsp" class="nav-link">🔆新增商品</a>
                            </li>
                            <li><a href="/CGA105G2/back-end/pointgood/listPointGood.jsp" class="nav-link">🔆商品總覽</a>
                            </li>
                            <hr>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <div class="container my-20 col-6 ">
                <div class="card card-body shadow bg-cyan-20 "
                     style="border-radius: 20px;">

                    <h1 class="text-center mt-5">修改商品</h1>
                    <FORM METHOD="post" ACTION="point.do" name="form1"
                          enctype="multipart/form-data">
                        <div class="col-8 mx-auto">
                            <img src="${pageContext.request.contextPath}/back-end/pointgood/point.do?action=getPdImg&pdId=${param.pdId}"
                                 width=345px height=400px>
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">商品名稱:</label> <input
                                type="TEXT" name="pdName" value="${param.pdName}"/>${errorMsgs.pdName}
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">商品單價:</label> <input
                                type="TEXT" name="pdPrice" value="${param.pdPrice}"/>${errorMsgs.pdPrice}
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">商品介紹:</label> <input
                                type="TEXT" cols="40" rows="3" name="pdText" value="${param.pdText}"
                                class="form-control">${errorMsgs.pdText}
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">上傳圖片:</label> <input
                                type="file" name="pdImg" class="form-control ">
                        </div>

                        <div class="col-8 mx-auto my-10 text-center">
                            <label class="font-weight-bold fs-6 float-left mt-9">商品狀態
                                :</label>
                            <div class="radio-buttons-group mb-5 mx-auto text-center m-5 ">
                                <input id=pdStatus type="hidden" name="pdStatus" value=0>
                                <input type="button" class="btn btn-light bg-white fs-5" value="上架"
                                       onclick="already()">
                                <input type="button" class="btn btn-light bg-white fs-5 selected" value="下架"
                                       onclick="besold()">
                            </div>
                        </div>
                        <div class=" col-7 mx-auto  text-center">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="pdId" value="${param.pdId}">
                            <button type="submit"
                                    class="btn btn-warning btn-block btn-lg fs-5">送出
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>

</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>

<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->

<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
<script>
    const list = [];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list) {
        switch (e) {
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


<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>修改商品</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }

        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 450px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }

        table, th, td {
            border: 0px solid #CCCCFF;
        }

        th, td {
            padding: 1px;
        }
    </style>

</head>


</html>
<%@page import="com.subs.model.service.SubsService"%>
<%@page import="com.subs.model.Subscribe.pojo.Subscribe"%>
<%@page import="java.util.List"%>
<%@page import="com.followmem.model.FollowMem.pojo.FollowMem"%>
<%@page import="com.followmem.model.service.FollowMemService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
Integer memId = (Integer) request.getSession().getAttribute("memId");
Subscribe subscribe = new Subscribe();
SubsService subsService = new SubsService();
List<Subscribe> list = subsService.getAllByMemId(memId);
pageContext.setAttribute("list", list);

%>


<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <title>🗃️管理</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous" />

    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css" />
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css" />
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

        /* ==========訂閱button=============== */
        .button-like {
            transition: all ease 0.4s;
            cursor: pointer;
        }

        .button-like span {
            transition: all ease 0.4s;
        }

        .button-like:hover .aaa {
            color: #5273f7;
        }

        .liked {
            background-color: #5273f7;
            border-color: #5273f7;
        }

        .liked:focus {
            background-color: #5273f7;
        }

        .liked .aaa {
            color: #fefefe;
        }
    </style>
</head>

<body>
<c:if test="${memId > 0}">
    <!-- header start -->
    <%@ include file="/front-end/Member/01h/headerin.jsp" %>
    <!-- header end -->
</c:if>
<c:if test="${memId == null||memId <=0}">
    <!-- header start -->
    <%@ include file="/front-end/Member/01h/headerout.jsp" %>
    <!-- header end -->
</c:if>
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <c:if test="${memId > 0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
            <!-- nav end -->
        </c:if>
        <c:if test="${memId == null||memId <=0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin00.jsp" %>
            <!-- nav end -->
        </c:if>
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
                        
                <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">🔆我的最愛</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2">
                            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
                        </div>
                    </div>
                </div>
                <!-- ==================我的最愛開始======================= -->
                <div class="row d-flex" style="margin-top: 40px;">
                    <div class="col-12" style="display: flex;">
                        <div class="col-2"></div>
                        <div class="col-2 mr-10 ml-20">
                            <a href="/CGA105G2/front-end/Member/saveArt/selectSaveArtByStore.jsp">
                                <button type="button" class="btn btn-block btn-info active"
                                    style="height: 60px;font-size: 22px;">訂閱店家</button>
                            </a>
                        </div>

                        <div class="col-2 mr-10">
                            <a href="/CGA105G2/front-end/Member/saveArt/selectSaveArtByMember.jsp">
                                <button type="button" class="btn btn-block btn-primary"
                                    style="height: 60px;font-size: 22px;">追蹤會員</button>
                            </a>
                        </div>


                    </div>
                </div>
                <!-- ==============button切換頁面結束=============== -->
                <ol style="list-style: none;">
                    <!-- ==============表頭開始================== -->
                    <li>
                        <div class="row mt-10">
                            <div class="col-2"></div>
                            <div class="col-7 border d-flex"
                                style="text-align: center; height: 50px;background-color: rgb(216, 233, 253);justify-content: center;line-height: 50px;font-size: 25px;font-weight: 800;">
                                店家名單
                            </div>
                        </div>
                    </li>
                    <!-- ==============表頭結束================== -->
                    <% if (list == null || list.size() == 0) {			%>
                    <li>
                        <div class="row">
                            <div class="col-2"></div>
                            <div class="col-7 border d-flex"
                                style="text-align: center; height: 100px;justify-content: center;line-height: 50px;font-size: 25px;font-weight: 800;line-height:100px;">
                                暫無訂閱名單
                            </div>
                        </div>
                    </li>
                    <%} else{ %>
                    <c:forEach var="subscribe" items="${list}">
                    <li>
                        <div class="row">
                            <div class="col-2"></div>
                            <div class="col-md-1 border">
                                <div class="position-relative snipimage"
                                    style="height: 100px;text-align: center;line-height: 100px;">
                                    <img src="/assets/images/ex1.jpg"
                                        style="width: 100%;height: 70%;border: 3px solid rgb(255, 216, 87);">
                                </div>
                            </div>
                            <div class="col-md-5 border d-flex border-right-0">
                                <div class="mt-8">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <a href="/CGA105G2/LonginServlet?action=StorePage&SearchstoreId=${subscribe.store.storeId}">
                                            <span class="mb-1" style="font-size:22px;font-weight: 700;">${subscribe.store.storeName}</span>
                                        </a>
                                    </div>
                                    <div class="total_information" style="font-size: 17px;">
                                        <div>
                                            ${subscribe.store.storeCity}${subscribe.store.storeDistrict}${subscribe.store.storeAddress}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- =====================追蹤button開始=========================== -->
                            
                            <div class="col-1 d-flex border border-left-0 align-items-center">
                            <form method="post" action="/CGA105G2/MyFavoriteServlet" name="" >
                            <input type="hidden" name="storeId" value="${subscribe.storeId}" id="storeId">
                        	<input type="hidden" name="memId" value="${subscribe.memId}" id="memId">
                                <button class="button button-like" type="submit"
                                    style="border: 0cm;width: 100%;height: 45px;border-radius: 5px;font-weight: 600;">
                                    <span class="aaa">已訂閱</span>
                                </button>
                                <input type="hidden" name="action" value="deleteSubs">
                            </form>
                            </div>
                            
                            <!-- =====================追蹤button結束=========================== -->

                        </div>
                        
                    </li>
					</c:forEach>
                    <%}; %>
                    <!-- =============表格結尾開始=============== -->
                    <li>
                        <div class="row">
                            <div class="col-2">

                            </div>
                            <div class="col-7 border mb-10" style="text-align: center; height: 30px;background-color: rgb(216, 233, 253);">

                            </div>
                        </div>
                    </li>
                </ol>
                <canvas class="my-4 w-100" id="myChart" width="900" height="100"></canvas>
            </main>
        </div>
    </div>
        <!-- =================我的最愛結束======================= -->


    <!-- main -->

    <!-- footer start -->
    <section class="footer bg-warning" style=" width: 100%; position:relative; bottom:0; top:30%">
        <div class="container">
            <!-- 三張小圖 -->
            <div class="d-flex align-items-stretch justify-content-md-center py-10">
                <!-- 地址 -->
                <div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
                    <div class="card-body py-17 px-10 text-center">
                        <div class="card-icon mb-6"><i class="material-icons">map</i></div>
                        <div
                            class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
                            Our address
                        </div>
                        <p class="mb-0 text-body">桃園市中壢區復興路46號9樓<br />Hollow Lane. NY 11706.</p>
                    </div>
                </div>
                <!-- 電話 -->
                <div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
                    <div class="card-body py-17 px-10 text-center">
                        <div class="card-icon mb-6"><i class="material-icons">phone</i></div>
                        <div
                            class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
                            連絡電話
                        </div>
                        <p class="mb-0 text-body">0800-087-087</p>
                    </div>
                </div>
                <!-- 營業時間 -->
                <div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
                    <div class="card-body py-17 px-10 text-center">
                        <div class="card-icon mb-6"><i class="material-icons">access_time</i></div>
                        <div
                            class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
                            營業時間
                        </div>
                        <p class="mb-0 text-body">11:00 AM - 9:00 PM</p>
                    </div>
                </div>
            </div>
            <!-- 跳轉到社群連結 -->
            <div class="d-flex align-items-center justify-content-center">
                <a href="home.html" class="footer-brand">FoodMap</a>
                <div class="brand-icons-list ml-10 ml-sm-20">
                    <!-- FB圖案 -->
                    <a href="#" class="brand-icon brand-icon-circle brand-icon-facebook">
                        <i class="fa fa-facebook-f"></i>
                    </a>
                    <!-- twitter圖案 -->
                    <a href="#" class="brand-icon brand-icon-circle brand-icon-twitter">
                        <i class="fa fa-twitter "></i>
                    </a>
                    <!-- ig圖案-->
                    <a href="#" class="brand-icon brand-icon-circle brand-icon-instagram">
                        <i class="fa fa-pinterest-p"></i>
                    </a>
                </div>
            </div>
            <!-- 頁底文字 -->
            <small class="d-flex align-items-center justify-content-center" style="margin:0px -10px 0px -10px;">
                TibaMe CGA105_2 MapFood
                <a href="#" data-toggle="smooth-scroll" data-target="#page-start-anchor">
                    <i class="material-icons text-black">arrow_upward</i>
                </a>
            </small>
        </div>
    </section>
    <!-- footer end -->

    <script src="/assets/js/vendor.js"></script>
    <script src="/assets/js/polyfills.js"></script>
    <script src="/assets/js/app.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>

    <!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->

    <!-- Bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    <script>
        $(document).ready(function () {

            new ClipboardJS('.btn');

        });
        const list = [
            {
                CODE_ID: 1,
                STORE_NAME: '麥當勞-中正店',
                CODE_OFF: 20,
                CODE_NUM: 'MYC20',
                CODE_NTIME: 2022 - 12 - 31
            },
            {
                CODE_ID: 2,
                STORE_NAME: '阿雄麵店',
                CODE_OFF: 35,
                CODE_NUM: 'YC35',
                CODE_NTIME: 2022 - 12 - 30
            },
            {
                CODE_ID: 3,
                STORE_NAME: '鑫艷',
                CODE_OFF: 2000,
                CODE_NUM: 'HaHa0101',
                CODE_NTIME: '2023-01-01'
            }
        ];
        render(list);
        function render(list) {
            // 定義變數，在使用變數
            const codetbody = document.querySelector('.code_tbody');
            codetbody.innerHTML = '';
            for (let item of list) {
                codetbody.innerHTML += `
             <tr>
              <td><button type="button" class="btn btn-dark p-0 " data-clipboard-action="copy" data-clipboard-target="#clipboardExample${item.CODE_ID}">Copy</button></td>
              <td>${item.STORE_NAME}</td>
              <td>$${item.CODE_OFF}</td>
              <td id="clipboardExample${item.CODE_ID}">${item.CODE_NUM}</td>
              <td>${item.CODE_NTIME}</td>
            </tr>
`;
            }
        }
    </script>
    <!-- Vue -->
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script>
        const { createApp } = Vue;

        createApp({
            data() {
                return {
                    message: "Hello Vue!",
                };
            },
        }).mount("#app");
    </script>
    <!-- ==========================button特效開始======================= -->
    <script>
        const button = document.querySelectorAll('.button');
        for (let i = 0; i < button.length; i++) {
            button[i].addEventListener('click', function () {
                button[i].classList.toggle('liked')
            })
        }

        const aaa = document.querySelectorAll('.aaa');

        for (let i = 0; i < button.length; i++) {
            button[i].addEventListener('click', function () {
                if (aaa[i].innerHTML == "已訂閱")
                    aaa[i].innerHTML = "訂閱";
                else
                    aaa[i].innerHTML = "已訂閱";
            })
        }

    </script>
    <!-- ==========================button特效結束======================= -->
    <!-- stickey bar: -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>

    <script>
        let a = new StickySidebar("#sidebar", {
            topSpacing: 40,
            bottomSpacing: 20,
            containerSelector: ".container",
            innerWrapperSelector: ".sidebar__inner"
        });
    </script>
</body>

</html>
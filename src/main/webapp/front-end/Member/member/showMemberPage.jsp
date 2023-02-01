<%@page import="com.member.model.Member.pojo.Member"%>
<%@page import="com.member.model.service.MemberService"%>
<%@page import="com.art.model.service.ArtService"%>
<%@page import="com.art.model.Article.pojo.Article"%>
<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<%
	
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
        <script src="https://kit.fontawesome.com/2c6d23848b.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css" />
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css" />
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
<!--     下面那條不能刪 -->
    <link rel="stylesheet" href="/CGA105G2/assets/css/profile.css">   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--     <link rel="stylesheet" href="/assets/css/carousel.css" /> -->


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

        /* ==========收藏button=============== */
        button.liked {
            color: #0571ed;
        }

        button.liked i {
            animation: anim 0.5s ease-in-out;
            -webkit-animation: anim 0.5s ease-in-out;
        }

        @keyframes anim {
            100% {
                transform: rotate(-15deg) scale(1.3);
                -webkit-transform: rotate(-15deg) scale(1.3);
                -moz-transform: rotate(-15deg) scale(1.3);
                -ms-transform: rotate(-15deg) scale(1.3);
                -o-transform: rotate(-15deg) scale(1.3);
                filter: blur(0.5px);
                -webkit-filter: blur(0.5px);
            }
        }

        .fa-heart-o {
            color: red;
            cursor: pointer;

        }

        .fa-heart {
            color: red;
            cursor: pointer;

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
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4  bg-cyan-20">
              
                <div class="container my-5">

                    <div class="profile-header">
                        <div class="profile-header-cover"
                            style="background: url(images/ex2.jpg);"></div>
                        <div class="profile-header-content">
                            <div class="profile-header-img mb-1">
                            <c:if test="${not empty member2.memPic}">
                                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member2.memId}" alt="" />
                            </c:if>
                            <c:if test="${empty member2.memPic}">
                            	 <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg" alt="" />
                            </c:if>
                            </div>
                            <div class="profile-header-info">
                                <div style="display: flex;">
                                <h3 class="m-t-sm mt-5" style="font-weight: 1000;font-size: 33px;">${member2.memName}</h3>
                                <p class="m-t-sm mt-7 ml-4" style="color: rgb(215, 235, 68);">@${member2.memAcc}</p> 
                            </div>
                            <div style="display:flex;">
                    <c:if test="${followlist.size() == 0}">
                            <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                       		 <input type="hidden" name="memId1" value="${member1.memId}" id="memId1">
                        	<input type="hidden" name="memId2" value="${member2.memId}" id="memId2">
                            <button type="submit" class="btn btn-sm btn-primary mb-4" style="font-size: 17px;">追蹤會員</button> 
                            <input type="hidden" name="action" value="insertFollow">
                            <input type="hidden" name="SearchMemberId" value="${member2.memId}" id="SearchMemberId">
                            </form>
                    </c:if>
                    
                    <c:if test="${followlist.size() != 0}">
                            <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                       		 <input type="hidden" name="memId1" value="${member1.memId}" id="memId1">
                        	<input type="hidden" name="memId2" value="${member2.memId}" id="memId2">
                            <button type="submit" class="btn btn-sm btn-warning mb-4" style="font-size: 17px;">取消追蹤</button> 
                            <input type="hidden" name="action" value="deleteFollowbyPage">
                            <input type="hidden" name="SearchMemberId" value="${member2.memId}" id="SearchMemberId">
                            </form>
                    </c:if>
                            <button class="btn btn-sm btn-primary mb-4 ml-5" style="font-size: 17px;">聊天室</button>
                            </div>    
                            </div>
                          
                        </div>


                    </div>
                    <div class="container bg-white mt-10 p-8">
                    
                        <div class="row">
                            <div class="col-md-12"
                                style="height: 100px;font-size: 20px;font-weight: 800;margin-top: 5px;">
                                自我簡介:

                                <div>

                                    ${member.memText} 
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- POST1 -->
                   	<!--                     這邊要開始for each -->
                   	
                <c:if test="${list.size() == 0 }">
                <div class="container bg-white mt-10 p-8">
                <div class="row">
                        <div class="col-md-12"
                             style="height: 100px;font-size: 40px;font-weight: 800;margin-top: 10px;text-align: center;line-height: 100px;color: rgb(91, 91, 91);">
                            此會員目前暫無貼文
                </div>
                </div>
                </div>
                </c:if>
                <c:if test="${list.size() != 0 }">
                   	<c:forEach var="article" items="${list}">
                    <div class="container bg-white mt-10 p-8">
                        <div class="row">
                            <div class="col-md-12" style="font-size: 20px;font-weight: 800;margin-top: 5px;">
                                 <!-- ====個人圖片==== -->
                                <div class="postmember_info" style="display: flex;">
                                    <div class="postmember_img">
                                    <c:if test="${not empty member2.memPic}">
                                        <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member2.memId}"
                                            style="width:70px ; height:65px;border-radius: 80%;border: 1px solid rgb(255, 216, 87);">
        							</c:if>
        							<c:if test="${empty member2.memPic}">
                            	 <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg" alt="" style="width:70px ; height:65px;border-radius: 80%;border: 1px solid rgb(255, 216, 87);">
                            		</c:if>
                                    </div>
                                    <div class="postmember_text mt-3" style="margin-left: 5px;line-height: 26px;">
        
                                        <span class="postmember_name" style="font-size: 25px;">
                                            ${member2.memName}
                                        </span>
                                        <div>
                                            <p style="font-size: 14px;color: rgb(104, 104, 104);"><fmt:formatDate value="${article.artTime}" pattern="yyyy-MM-dd" /></p>
                                        </div>
                                    </div>
                                  <!-- ==================評分跟店家頭像===================== -->
                                    <div class="ml-5"  style="margin-top: 14px;" >
                                        <span style="font-size: 20px;padding: 5px 15px;border-radius:15px ;background-color: rgb(255, 112, 60);">
                                        ${article.artScore} <i class="fa-solid fa-star" style="color: rgb(249, 249, 106);"></i>

                                        </span>
                                    </div>
                                    <div class="poststore_info ml-8" style="display: flex;">
                                        <div class="poststore_img">
                                            <img src="/CGA105G2/assets/images/ex1.jpg"
                                                style="width:65px ; height:60px;border: 1px solid rgb(255, 216, 87);">
            
                                        </div>
                                        <div class="poststore_text" style="margin-left: 5px;align-items: center;display: flex;">
            							<a href="${pageContext.request.contextPath}/LonginServlet?action=StorePage&SearchstoreId=${article.store.storeId}">
                                            <span class="post_name" style="font-size: 30px;font-weight: 1000;">
                                                ${article.store.storeName}
                                            </span>
            							</a>
                                        </div>
                                    </div>


                                    <!-- ===============評分跟店家頭像和form表單在這============================ -->

                                </div>
                                <!-- ====個人圖片結束==== -->
                                <h2 class="mt-6"style="font-weight: 1000;" >${article.artHeader}</h2>
                                <div>
                                    <p>${article.artText}</p>
                                    <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberArticlePhoto&artId=${article.artId}" style="max-width:600px;max-height:450px">
                                </div>
                                <ul class="profile-header-tab nav nav-tabs mt-5">
                                    
                                    <li class="nav-item">
                                    <a href="https://line.me/R/msg/text/?${article.artHeader}%0D%0A/CGA105G2/front-end/Member/art/listArt.jsp">
                                        <button class=" btn btn-outline-primary align-items-center"
                                            style="height: 46px; padding: 5px; border-radius: 0%;font-size: 20px;font-weight: 1000">
                                            <i class="material-icons">share</i>
                                            分享到Line
                                        </button>
                                        </a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    </c:if>
				<!--                     這邊要結束for each -->

                </div>
                <!-- =================我的最愛結束======================= -->

            </main>
        </div>
    </div>
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
        function liked() {
            const element = document.getElementById("like");
            element.classList.toggle("liked");
        }

        $(document).ready(function () {
            $("#heart").click(function () {
                if ($("#heart").hasClass("liked")) {
                    $("#heart").html('<i class="fa fa-heart-o m-5" aria-hidden="true"><span class="icon ml-2">收藏</span></i>');
                    $("#heart").removeClass("liked");
                } else {
                    $("#heart").html('<i class="fa fa-heart m-5" aria-hidden="true" ><span class="icon ml-2">收藏</span></i>');
                    $("#heart").addClass("liked");
                }
            });
        });
    </script>
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
                if (aaa[i].innerHTML == "已收藏")
                    aaa[i].innerHTML = "收藏";
                else
                    aaa[i].innerHTML = "已收藏";
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
<%@ page import="com.member.model.Member.pojo.Member" %>
<%@ page import="com.member.model.service.MemberService" %>
<%@ page import="com.art.model.service.ArtService" %>
<%@ page import="com.art.model.Article.pojo.Article" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"/>
    <script src="https://kit.fontawesome.com/2c6d23848b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <!--     下面那條不能刪 -->
    <link rel="stylesheet" href="/CGA105G2/assets/css/profile.css">
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
                         style="background: url(../webapp/assets/images/ex2.jpg);"></div>
                    <div class="profile-header-content">
                        <div class="profile-header-img mb-1">
                            <c:if test="${not empty member2.memPic}">
                                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member2.memId}"
                                     alt=""/>
                            </c:if>
                            <c:if test="${empty member2.memPic}">
                                <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                     alt=""/>
                            </c:if>
                        </div>
                        <div class="profile-header-info">
                            <div style="display: flex;">
                                <h3 class="m-t-sm mt-5"
                                    style="font-weight: 1000;font-size: 33px;">${member2.memName}</h3>
                                <p class="m-t-sm mt-7 ml-4" style="color: rgb(215, 235, 68);">@${member2.memAcc}</p>
                            </div>
                            <div style="display:flex;">
                                <c:if test="${followlist.size() == 0}">
                                    <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                                        <input type="hidden" name="memId1" value="${member1.memId}">
                                        <input type="hidden" name="memId2" value="${member2.memId}">
                                        <button type="submit" class="btn btn-sm btn-primary mb-4"
                                                style="font-size: 17px;">追蹤會員
                                        </button>
                                        <input type="hidden" name="action" value="insertFollow">
                                        <input type="hidden" name="SearchMemberId" value="${member2.memId}">
                                    </form>
                                </c:if>
                                <c:if test="${followlist.size() != 0}">
                                    <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                                        <input type="hidden" name="memId1" value="${member1.memId}">
                                        <input type="hidden" name="memId2" value="${member2.memId}">
                                        <button type="submit" class="btn btn-sm btn-warning mb-4"
                                                style="font-size: 17px;">取消追蹤
                                        </button>
                                        <input type="hidden" name="action" value="deleteFollowbyPage">
                                        <input type="hidden" name="SearchMemberId" value="${member2.memId}">
                                    </form>
                                </c:if>
                                <button class="btn btn-sm btn-primary mb-4 ml-5" style="font-size: 17px;">聊天室
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container bg-white mt-10 p-8">
                    <div class="row">
                        <div class="col-md-12" style="height: 100px;font-size: 20px;font-weight: 800;margin-top: 5px;">
                            自我簡介:
                            <div>
                                ${member.memText}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- POST1 -->
                <!-- 這邊要開始for each -->
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
                                                <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                                     alt=""
                                                     style="width:70px ; height:65px;border-radius: 80%;border: 1px solid rgb(255, 216, 87);">
                                            </c:if>
                                        </div>
                                        <div class="postmember_text mt-3" style="margin-left: 5px;line-height: 26px;">
                                        <span class="postmember_name" style="font-size: 25px;">
                                                ${member2.memName}
                                        </span>
                                            <div>
                                                <p style="font-size: 14px;color: rgb(104, 104, 104);"><fmt:formatDate
                                                        value="${article.artTime}" pattern="yyyy-MM-dd"/></p>
                                            </div>
                                        </div>
                                        <!-- ==================評分跟店家頭像===================== -->
                                        <div class="ml-5" style="margin-top: 14px;">
                                        <span style="font-size: 20px;padding: 5px 15px;border-radius:15px ;background-color: rgb(255, 112, 60);">
                                        ${article.artScore} <i class="fa-solid fa-star"
                                                               style="color: rgb(249, 249, 106);"></i>
                                        </span>
                                        </div>
                                        <div class="poststore_info ml-8" style="display: flex;">
                                            <div class="poststore_img">
                                                <img src="/CGA105G2/assets/images/ex1.jpg"
                                                     style="width:65px ; height:60px;border: 1px solid rgb(255, 216, 87);">
                                            </div>
                                            <div class="poststore_text"
                                                 style="margin-left: 5px;align-items: center;display: flex;">
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
                                    <h2 class="mt-6" style="font-weight: 1000;">${article.artHeader}</h2>
                                    <div>
                                        <p>${article.artText}</p>
                                        <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberArticlePhoto&artId=${article.artId}"
                                             style="max-width:600px;max-height:450px">
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
                <!--這邊要結束for each -->
            </div>
            <!-- =================我的最愛結束======================= -->
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
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
<!-- Vue -->
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script>
    const {createApp} = Vue;
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
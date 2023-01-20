<%@page import="com.art.model.Article.pojo.Article" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    Article article = (Article) request.getAttribute("article");
%>

<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>🗃️管理</title>
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

        @media ( min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }

        /* ========button的樣式=============== */
        .btn-secondary {
            font-family: "Noto Sans TC", sans-serif;
            font-size: 18px;
            letter-spacing: .05em;
            border-radius: .75em;
            font-weight: 500;
            color: #FFFFFF;
            background-color: #164570;
            padding: 5px 13px;
            cursor: pointer;
        }

        /* ============星星的css============== */
        .rating-wrapper {
            direction: rtl !important;
        }

        .storescorelabel {
            color: #E1E6F6;
            cursor: pointer;
            font-size: 32px;
            padding: 8px 3px;
            transition: color 0.5s;
        }

        .storescore {
            height: 100%;
            width: 100%;
            display: none;
        }

        .storescorelabel:hover, .storescorelabel:hover ~ .storescorelabel,
        .storescore:checked ~ label {
            color: #ffe223;
        }

        /* ==================上傳圖片的css==================== */
        .image-upload input {
            display: none;
        }

        .upload-field {
            cursor: pointer;
        }

        .upload-field .file-thumbnail {
            cursor: pointer;
            border: 1px dashed #BBD9EC;
            border-radius: 11px;
            text-align: center;
            padding: 10px 0px;
            width: 100px;
            height: 100px;
        }

        .upload-field .file-thumbnail img {
            width: 50px;
        }

        .upload-field .file-thumbnail h3 {
            font-size: 15px;
            color: #000000;
            font-weight: 1000;
            margin-top: 10px;
        }

        /* ==================上傳圖片css結束======================= */
    </style>
</head>

<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆發文頁面</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
                    </div>
                </div>
            </div>
            <!-- =======================會員頭像======================= -->

            <div class="row justify-content-center">
                <div class="col-7 mb-10">
                    <div class="postmember_info"
                         style="display: flex; margin-top: 30px;">
                        <div class="postmember_img">
                            <img src="/CGA105G2/assets/images/men.png"
                                 style="width: 65px; height: 60px; border-radius: 80%; border: 1px solid rgb(255, 216, 87);">

                        </div>
                        <div class="postmember_text" style="margin-left: 5px;">

								<span class="postmember_name" style="font-size: 20px;">
									江詩傑 </span>
                            <div>
                                <time>2022-12-23</time>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <!-- ==================發文開始======================= -->
            <div class="row justify-content-center">
                <div class="col-7 mb-10 shadow "
                     style="padding: 30px; border: 3px solid rgba(208, 208, 208, 0.644); border-radius: 20px;">
                    <div class="poststore_info"
                         style="display: flex; margin-top: 10px;">
                        <div class="poststore_img">
                            <img src="/CGA105G2/assets/images/ex1.jpg"
                                 style="width: 65px; height: 60px; border: 1px solid rgb(255, 216, 87);">

                        </div>
                        <div class="poststore_text"
                             style="margin-left: 5px; align-items: center; display: flex;">

								<span class="post_name"
                                      style="font-size: 30px; font-weight: 1000;"> 劉媽媽小吃店 </span>

                        </div>
                    </div>
                    <!-- ===================店家評分星星================== -->
                    <!-- star rating -->
                    <form method="post" action="ArtServlet" name="form1" enctype="multipart/form-data">
                        <div class="rating-wrapper pt-3">
                            <div>
                                <input type="hidden" name="memId" value="2">
                                <input type="hidden" name="storeId" value="5">
                                <span
                                        style="font-size: 22px; font-weight: 600; background-color: antiquewhite; margin-left: 20px; line-height: 60px;">(5
										/ 1)</span>
                                <!-- star 5 -->
                                <input type="radio" id="5-star-rating" class="storescore"
                                       name="artScore" value="5"> <label
                                    for="5-star-rating" class="star-rating storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>

                                <!-- star 4 -->
                                <input type="radio" id="4-star-rating" class="storescore"
                                       name="artScore" value="4"> <label
                                    for="4-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>

                                <!-- star 3 -->
                                <input type="radio" id="3-star-rating" class="storescore"
                                       name="artScore" value="3"> <label
                                    for="3-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>

                                <!-- star 2 -->
                                <input type="radio" class="storescore" id="2-star-rating"
                                       name="artScore" value="2"> <label
                                    for="2-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>

                                <!-- star 1 -->
                                <input type="radio" id="1-star-rating" class="storescore"
                                       name="artScore" value="1"> <label
                                    for="1-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label> <span
                                    style="font-size: 22px; font-weight: 600; background-color: antiquewhite;">:評分</span>
                            </div>
                        </div>


                        <!-- ==============標記tag================== -->
                        <div class="tag" style="margin-top: 15px; display: flex;">
                            <b><span
                                    style="font-size: 16px; padding: 8px 15px; border-radius: 15px; margin-right: 5px; background-color: rgb(82, 206, 156); color: white;">#寵物友善店家</span></b>
                            <b><span
                                    style="font-size: 16px; padding: 8px 15px; border-radius: 15px; margin-right: 5px; background-color: rgb(82, 206, 156); color: white;">#銀髮族友善店家</span></b>
                        </div>
                        <!-- ===========輸入欄位開始================ -->
                        <div style="margin-bottom: 30px;">

                            <div>
                                <input type="text" name="artHeader" id="tb22_title" placeholder="文章標題"
                                       value="<%= (article==null)? "" : article.getArtHeader()%>"
                                       class="form-control" style="width: 100%; margin: 20px 0px;">
                            </div>
                            <div class="mb-5">
                                <input type="text" name="artText" id="tb22_comment"
                                       value="<%= (article==null)? "" : article.getArtText()%>"
                                       placeholder="給店家的評語" style="width: 100%; height: 150px;"
                                       class="form-control">
                            </div>
                        </div>
                        <!-- ==============插入圖片開始================= -->
                        <div class="image-upload">
                            <input type="file" name="artImg" id="logo"
                                   value="<%= (article==null)? "" : article.getArtImg()%>"
                                   onchange="fileValue(this)"> <label for="logo"
                                                                      class="upload-field" id="file-label">
                            <div class="file-thumbnail">
                                <img id="image-preview"
                                     src="https://www.btklsby.go.id/images/placeholder/basic.png"
                                     alt="">
                                <h3 id="filename">上傳圖片</h3>

                            </div>
                        </label>
                        </div>
                        <!-- ==================發表評論開始====================== -->
                        <div class="post_btn"
                             style="display: flex; justify-content: flex-end">
                            <button type="reset" class="btn-secondary" @click="backHome"
                                    @submit.prevent style="background-color: gray;">取消
                            </button>
                            <button type="submit" class="btn-secondary" @click="uploadData"
                                    @submit.prevent style="margin-left: 10px">發表評論
                            </button>
                            <input type="hidden" name="action" value="insertArt">
                        </div>
                    </form>
                    <%-- 錯誤表列 --%>
                    <c:if test="${not empty errorMsgs}">
                        <p style="text-align: center; color: red">錯誤表列</p>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <%-- 錯誤表列 --%>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🌟)").closest("a").addClass("active disabled topage");
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>

<script>
    $(document).ready(function () {

        new ClipboardJS('.btn');

    });
</script>
<!-- ==================上傳圖片js=================== -->
<script>
    function fileValue(value) {
        const path = value.value;
        const extenstion = path.split('.').pop();
        if (extenstion == "jpg" || extenstion == "svg" || extenstion == "jpeg" || extenstion == "png" || extenstion == "gif" || extenstion == "JPG" || extenstion == "PNG" || extenstion == "JPEG") {
            document.getElementById('image-preview').src = window.URL.createObjectURL(value.files[0]);
            const filename = path.replace(/^.*[\\\/]/, '').split('.').slice(0, -1).join('.');
            document.getElementById("filename").innerHTML = filename;
        } else {
            alert("檔案格式錯誤，請上傳圖片格式為JPG、PNG、JPEG、SVG")
        }
    }

    // =====================上傳圖片js結束============================
</script>

<!-- stickey bar: -->
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>

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
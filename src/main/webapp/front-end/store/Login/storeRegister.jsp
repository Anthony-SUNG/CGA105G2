<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<title>🗃️管理</title>
<!-- Bootstrap css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css" />
<link rel="stylesheet" href="/CGA105G2/assets/css/style.css" />
<link rel="stylesheet" href="/CGA105G2/assets/custom.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


<style>
body {
	height: 100%;
	/* background-color: rgb(223, 211, 211); */
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

a {
	color: black;
}
</style>
</head>

<body>
	<div id="page-start-anchor"></div>

	<!-- header start -->
	<section class="header header-fixed-xl">
		<div class="header-main bg-warning">
			<div class="container" style="max-width: 1400px">
				<div class="row" style="max-width: 1400px">
					<nav class="navbar navbar-expand-lg navbar-light fs-md-6"
						id="header-navbar">
						<!-- @*Navbar(白色)*@ -->
						<div>
							<a class="navbar-brand font-weight-bold" href="Member.html">
								<img src="/CGA105G2/assets/images/Logo.PNG"
								style="width: 100px; height: 100px" alt="" />
							</a>
						</div>
						<form class="form-inline my-2 my-md-0 bg-white p-1 "
							style="border-radius: 30px;">
							<div class="single-icon" data-toggle="tooltip" title=""
								data-original-title="search" style="border: 0;">
								<i class="material-icons">search</i>
							</div>

							<input class="form-control " type="text" placeholder="Search"
								style="border: 0; border-radius: 30px;">
						</form>

						<!--            ps-->
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto">
								<div class="navbar-spacer"></div>
								<div class="navbar-spacer"></div>
								<li class="nav-item"><a class="nav-link text-uppercase"
									data-toggle="none" href="./ContactUs.html"> 📭聯繫我們 </a></li>

								<li class="nav-item"><a class="nav-link text-uppercase"
									data-toggle="none" href="./MemberLogin.html"> 💰point </a></li>
								<li class="nav-item"><a class="nav-link text-uppercase"
									data-toggle="none" href="./ContactUs.html"> 🌟 </a></li>
								<li class="nav-item"><a class="nav-link text-uppercase"
									data-toggle="none" href="./Waiting.html"> 🛒 </a></li>
								<li class="nav-item"><a
									class="nav-link text-uppercase active disabled"
									data-toggle="none" style="color: #216a51 !important;"
									href="./AboutUS.html"> 🗃️管理 </a></li>
								<li class="nav-item"><a class="nav-link text-uppercase"
									data-toggle="none" href="home.html"> 🚪Sing out </a></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- header end -->

	<!-- main -->

	<!-- 上方標題(可刪) -->



	<!-- main -->
	<section class="jumbotron jumbotron-fluid mb-17 bg-yellow-20">
		<div class="container">
			<h1 class="jumbotron-title ">店家註冊</h1>
		</div>
	</section>
	<div class="container  mb-17 p-4">
		<section class="shadow"
			style="background-color: #2779e2; border-radius: 35px;">
			<div class="container p-4">
				<div
					class="row d-flex justify-content-center align-items-center h-100 p-4">
					<div class="col-xl-9">

						<h1 class="text-white text-center m-5">Store Registration</h1>

						<div class="card" style="border-radius: 15px;">
							<div class="card-body">



								<Form method="post"
									action="${pageContext.request.contextPath}/LonginServlet"
									name="addfrom">
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">店家名稱</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text"disabled class="form-control form-control-lg"
												id="memberstore-storename" name="STORE_NAME" value=${ Store.storeName } />

										</div>
									</div>
									<hr class="mx-n3">

									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">帳號名稱</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												id="memberstore-accountid" name="STORE_ACC" />

										</div>
									</div>

									<hr class="mx-n3">

									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">密碼</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												id="memberstore-password" name="STORE_PWD" />

										</div>
									</div>

									<hr class="mx-n3">
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">確認密碼</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												id="memberstore-confirmpassword" name="STORE_PWD2" />

										</div>
									</div>

										<hr class="mx-n3">

									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">店家電話</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												id="memberstore-phonenumber" name="STORE_PHONE1"  value=${ Store.storePhone1 } />

										</div>
									</div>



									<hr class="mx-n3">
									<div class="row align-items-center py-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">申請人姓名</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												placeholder="Holder's Name" id="memberstore-name" name="STORE_COM_ADDRESS" />

										</div>
									</div>
									<hr class="mx-n3">
									<div class="row align-items-center py-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">申請人電話</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												placeholder="Holder's Phone" id="memberstore-phone" name="STORE_PHONE2" />

										</div>
									</div>
									<hr class="mx-n3">
									<div class="row align-items-center py-3">
										<div class="col-md-3 ps-5">

											<h3 class="mb-0">身分證字號</h3>

										</div>
										<div class="col-md-9 pe-5">

											<input type="text" class="form-control form-control-lg"
												placeholder="Holder's Identity Card"
												id="memberstore-identitycard" name="STORE_TW_ID" />

										</div>
									</div>

									<hr class="mx-n3">


									<hr class="mx-n3">
									<div class="px-5 py-4 ">
									<input type="hidden" name="action" value="inserts">
										<button type="submit"
											class="btn btn-block btn-primary btn-lg fs-5">Send
											application</button>
									</div>
								</FORM>
							</div>
						</div>

					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- to the top  -->
	<a
		class="d-block btn btn-outline-danger  position-fixed position-bottom-10  position-right-10 text-center"
		href="#" data-toggle="smooth-scroll" data-target="#page-start-anchor"
		style="z-index: 9; border-radius: 50%;"> <i
		class="material-icons text-black ">arrow_upward</i>
	</a>

	<!-- main -->

	<!-- footer start -->
	<section class="footer bg-warning"
		style="width: 100%; position: relative; bottom: 0; top: 30%">
		<div class="container">
			<!-- 三張小圖 -->
			<div
				class="d-flex align-items-stretch justify-content-md-center py-10">
				<!-- 地址 -->
				<div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
					<div class="card-body py-17 px-10 text-center">
						<div class="card-icon mb-6">
							<i class="material-icons">map</i>
						</div>
						<div
							class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
							Our address</div>
						<p class="mb-0 text-body">
							桃園市中壢區復興路46號9樓<br />Hollow Lane. NY 11706.
						</p>
					</div>
				</div>
				<!-- 電話 -->
				<div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
					<div class="card-body py-17 px-10 text-center">
						<div class="card-icon mb-6">
							<i class="material-icons">phone</i>
						</div>
						<div
							class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
							連絡電話</div>
						<p class="mb-0 text-body">0800-087-087</p>
					</div>
				</div>
				<!-- 營業時間 -->
				<div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
					<div class="card-body py-17 px-10 text-center">
						<div class="card-icon mb-6">
							<i class="material-icons">access_time</i>
						</div>
						<div
							class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
							營業時間</div>
						<p class="mb-0 text-body">11:00 AM - 9:00 PM</p>
					</div>
				</div>
			</div>
			<!-- 跳轉到社群連結 -->
			<div class="d-flex align-items-center justify-content-center">
				<a href="home.html" class="footer-brand">FoodMap</a>
				<div class="brand-icons-list ml-10 ml-sm-20">
					<!-- FB圖案 -->
					<a href="#"
						class="brand-icon brand-icon-circle brand-icon-facebook"> <i
						class="fa fa-facebook-f"></i>
					</a>
					<!-- twitter圖案 -->
					<a href="#" class="brand-icon brand-icon-circle brand-icon-twitter">
						<i class="fa fa-twitter "></i>
					</a>
					<!-- ig圖案-->
					<a href="#"
						class="brand-icon brand-icon-circle brand-icon-instagram"> <i
						class="fa fa-pinterest-p"></i>
					</a>
				</div>
			</div>
			<!-- 頁底文字 -->
			<small class="d-flex align-items-center justify-content-center"
				style="margin: 0px -10px 0px -10px;"> TibaMe CGA105_2
				MapFood <a href="#" data-toggle="smooth-scroll"
				data-target="#page-start-anchor"> <i
					class="material-icons text-black">arrow_upward</i>
			</a>
			</small>
		</div>
	</section>
	<!-- footer end -->

	<script src="/CGA105G2/assets/js/vendor.js"></script>
	<script src="/CGA105G2/assets/js/polyfills.js"></script>
	<script src="/CGA105G2/assets/js/app.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>
	<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

	<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->

	<!-- Bootstrap js -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
		crossorigin="anonymous"></script>
	<script>
    $(document).ready(function () {

      new ClipboardJS('.btn');

    });
   
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
	<script>
    $(document).ready(function () {
      $("#email").val("早安")
    })
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

</body>

</html>
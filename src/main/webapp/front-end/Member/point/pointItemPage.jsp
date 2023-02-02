<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point.model.PointGoods.pojo.PointGoods"%>
<%@ page import="com.point.model.service.PointGoodsService"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

  <title>💰point</title>
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
  </style>
</head>

<body>

  <div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
  <!-- main -->
  <div class="container-fluid">
    <div class="row">
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light collapse  sidebar">
        <div class="p-4 pt-5 sidebar__inner">
          <ul class="list-unstyled components mb-5">
            <li class="mb-5 mt-5">
              <a href="/CGA105G2/front-end/Member/point/listPoint.jsp">
                <h2>Home</h2>
              </a>
            </li>
            <li class="mb-5 mt-5">
              <a href="/CGA105G2/front-end/Member/point/listPoint.jsp" data-toggle="collapse" aria-expanded="false" class="dropdown">
                <h3>💰點數查詢</h3>
              </a>
            </li>
            <li class="mb-5 mt-5">
              <a href="/CGA105G2/front-end/Member/point/listPointGood.jsp" aria-expanded="false"
                class="dropdown">
                <h3>💰點數商品</h3>
              </a>
            </li>
          </ul>
        </div>
      </nav>
      <FORM METHOD="post" ACTION="point.do" name="form1" enctype="multipart/form-data">
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
        <section class="py-5">
          <div class="   my-5">
            <div class="row gx-4 gx-lg-5 align-items-center">
              <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" height="500px" width="300px"
                  src="${pageContext.request.contextPath}/back-end/pointgood/point.do?action=getPdImg&pdId=${param.pdId}"
                  alt="..." /></div>
              <div class="col-md-6">
                <h1 class="display-5 fw-bolder">${param.pdName}</h1>
                <div class="fs-5 mb-5">
                  <span class="text-decoration-line-through">${param.pdPrice} <dfn>points</dfn></span>
                </div>
                <div class="slogan" id="SloganContainer" itemprop="description">
                  <ul>
                    <li>${param.pdText}</li>
                  </ul>

                </div>
                <div class="d-flex p-4">
                  <button class="btn btn-outline-dark flex-shrink-0 ml-4 fs-3 mt-5" type="button"
                    onclick="exchangeAlert()">
                    <i class="bi-cart-fill me-1 "></i>
                    立即兌換
                  </button>
                  
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
      </form>
    </div>
  </div>

  <!-- main -->

  <!-- to the top  -->
  <a class="d-block btn btn-outline-danger  position-fixed position-bottom-10  position-right-10" href="#"
    data-toggle="smooth-scroll" data-target="#page-start-anchor" style="z-index:1;">
    <i class="material-icons text-black">arrow_upward</i>
  </a>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
  <script src="/assets/js/vendor.js"></script>
  <script src="/assets/js/polyfills.js"></script>
  <script src="/assets/js/app.js"></script>
  <!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->

  <!-- Bootstrap js -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>
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


  <!-- sticky-sidebar -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
  <script>
    let a = new StickySidebar("#sidebar", {
      topSpacing: 40,
      bottomSpacing: 20,
      containerSelector: ".container",
      innerWrapperSelector: ".sidebar__inner"
    });

  </script>


  <!-- sweetalert2 -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  <script>
    function exchangeAlert() {
      const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-outline-primary m-5 fs-5',
          cancelButton: 'btn btn-outline-danger m-5 fs-5'
        },
        buttonsStyling: false
      })

      swalWithBootstrapButtons.fire({
        title: '確定要兌換嗎?',
        text: "兌換候無法退貨!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '兌換',
        cancelButtonText: '取消',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          swalWithBootstrapButtons.fire(
            '兌換成功',
            '',
            'success'
          )
        }
      })
    }
  </script>


</body>

</html>
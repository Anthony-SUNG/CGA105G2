<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp.model.*"%>

<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

  <title>💰point</title>
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
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
  <div class="container-fluid">
    <div class="row">
      <!-- nav start -->
      <%@ include file="/front-end/Member/01h/nav/navin01.jsp" %>
      <!-- nav end -->
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
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
          <div class="   my-5">
            <div class="row gx-4 gx-lg-5 align-items-center">
              <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" height="500px" width="300px"
                  src="https://images.pexels.com/photos/3768323/pexels-photo-3768323.jpeg?auto=compress&cs=tinysrgb&w=600"
                  alt="..." /></div>
              <div class="col-md-6">
                <h1 class="display-5 fw-bolder">${PointGoods.pdName}</h1>
                <div class="fs-5 mb-5">
                  <span class="text-decoration-line-through">${PointGoods.pdPrice} <dfn>points</dfn></span>
                </div>
                <div class="slogan" id="SloganContainer" itemprop="description">
                  <ul>
                    <li>${PointGoods.pdText}</li>
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
<!-- sticky-sidebar -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
  $("a:contains(💰point)").closest("a").addClass("active disabled topage");
  let a = new StickySidebar("#sidebar", {
    topSpacing: 30,
    bottomSpacing: 20,
    leftSpacing: 30,
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point.model.PointGoods.pojo.PointGoods"%>
<%@ page import="com.point.model.service.PointGoodsService"%>
<%
	PointGoodsService pointgoodsSvc = new PointGoodsService();
	List<PointGoods> list = pointgoodsSvc.getAlready();
    pageContext.setAttribute("list",list);
%>
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

  <!--  <link rel="stylesheet" href="/src/assets/css/vendor.css" />-->
  <!--  <link rel="stylesheet" href="/src/assets/css/style.css" />-->
  <!--  <link rel="stylesheet" href="/src/assets/fonts/font-awesome/css/font-awesome.css" />-->
  <!--  <link rel="stylesheet" href="./css/carousel.css" />-->
  <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css" />
  <link rel="stylesheet" href="/CGA105G2/assets/css/style.css" />
  <link rel="stylesheet" href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css" />
  <link rel="stylesheet" href="./css/carousel.css" />



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
  </style>
</head>

<body>
  <div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
  <!-- main -->


  <!-- main -->
  <div class="container-fluid p-0">
    <div class="row">
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse ">
        <div class="p-4 pt-5">
          <ul class="list-unstyled components mb-5">
            <li>
              <a href="#">
                <h2>Home</h2>
              </a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/front-end/Member/point/point.do?action=listPoint">
                <h3>💰點數查詢</h3>
              </a>
            </li>
            <li>
              <a href="/CGA105G2/front-end/Member/point/listPointGood.jsp" data-toggle="collapse" aria-expanded="false" class="dropdown">
                <h3>💰點數商品</h3>
              </a>
            </li>
          </ul>
        </div>
      </nav>
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
        <section class="py-5">
          <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <%@ include file="/back-end/pointgood/page1.file" %> 
<c:forEach var="PointGoods" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
              <div class="col mb-5">
                <div class="card h-100">
				<!--Product image -->
				<img src="${pageContext.request.contextPath}/PointServlet?action=getPdImg&pdId=${PointGoods.pdId}" width= 260px height= 300px>
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
                    <div class="text-center p-1">
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/front-end/Member/point/point.do"
														style="margin-bottom: 0px;">
														<input class="btn btn-outline-dark mt-auto fs-4" type="submit" value="立即兌換" onclick="exchangeAlert()">
														<input type="hidden" name="pdId" value="${PointGoods.pdId}">
														<input type="hidden" name="action" value="exchangeRewards">
													</FORM>
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
<%@ include file="/back-end/pointgood/page2.file" %>
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

  <script src="/CGA105G2/assets/js/vendor.js"></script>
  <script src="/CGA105G2/assets/js/polyfills.js"></script>
  <script src="/CGA105G2/assets/js/app.js"></script>

  <!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->

  <!-- Bootstrap js -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>
  <!-- Vue -->
  <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
  
  <script>
    $("a:contains(前台查詢)").closest("a").addClass("active disabled topage");
</script>
  <script>
    const { createApp } = Vue;

    createApp({
      data() {
        return {
          message: "Hello Vue!",
        };
      },
    }).mount("#app");

    $(document).ready(function() {
    	
//     	const both = document.getElementsByClassName('.card h-100');
//     	for( let i = 0; i < both.length; i++) {
//     		both[i].addEventListener('click', function(){
//     			window.location.href = "pointItemPage.jsp";
//     	  }, false);
//     	}
        $("#pointgooditem").click(function() {
            //输入另一个页面的链接
            //我的跳转到controller中的toIntroduction这个方法中进行的页面跳转
            window.location.href = "pointItemPage.jsp";
        });
        
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
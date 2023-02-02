<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point.model.PointOrder.pojo.PointOrder"%>
<%@ page import="com.point.model.service.PointOrderService"%>
<%
	PointOrderService pointorderSvc = new PointOrderService();
	List<PointOrder> list = pointorderSvc.getAll();
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
                  <hr><a href="/CGA105G2/back-end/pointgood/backPointOrder.jsp" class="nav-link">🔆待出貨訂單</a>
                </li>
                <li>
                  <a href="/CGA105G2/back-end/pointgood/listPointOrder.jsp" class="nav-link disabled bg-white"  style="color: #216a51;">🔆訂單總覽</a>
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
                  <hr><a href="/CGA105G2/back-end/pointgood/addPointGood.jsp" class="nav-link">🔆新增商品</a>
                </li>
                <li><a href="/CGA105G2/back-end/pointgood/listPointGood.jsp" class="nav-link">🔆商品總覽</a></li>
                <hr>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
        <section class="py-5">
        <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">🔆訂單總覽</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2">
<!--                             <button type="button" class="btn btn-sm btn-outline-info">Share</button> -->
<!--                             <button type="button" class="btn btn-sm btn-outline-info">Export</button> -->
                        </div>
                    </div>
                </div>
                <div class="table-responsive ">
                    <table class="table table-striped ">
                        <thead>
                            <tr>
                                <th>編號</th>
                                <th>會員編號</th>
                                <th>商品編號</th>
                                <th>單價</th>
                                <th>備註</th>
                                <th>新增日期</th>
                                <th>出貨日期</th>
<!--                                 <th>員工編號</th> -->
                                </tr>
                        </thead>
                        <tbody class="code_tbody">
	<c:forEach var="PointOrder" items="${list}" >
		<tr>
								<td>${PointOrder.poId}</td>
								<td>${PointOrder.memId}</td>
								<td>${PointOrder.pdId}</td>
								<td>${PointOrder.poPrice}</td>
								<td>${PointOrder.poText}</td>
								<td>${PointOrder.poTime}</td>
								<td>${PointOrder.poUtime}</td>
<%-- 								<td>${PointOrder.empId}</td> --%>
		</tr>
	</c:forEach>
                        </tbody>
                    </table>
                </div>
                <canvas class="my-4 w-100" id="myChart" width="900" height="150"></canvas>
          </section>
                          <br>
               <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">  	              
              <nav aria-label="Page navigation example   justify-content-center" class="m-5 ">
                <ul class="pagination">
                </ul>
              </nav>
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
    window.addEventListener("DOMContentLoaded", function (event) {
      <c:forEach var="empRoot" items="${empRoot}" >
      let e =${empRoot.rootId};
      if (e === 2) {
        $("#a1").attr('class', 'nav-link text-uppercase');
      } else if (e === 3) {
        $("#a2").attr('class', 'nav-link text-uppercase');
      } else if (e === 4) {
        $("#a3").attr('class', 'nav-link text-uppercase');
      } else if (e === 5) {
        $("#a4").attr('class', 'nav-link text-uppercase');
      }
      </c:forEach>
    });
  </script>

</body>

</html>
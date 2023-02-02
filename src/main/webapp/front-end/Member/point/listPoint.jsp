<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point.model.Point.pojo.Point"%>
<%@ page import="com.point.model.service.PointService"%>
<%
	PointService pointSvc = new PointService();
	List<Point> list = pointSvc.getAll();
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
  <div class="container-fluid p-0">
    <div class="row">
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse ">
        <div class="p-4 pt-5">
          <ul class="list-unstyled components mb-5">
            <li>
              <a href="/CGA105G2/front-end/Member/point/listPoint.jsp">
                <h2>Home</h2>
              </a>
            </li>
            <li>
              <a href="/CGA105G2/front-end/Member/point/listPoint.jsp" data-toggle="collapse" aria-expanded="false" class="dropdown">
                <h3>💰點數查詢</h3>
              </a>
            </li>
            <li>
              <a href="/CGA105G2/front-end/Member/point/listPointGood.jsp" >
                <h3>💰點數商品</h3>
              </a>
            </li>
          </ul>
        </div>
      </nav>
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
<section class="py-5">
        <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">💰點數查詢</h1>
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
                                <th>異動原因</th>
                                <th>異動點數</th>
                            </tr>
                        </thead>
                        <tbody class="code_tbody">
	<c:forEach var="Point" items="${list}" >
		<tr>
			<td>${Point.pointChange}</td>
			<td>${Point.pointNumber}</td>
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
    $(document).ready(function() {
        $("#pointgooditem").click(function() {
            //输入另一个页面的链接
            //我的跳转到controller中的toIntroduction这个方法中进行的页面跳转
            window.location.href = "pointItemPage.jsp";
        });
    });
  </script>
</body>

</html>
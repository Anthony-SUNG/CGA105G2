<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

  <title>🗃️管理</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
  <!-- main -->
  <div class="container-fluid">
    <div class="row">
      <!-- nav start -->
      <%@ include file="/front-end/Member/01h/nav/navin04.jsp" %>
      <!-- nav end -->
      <div class="container mt-17 mb-17">
        <div class="col-md-9  card shadow m-5">
          <h1 class=" m-5 text-center">結帳</h1>
          <form METHOD="post" action="<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do"
            style="padding: 10px 100px;" autocomplete="off">
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >店家名稱 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="${storeName}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >名字 : </span>
              <input type="text" name="order_member_name"  readonly="readonly"
                value="${foodorder_name}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >電話 : </span>
              <input type="text" name="order_member_phone"  readonly="readonly"
                value="${foodorder_phone}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >人數 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="${foodorder_peopleNum}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >日期 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="${foodorder_date}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >時段 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="${foodorder_time}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >信用卡人 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="xxxx" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
              <span></span>
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >信用卡號 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="xxxx" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
            </div>
            <table class="table" style="text-align:center; font-size: 20px;">
              <thead class="thead-light">
                <tr>
                  <th scope="col"></th>
                  <th scope="col">編號</th>
                  <th scope="col">套餐名稱</th>
                  <th scope="col">金額</th>
                  <th scope="col">數量</th>
                </tr>
              </thead>
              <tbody id="tbody_detail">
                <!-- 這邊tr_detail+i 1記得寫jsp要改成i-->
                <% int i = 1; %>
                <c:forEach var="mealVO" items="${foodorder_mealList}">
	                <tr id="tr_detail_<%= i %>">
	                  <th scope="row"><%= i %></th>
	                  <td>${mealVO.mealId}</td>
	                  <td>${mealVO.mealName}</td>
	                  <td id="money_<%= i %>">${mealVO.mealPrice}</td>
	                  <td>${mealVO.rdQuantity}</td>
	                </tr>
	                <% i = i+1; %>
                </c:forEach>
                <tr id="tr_total">
                  <td>合計 : ${foodorder_totalMoney} </td>
                  <td></td>
                  <td></td>
                </tr>
              </tbody>
            </table>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >優惠碼扣金額 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="${foodorder_codeMoney}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
            </div>
            <div class="input-group input-group-lg mb-5">
              <span class="input-group-text" >結帳金額 : </span>
              <input type="text" name="order_shop_name"  readonly="readonly"
                value="${foodorder_totalCodeMoney}" class="form-control" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg">
            </div>
            <div>
              <input type="hidden" name="action" value="checkout">
              <input class="btn btn-warning mb-1 btn-block fs-5 mb-10" type="submit" value="結帳">
            </div>
          </form>
        </div>
      </div>

    </div>
  </div>
  </div>

  <!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
  $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
  $("a:contains(🔻訂位)").closest("a").attr("data-toggle", "show");
  $("#pageSubmenu3").removeClass("collapse");
  $("#pageSubmenu3 a:contains(🔆訂位預約)").closest("a").addClass("active disabled bg-white topage");
</script>
</body>

</html>
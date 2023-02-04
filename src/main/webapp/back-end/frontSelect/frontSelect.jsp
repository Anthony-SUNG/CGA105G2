<%@page import="com.order.model.Order.pojo.Order" %>
	<%@page import="com.order.model.service.OrderService" %>
		<%@page import="com.store.model.Store.pojo.Store" %>
			<%@page import="com.store.model.service.StoreService" %>
				<%@page import="com.member.model.Member.pojo.Member" %>
					<%@page import="java.util.List" %>

						<%@page import="com.member.model.service.MemberService" %>
							<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
								<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
									<%@ page import="com.goods.model.*" %>
										<%@ page import="com.member.model.*" %>
											<%@ page import="com.store.model.*" %>
												<%@ page import="com.order.model.*" %>


													<!DOCTYPE html>
													<html class="no-js" lang="en">

													<head>
														<meta charset="utf-8" />
														<meta http-equiv="x-ua-compatible" content="ie=edge" />
														<meta name="viewport"
															content="width=device-width, initial-scale=1, shrink-to-fit=no" />
														<link rel="stylesheet"
															href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
														<script
															src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
														<title>後台</title>
													</head>

													<body>
														<!-- header start -->
														<%@ include file="/back-end/01h/headerin.jsp" %>
															<!-- header end -->

															<!-- main -->
															<div id="result"></div>
															<section class="scontainer-fluid my-14" id="">
																<div class="section-content text-center ">
																	<div
																		class="d-flex justify-content-center justify-content align-items-center mb-12">
																		<div class="d-none d-md-block">
																			<div class="btn-group" id="btg">
																				<div class="radio-buttons-group border-0  "
																					data-toggle="shuffle-grid"
																					data-target="#projects-grid">
																					<button
																						class="btn btn-light selected fs-4 bg-cyan-30 "
																						data-value="member">會員查詢</button>
																					<button
																						class="btn btn-light fs-4 bg-cyan-30"
																						data-value="store">店家查詢</button>
																					<button
																						class="btn btn-light fs-4 bg-cyan-30"
																						data-value="order">訂單查詢</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="shuffle-grid shuffle-grid-gap-14 shuffle-grid-cols-1 shuffle-grid-cols-md-1 shuffle-grid-cols-xl-1 m-5"
																		id="projects-grid">
																		<!--會員查詢-->
																		<div class="shuffle-grid-item "
																			data-groups="member" id="member">
																			<section>
																				<div class="container col-11 mx-auto">
																					<div class="  col-11 justify-content-center">
																						
																						

																						<% MemberService memberSvc=new
																							MemberService();
																							List<Member> list =
																							memberSvc.getMemberList();
																							pageContext.setAttribute("list",
																							list);
																							%>

																							<table
																								class="table table-hover"
																								id="memSelect">
																								<thead>
																									<tr>
																										<th scope="col">
																											會員編號</th>
																										<th scope="col">
																											會員帳號</th>
																										<th scope="col">
																											會員信箱</th>
																										<th scope="col">
																											會員名稱</th>
																										<th scope="col">
																											收件姓名</th>
																										<th scope="col">
																											出生日期</th>
																										<th scope="col">
																											註冊時間</th>
																										<th scope="col">
																											會員點數</th>
																									</tr>
																								</thead>
																								<tbody>

																									<% Member
																										memberVo=new
																										Member(); %>
																										<% for (int i=0;
																											i <
																											list.size();
																											i++) {
																											memberVo=list.get(i);
																											%>


																											<tr>
																												<th
																													scope="row">
																													<%=memberVo.getMemId()%>
																												</th>
																												<td>
																													<%=memberVo.getMemAcc()%>
																												</td>
																												<td>
																													<%=memberVo.getMemMail()%>
																												</td>
																												<td>
																													<%=memberVo.getMemName()%>
																												</td>
																												<td>
																													<%=memberVo.getMemRecipient()%>
																												</td>
																												<td>
																													<%=memberVo.getMemBirthday()%>
																												</td>
																												<td>
																													<%=memberVo.getMemTime()%>
																												</td>
																												<td>
																													<%=memberVo.getMemPoint()%>
																												</td>
																											</tr>
																											<% } %>

																								</tbody>
																							</table>
																					</div>
																				</div>
																			</section>
																		</div>
																		<!--店家查詢-->
																		<div class="shuffle-grid-item hiddenBtn"
																			data-groups="store" id="store"
																			style="display: none;">
																			<section>
																				<div class="container col-11">
																					<div
																						class="col-12 justify-content-center">
																						

																						<% StoreService storeSvc=new
																							StoreService(); List<Store>
																							storeList =
																							storeSvc.getStoreList();
																							pageContext.setAttribute("storeList",
																							storeList);
																							%>
																							<table
																								class="table table-hover"
																								id="storeSelect">
																								<thead>
																									<tr>
																										<th scope="col">
																											店家編號</th>
																										<th scope="col">
																											店家名稱</th>
																										<th scope="col"
																											class="col-1">
																											審核狀態</th>
																										<th scope="col">
																											電話</th>
																										<th scope="col">
																											店家地址</th>
																										<th scope="col">
																											新增時間</th>
																									</tr>
																								</thead>
																								<tbody>
																									<% Store storeVo=new
																										Store(); %>
																										<% for (int i=0;
																											i <
																											storeList.size();
																											i++) {
																											storeVo=storeList.get(i);
																											%>


																											<tr>
																												<td>
																													<%=storeVo.getStoreId()%>
																												</td>
																												<td>
																													<%=storeVo.getStoreName()%>
																												</td>

																												<td>
																													<% switch
																														(storeVo.getStoreStatus())
																														{
																														case
																														0
																														:
																														out.print("未註冊");
																														break;
																														case
																														1
																														:
																														out.print("審核中");
																														break;
																														case
																														2
																														:
																														out.print("審核通過");
																														break;
																														case
																														3
																														:
																														out.print("停權");
																														break;
																														}
																														%>
																												</td>

																												<td>
																													<%=storeVo.getStorePhone1()%>
																												</td>
																												<td>
																													<%=storeVo.getStoreCity()
																														+
																														storeVo.getStoreDistrict()
																														+
																														storeVo.getStoreAddress()%>
																												</td>
																												<td>
																													<%=storeVo.getStoreTime()%>
																												</td>
																											</tr>

																											<% } %>
																								</tbody>
																							</table>
																					</div>
																				</div>
																			</section>
																		</div>

																		<!--訂單查詢-->
																		<div class="shuffle-grid-item hiddenBtn"
																			data-groups="order" id="order"
																			style="display: none;">
																			<section>
																				<div class="container col-lg-10">
																					<div
																						class=" col-12 justify-content-center">
																					
																						<% OrderService orderSvc=new
																							OrderService(); List<Order>
																							orderList =
																							orderSvc.getAll();
																							pageContext.setAttribute("orderList",
																							orderList);
																							%>

																							<table
																								class="table table-hover"
																								id="orderSelect">
																								<thead>
																									<tr>
																										<th scope="col">
																											訂單編號</th>
																										<th scope="col">
																											會員編號</th>
																										<th scope="col">
																											店家</th>
																										<th scope="col">
																											總金額</th>
																										<th scope="col">
																											查看明細</th>
																									</tr>
																								</thead>

																								<% Order orderVo=new
																									Order(); %>

																									<% for (int i=0; i <
																										orderList.size();
																										i++) {
																										orderVo=orderList.get(i);
																										%>

																										<tbody>
																											<tr
																												class="text-center">
																												<th
																													scope="row">
																													<%=orderVo.getOrderId()%>
																												</th>
																												<td>
																													<%=orderVo.getMemId()%>
																												</td>
																												<td>
																													<%=orderVo.getStoreId()%>
																												</td>
																												<td>
																													<%=orderVo.getOrderPrice()%>
																												</td>
																												<td><button
																														class=" btn-primary"
																														onclick="addEmpAlert()">123</button>
																												</td>
																											</tr>
																											<% } %>
																										</tbody>
																							</table>
																					</div>
																				</div>
																			</section>
																		</div>
																	</div>
																</div>
															</section>

															<!-- main -->
															<!-- footer start -->
															<%@ include file="/back-end/01h/footerin.jsp" %>
																<!-- footer end -->
																<!-- sidebar menu Class -->
																<script>
																	$("a:contains(前台查詢)").closest("a").addClass("active disabled topage");

																	$(document).ready(function () {

																		$('#btg').hover(function () {
																			$(".hiddenBtn").css("display", "block");
																		});
																		
																		$('#memSelect').DataTable();
																		$('#storeSelect').DataTable();
																		$('#orderSelect').DataTable();



																	});




																	//     $(document).ready(function(){
																	//     $("#memberInput").keyup(function(){
																	//     	let inputValue = $(this).val();
																	//     	$.ajax({
																	//     		type:"POST",
																	//     		url: "/CGA105G2/frontSelect" ,
																	//     		data:{inputValue:inputValue},
																	//     		success: function(response){
																	//     			$("#result").html(response);
																	//     		}
																	//     	});
																	//     });

																	//     });





																	function addEmpAlert() {
																		const swalWithBootstrapButtons = Swal.mixin({
																			customClass: {
																				confirmButton: 'btn btn-outline-primary m-5 fs-5',

																			},
																			buttonsStyling: false
																		})

																		swalWithBootstrapButtons.fire({
																			position: 'middle',

																			title: '訂單明細',
																			html: "<h2>訂單編號:1</h2>" +
																				"<h2>商品編號:1</h2>" +
																				"<h2>商品數量:3</h2>" +
																				"<h2>商品價格:200</h2>",

																			showConfirmButton: false,
																			timer: 10000
																		})
																	}

																</script>
																<script>
																	const list = [];
																	<c:forEach var="empRoot" items="${empRoot}">
																		list.push(${empRoot.rootId});
																	</c:forEach>
																	for (let e of list) {
																		switch (e) {
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
													</body>

													</html>
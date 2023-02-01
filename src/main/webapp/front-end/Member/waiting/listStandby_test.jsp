<%@page import="java.util.List" %>
	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<%@page import="java.sql.Timestamp" %>
				<%@ page import="com.waiting.model.dao.impl.StandbyDAO" %>
					<%@ page import="com.waiting.model.pojo.Standby" %>
						<%@ page import="com.waiting.model.service.StandbyService" %>


							<!-- 123 -->


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
									<!-- jquery 3.4.1  css -->
									<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
									<link rel="stylesheet" href="/resources/demos/style.css">

									<link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css" />
									<link rel="stylesheet" href="/CGA105G2/assets/css/style.css" />
									<link rel="stylesheet" href="/CGA105G/assets/custom.css">
									<link rel="stylesheet"
										  href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css" />
									<!-- <link rel="stylesheet" href="/CGA105G2/src/main/webapp/assets/css/carousel.css" /> -->


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

										@media (min-width : 768px) {
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
												<%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
													<!-- nav end -->
													<main role="main "
														  class="col-md-9 ml-sm-auto col-lg-10 px-md-4 container ">





														<div class="container m-5 p-5">
															<button class="btn btn-outline-primary m-5 "
																	id="btnStandBy">
																TestStandby3</button>
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Name</th>
																		<th scope="col">Phone</th>
																		<th scope="col">Number</th>
																	</tr>
																</thead>
																<tbody id="result1">


																</tbody>
															</table>


														</div>













													</main>
											</div>
										</div>
										<!-- main -->
										<!-- footer start -->
										<%@ include file="/front-end/Member/01h/footerin.jsp" %>
											<!-- footer end -->

											<script>
												$(document).ready(function () {
													$('#btnStandBy').click(function () {
														// addStandBy();
														$.ajax({
															type: "POST",
															url: "/CGA105G2/standby",
															data: { action: "selectStandBy" },
															dataType: "json",
															success: function (data) {
																console.log("success!");
																console.log(data);
																let html = "";
																for (let i = 0; i < data.length; i++) {

																	html += `<tr>
																		<th>\${data[i].staId}</th>
																		<td>\${data[i].staName}</td>
																		<td>\${data[i].staPhone}</td>
																		<td>\${data[i].staNumber}</td>
																		</tr>`;
																}
																$("#result1").html(html);
															}


														});


													});
												});


											</script>










											<script>
												$("a:contains(🌟)").closest("a").addClass("active disabled topage");
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

											<!-- sweetalert2 -->
											<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
											<script>
												function addStandBy() {
													setTimeout(addStandByAlert(), 1000);
												}

												function addStandByAlert() {
													const swalWithBootstrapButtons = Swal.mixin({
														customClass: {
															confirmButton: 'btn btn-outline-primary m-5 fs-5',

														},
														buttonsStyling: false
													})

													swalWithBootstrapButtons.fire({
														position: 'middle',
														icon: 'success',
														title: '登記成功',
														showConfirmButton: false,
														timer: 1500
													})
												}
											</script>
								</body>

							</html>
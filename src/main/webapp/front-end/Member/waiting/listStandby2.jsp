<%@page import="java.util.List"%>
<%@ page
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="com.waiting.model.dao.impl.StandbyDAO"%>
<%@ page import="com.waiting.model.pojo.Standby"%>
<%@ page import="com.waiting.model.service.StandbyService"%>


<!-- 123 -->


<!DOCTYPE html>
<html
	class="no-js"
	lang="en">

<head>
<meta charset="utf-8" />
<meta
	http-equiv="x-ua-compatible"
	content="ie=edge" />
<meta
	name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<title>🗃️管理</title>
<!-- Bootstrap css -->
<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous" />
<!-- jquery 3.4.1  css -->
<link
	rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link
	rel="stylesheet"
	href="/resources/demos/style.css">

<link
	rel="stylesheet"
	href="/CGA105G2/assets/css/vendor.css" />
<link
	rel="stylesheet"
	href="/CGA105G2/assets/css/style.css" />
<link
	rel="stylesheet"
	href="/CGA105G/assets/custom.css">
<link
	rel="stylesheet"
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
	<!-- header start -->
	<%@ include file="/front-end/Member/01h/headerin.jsp"%>
	<!-- header end -->
	<!-- main -->
	<div class="container-fluid">
		<div class="row">
			<!-- nav start -->
			<%@ include file="/front-end/Member/01h/nav/navin02.jsp"%>
			<!-- nav end -->
			<main
				role="main "
				class="col-md-9 ml-sm-auto col-lg-10 px-md-4 container ">

				<div>
					<div class="input-group-text">
						<p
							class="form-control pt-5"
							style="background-color: rgb(255, 201, 123); color: black;">候位表</p>
					</div>
					<div
						class="p-0 flex-grow-1"
						id="tablenumber"
						style="display: none">
						<div
							class="btn-group btn-group-toggle flex-grow-1"
							data-toggle="buttons">
							<label
								class="btn btn-secondary active"
								id="btmon"> <input
								type="radio"
								name="options"
								id="option1">on
							</label> <label
								class="btn btn-secondary"
								id="btmoff"> <input
								type="radio"
								name="options"
								id="option2"
								checked>off
							</label>
						</div>
					</div>
					<div
						class="btn-group btn-group-toggle"
						style=""
						id="tablewait">
						<div class="table-responsive ">
							<%
							StandbyService standbySvc = new StandbyService();
							List<Standby> list = standbySvc.getAll();

							pageContext.setAttribute("list", list);
							%>
							<table class="table table-striped m-0">
								<thead>
									<tr class="text-center">
										<th class="col-4">電話</th>
										<th class="col-4">姓名</th>
										<th class="col-4">人數</th>
									</tr>
								</thead>
							</table>
						</div>
						<section
							class="section p-0 "
							id="faq2"
							style="overflow-y: scroll; height: 300px">
							<div class="section-content container ">
								<div class="row">
									<div class="col-12 p-0">
										<article class="faq p-0">

											<%
											Standby standby = new Standby();
											%>

											<%
											for (int i = 0; i < list.size(); i++) {

												standby = list.get(i);
											%>



											<header
												class="faq-header"
												data-toggle="collapse"
												data-target="#faq2-item-<%=standby.getStaId() %>"
												aria-expanded="false">
												<table class="table table-striped m-0">
													<tbody class="code_tbody">

														<tr class="text-center">
															<td class="col-5"><%=standby.getStaPhone() %></td>
															<td><%=standby.getStaName() %></td>
															<td><%=standby.getStaNumber() %>位</td>
														</tr>
													</tbody>
												</table>
												<div
													class="faq-toggle"
													style="display: none">
													<i class="material-icons faq-toggle-closed">add</i> <i
														class="material-icons faq-toggle-open">remove</i>
												</div>
											</header>
											<div
												class="faq-body collapse row mx-auto"
												id="faq2-item-${standbyVo.staId}"
												style="width: 100%; justify-content: center">
												<!-- 													<div class="radio-buttons-group" -->
												<!-- 														style="width: 100%; justify-content: center"> -->

												<form
													METHOD="post"
													id=noticeStandby
													ACTION="<%=request.getContextPath()%>/standby">
													<input
														type="submit"
														value="通知"
														onclick=""
														class="btn btn-outline-info"
														data-value="call"> <input
														type="hidden"
														name="staId"
														value="<%=standby.getStaId() %>"> <input
														type="hidden"
														name="staStatus"
														value="<%=standby.getStaStatus() %> "> <input
														type="hidden"
														name="action"
														value="delete">

													<!-- 															<button class="btn btn-light selected" data-value="call">通知</button> -->
												</form>

												<form
													METHOD="post"
													id="checkStandby"
													ACTION="<%=request.getContextPath()%>/standby">
													<input
														class="btn btn-outline-info"
														data-value="come"
														onclick=""
														type="submit"
														value="報到d"> <input
														type="hidden"
														name="staId"
														value="<%=standby.getStaId() %>"> <input
														type="hidden"
														name="action"
														value="callStandby">

													<!-- 															<button class="btn btn-light" data-value="come">報到</button> -->
												</form>
											</div>

											<%
											}
											%>
										</article>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
		</div>






		</main>

	</div>
	</div>
	<!-- main -->
	<!-- footer start -->
	<%@ include file="/front-end/Member/01h/footerin.jsp"%>
	<!-- footer end -->
	<script>
		$("a:contains(🌟)").closest("a").addClass("active disabled topage");
	</script>

	<!-- stickey bar: -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>

	<script>
		let a = new StickySidebar("#sidebar", {
			topSpacing : 40,
			bottomSpacing : 20,
			containerSelector : ".container",
			innerWrapperSelector : ".sidebar__inner"
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
				customClass : {
					confirmButton : 'btn btn-outline-primary m-5 fs-5',

				},
				buttonsStyling : false
			})

			swalWithBootstrapButtons.fire({
				position : 'middle',
				icon : 'success',
				title : '登記成功',
				showConfirmButton : false,
				timer : 1500
			})
		}
	</script>
</body>

</html>
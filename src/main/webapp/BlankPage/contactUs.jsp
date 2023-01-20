<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<title>🗃️管理</title>

</head>

<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
	<div class="container-fluid">
			<main role="main" class="col-md-9 m-sm-auto col-lg-10 px-md-4 my-5">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">🔆聯繫我們</h1>
					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group mr-2">
							<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
							<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
						</div>
					</div>
				</div>
				<!-- "contacts" section start -->
				<section class="section container" id="contacts">
					<div class="section-content container">
						<div class="">
							<div class="col-12 col-lg-8 mb-14 mb-lg-0">
								<small
									class="fs-1 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
									We answer within 24 hours </small>
								<h1>Contact Us</h1>
								<form action="#" class="row mt-17">
									<div class="col-12 ">
										<div class="form-group">
											<label for="name" class="form-label">First name</label> <input
												type="text" class="form-control" id="name" />
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label for="phone" class="form-label">Phone</label> <input
												type="text" class="form-control" id="phone" />
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label for="email" class="form-label">Email</label> <input
												type="text" class="form-control" id="email" />
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label for="message" class="form-label">Message</label>
											<textarea id="message" class="form-control" rows="3"></textarea>
										</div>
										<div class="form-group mb-0">
											<button class="btn btn-primary btn-lg">Send message</button>
										</div>
									</div>
								</form>
							</div>

						</div>
					</div>
				</section>
			</main>
	</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
	$("a:contains(📭聯繫我們)").closest("a").addClass("active disabled topage");
</script>

</body>

</html>
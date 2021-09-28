<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creation finance</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<!-- Styles -->
<link rel="stylesheet"
	href="<c:out value="resources/css/template/style.css"/>">

<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">

<!-- JS FINANCE -->
<link rel="stylesheet"
	href="<c:out value="resources/css/cssFinance/libs/style.css"/>">
<link rel="stylesheet"
	href="<c:out value="resources/css/cssFinance/std/style.css"/>">
<link rel="stylesheet"
	href="<c:out value="resources/css/cssFinance/jqvmap.css"/>">
<link rel="stylesheet"
	href="<c:out value="resources/css/cssFinance/jvectormap.css"/>">
<link rel="stylesheet"
	href="<c:out value="resources/css/cssFinance/fontawesome-all.css"/>">
<link rel="stylesheet"
	href="<c:out value="resources/css/cssFinance/flag-icon.min.css"/>">

</head>
<body>

	<!-- header -->
	<c:import url="/WEB-INF/shared/header.jsp"></c:import>

	<div class="row">

		<div class="col-xl-2 col-lg-12 col-md-2 col-sm-12 col-12 mx-auto my-5"></div>

		<div class="col-xl-4 col-lg-12 col-md-4 col-sm-12 col-12 mx-auto my-5">
			<div class="card">
				<h5 class="card-header">Total Sale</h5>
				<div class="card-body">
					<canvas id="total-sale" width="220" height="155"></canvas>
					<div class="chart-widget-list"></div>
				</div>
			</div>
		</div>

		<div class="col-xl-4 col-lg-12 col-md-4 col-sm-12 col-12 mx-auto my-5">

			<div class="card">
				<div class="card-body">
					<h5 class="text-muted">Montant totale</h5>
					<div class="metric-value d-inline-block">
						<h1 class="mb-1 text-primary" id="montantTotale"></h1>
					</div>
				</div>
				<div id="sparkline-1"></div>
			</div>

			<div class="card">
				<div class="card-body">
					<h5 class="text-muted">Montant actuel : ${isika}</h5>
					<h1>
						Welcome to
						<%= request.getAttribute("isika") %>
					</h1>

					<div class="metric-value d-inline-block">
						<h1 class="mb-1 text-primary" id="montantActuel">${amountTotalInDB}</h1>
					</div>
				</div>
				<div id="sparkline-1"></div>
			</div>

			<div class="card text-center shadow-sm">
				<div class="card-header">
					Realisation financement<br />
				</div>
				<div class="card-body">
					<span class="error">${result}</span><br />
					<form method="post" action="registrationFinance">
						<div class="form-floating mb-3">
							<input type="text" name="amount" class="form-control" id="amount"
								required><label for="floatingInput">Montant</label>
						</div>

						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>

		<div class="col-xl-2 col-lg-12 col-md-2 col-sm-12 col-12 mx-auto my-5"></div>

	</div>


	<c:import url="/WEB-INF/shared/footer.jsp"></c:import>

	<!-- Lien Jquery et js bootstrap -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type='text/javascript'
		src='resources/js/template/jquery.collapsible.min.js'></script>
	<script type='text/javascript'
		src='resources/js/template/swiper.min.js'></script>
	<script type='text/javascript'
		src='resources/js/template/jquery.countdown.min.js'></script>
	<script type='text/javascript'
		src='resources/js/template/circle-progress.min.js'></script>
	<script type='text/javascript'
		src='resources/js/template/jquery.countTo.min.js'></script>
	<script type='text/javascript'
		src='resources/js/template/jquery.barfiller.js'></script>
	<script type='text/javascript' src='resources/js/template/custom.js'></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="<c:out value="resources/js/scriptAjax.js"/>"></script>
	<script type="text/javascript"
		src="<c:out value="resources/js/scriptRegex.js"/>"></script>

	<!-- JS FINANCE -->
	<script type='text/javascript'
		src='resources/js/jsFinance/Chart.bundle.js'></script>
	<script type='text/javascript' src='resources/js/jsFinance/chart.js'></script>
	<script type='text/javascript'
		src='resources/js/jsFinance/jquery.sparkline.js'></script>
	<script type='text/javascript'
		src='resources/js/jsFinance/jvectormap.js'></script>
	<script type='text/javascript'
		src='resources/js/jsFinance/jvectormap.min.js'></script>
	<script type='text/javascript' src='resources/js/jsFinance/main-js.js'></script>
	<script type='text/javascript'
		src='resources/js/jsFinance/slimscroll.js'></script>
	<script type='text/javascript' src='resources/js/jsFinance/spark-js.js'></script>

	<script type='text/javascript'
		src='resources/js/jsFinance/createFinance/createFinance.js'></script>
</body>
</html>
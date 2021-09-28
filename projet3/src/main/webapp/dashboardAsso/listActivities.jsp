<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des activités</title>
<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tables</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-1"></div>
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">Liste des Activités</div>
				<!-- /.panel-heading -->
				<div class="panel-body" align="center">
					<table width="80%"
						class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th>Activités validées</th>
								<th>Activités en cours</th>
								<th>Activités en attente</th>
							</tr>
						</thead>
						<tbody>
							<tr class="odd gradeX">
								<td>Trident</td>
								<td>Internet Explorer 4.0</td>
								<td>Win 95+</td>
							</tr>
							<tr class="even gradeC">
								<td>Trident</td>
								<td>Internet Explorer 5.0</td>
								<td>Win 95+</td>
							</tr>
							<tr class="odd gradeA">
								<td>Trident</td>
								<td>Internet Explorer 5.5</td>
								<td>Win 95+</td>
							</tr>
							<tr class="even gradeA">
								<td>Trident</td>
								<td>Internet Explorer 6</td>
								<td>Win 98+</td>
							</tr>
							<tr class="odd gradeA">
								<td>Trident</td>
								<td>Internet Explorer 7</td>
								<td>Win XP SP2+</td>
							</tr>
							<tr class="even gradeA">
								<td>Trident</td>
								<td>AOL browser (AOL desktop)</td>
								<td>Win XP</td>
							</tr>
							<tr class="gradeA">
								<td>Gecko</td>
								<td>Firefox 1.0</td>
								<td>Win 98+ / OSX.2+</td>
							</tr>
							<tr class="gradeA">
								<td>Gecko</td>
								<td>Firefox 1.5</td>
								<td>Win 98+ / OSX.2+</td>
							</tr>
							<tr class="gradeA">
								<td>Gecko</td>
								<td>Firefox 2.0</td>
								<td>Win 98+ / OSX.2+</td>
							</tr>
							<tr class="gradeA">
								<td>Gecko</td>
								<td>Firefox 3.0</td>
								<td>Win 2k+ / OSX.3+</td>
							</tr>
							<tr class="gradeA">
								<td>Gecko</td>
								<td>Camino 1.0</td>
								<td>OSX.2+</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->

	</div>
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
</body>
</html>
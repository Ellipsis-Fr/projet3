<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Accueil</title>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/bootstrap.min.css"/>'> --%>
	
	    <!-- FontAwesome CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/font-awesome.min.css"/>'> --%>
	
	    <!-- ElegantFonts CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/elegant-fonts.css"/>'> --%>
	
	    <!-- themify-icons CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/themify-icons.css"/>'> --%>
	
	    <!-- Swiper CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/swiper.min.css"/>'> --%>
	
	    <!-- Styles -->
	    <link rel="stylesheet" href="<c:out value="resources/css/template/style.css"/>">
	    
	    <!-- bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
   
    
	</head>
	<body>
			
		<!-- header -->
		<c:import url="/WEB-INF/shared/header.jsp"></c:import>
		
		<h1>Bienvenue sur notre plateforme de collectes carritatives</h1>		

		<hr />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		
		<c:choose>
			<c:when test="${!empty requestScope.associations}">
				<h3>Liste des évenements en cours</h3>
				<ul>
					<c:forEach items="${requestScope.associations}" var="association">
						<li><c:out value="${association.name}" /> <a href='<c:out value="accesCollecte?id=${association.id}"/>'>Voir l'événement</a></li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<h3>Aucun événement en cours</h3>
			</c:otherwise>
		</c:choose>
	
		<!-- footer -->
		<c:import url="/WEB-INF/shared/footer.jsp"></c:import>
		
		<!-- Lien Jquery et js bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	    <script type='text/javascript' src='resources/js/template/jquery.collapsible.min.js'></script>
	    <script type='text/javascript' src='resources/js/template/swiper.min.js'></script>
	    <script type='text/javascript' src='resources/js/template/jquery.countdown.min.js'></script>
	    <script type='text/javascript' src='resources/js/template/circle-progress.min.js'></script>
	    <script type='text/javascript' src='resources/js/template/jquery.countTo.min.js'></script>
	    <script type='text/javascript' src='resources/js/template/jquery.barfiller.js'></script>
	    <script type='text/javascript' src='resources/js/template/custom.js'></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	</body>
</html>


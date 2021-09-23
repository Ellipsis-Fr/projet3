<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Connexion Association</title>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		
		
<!-- 	    FontAwesome CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/font-awesome.min.css"/>'> --%>
	
<!-- 	    ElegantFonts CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/elegant-fonts.css"/>'> --%>
	
<!-- 	    themify-icons CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/themify-icons.css"/>'> --%>
	
<!-- 	    Swiper CSS -->
<%-- 	    <link rel="stylesheet" href='<c:out value="resources/css/template/swiper.min.css"/>'> --%>
	
		<!-- Styles -->
	    <link rel="stylesheet" href="<c:out value="resources/css/template/style.css"/>">
	    
	    <!-- bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
   
	</head>
	<body>
		<!-- header -->
		<c:import url="/WEB-INF/shared/header.jsp"></c:import>
		
		
		<!-- body -->
		<div class="col-3 mx-auto my-5">
			<div class="card text-center shadow-sm">
				<div class="card-header">
				  Connexion <br />
				</div>
				<div class="card-body">
				<span class="error">${result}</span><br />
				<form method="post" action="connexionAssociation">
					<div class="form-floating mb-3">
						<input type="email" name="email" class="form-control" id="floatingInput" placeholder="name@example.com" required><label for="floatingInput">Email address</label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" required> <label for="floatingPassword">Password</label>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
				</div>
				<div class="card-footer">
					Première visite ? <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="btnAssociationRegistration" >S'inscrire</button>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="false">
		  <div class="modal-dialog modal-xl modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <c:import url="/WEB-INF/inscriptionAssociation.jsp"></c:import>
		      </div>
		    </div>
		  </div>
		</div>				
		
		


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
	    <script type="text/javascript" src="<c:out value="resources/js/scriptAjax.js"/>"></script>
	    <script type="text/javascript" src="<c:out value="resources/js/scriptRegex.js"/>"></script>
	</body>
</html>


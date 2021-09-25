<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Dashboard Administrateur</title>

		<!-- Styles -->
	    <link rel="stylesheet" href="<c:out value="resources/css/template/style.css"/>">
	    
	    <!-- bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
   
	</head>
	<body>
		Je suis le dashboard Association
		<br /> Informations association connectée
		<ul>
			<li>${sessionScope.sessionAssociation.id}</li>
			<li>${sessionScope.sessionAssociation.name}</li>
			<li>${sessionScope.sessionAssociation.address}</li>
			<li>${sessionScope.sessionAssociation.rna}</li>
		</ul>
		
		<a class="btn btn-primary" href='<c:url value="editAccount"/>' role="button">Modification compte</a>
		
		<c:choose>
			<c:when test="${sessionScope.sessionAssociation.eventInProgress}">
				<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#closeEvent" id="btnCloseEvent" >Clotûrer l'Evenement</button>
			</c:when>
			<c:otherwise>
				<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createEvent" id="btnCreateEvent" >Créer un nouvel Evenement</button>
			</c:otherwise>
		</c:choose>

		<!-- Modal CreateEvent -->
		<div class="modal fade" id="createEvent" tabindex="-1" aria-labelledby="eventHandlerLabel" aria-hidden="false">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Création de l'événement</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<c:import url="/dashboardAsso/createEvent.jsp"></c:import>					
					</div>
				</div>
			</div>
		</div>
		
		<!-- Modal CloseEvent -->		
		<div class="modal fade" id="closeEvent" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Clotûre de l'événement</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						Etes vous sûr de vouloir arrêter cet événement ?
					</div>
					<div class="modal-footer">
						<a class="btn btn-primary" href='<c:url value="closeEvent"/>' role="button">Oui</a>
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Non</button>
					</div>
				</div>
			</div>
		</div>
		
		
		
	
		<!-- Lien Jquery et js bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	    <script type="text/javascript" src="<c:out value="resources/js/scriptRegex.js"/>"></script>
	
	</body>
</html>
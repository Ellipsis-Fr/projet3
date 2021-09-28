<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifier mes Informations</title>
	
			<!-- bootstrap CSS -->
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	</head>
	<body onload="inputsToCheck(formEditAssociation.id)">
	
		<!-- header -->
		
		
		
		<!-- body -->
        <div class="container">
            <form:form modelAttribute="association" method="POST" class="register-form" id="formEditAssociation" action="editAccount" enctype="multipart/form-data">
                <div class="row">
                    <div class="col">
                        <form:label path="name">Nom Association </form:label>
                        <form:input path="name"/>
                        <span class="error"></span>
                    </div>
                    <div class="col">
                        <label for="logo">Logo </label>
                        <input type="file" name="logo" id="logo" accept=".jpg,.jpeg,.png"/>
                        <span class="error" id="errorFile"></span>
                    </div>
                </div>
                
                <div class="row">
                	<div class="col">
                        <form:label path="url">Site internet :</form:label>
                        <form:input path="url" type="url" placeholder="https://www.exemple.fr"/>
                    </div>	                        
                 	<div class="col">
                        <form:label path="address">Adresse</form:label>
                    	<form:input path="address" id="address" pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\\u00e0\\u00e2\\u00e4\\u00e7\\u00e8\\u00e9\\u00ea\\u00ee\\u00ef\\u00f4\\u00f6\\u00f9\\u00fb\\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\\u00e0\\u00e2\\u00e4\\u00e7\\u00e8\\u00e9\\u00ea\\u00ee\\u00ef\\u00f4\\u00f6\\u00f9\\u00fb\\u00fc -]+" placeholder="123 rue de la fleur 45678 Paris"/>
                    	<span class="error"></span>
                   </div>
                </div>
             
                
                <div class="row">
                	<div class="col">
                        <form:label path="email">Mail</form:label>
                        <form:input path="email" type="email"/>
                    </div>
                
                 <div class="col">
                        <form:label path="password">Mot de Passe</form:label>
                        <form:input type="password" path="password" id="password" pattern=".{8,}" />
                        <span class="error"></span>
                    </div>
                </div>
                
                <div class="row">
                    <input type="reset" value="Réinitialiser" class="submit" name="Reset All" id="reset" />
                    <input type="submit" value="Valider" class="submit" name="submit" id="submit" />
                </div>
            </form:form>
        </div>
		
		
		<c:choose>
			<c:when test="${sessionScope.sessionAssociation.eventInProgress}">
				<button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteAccount" id="btnDeleteAccount" disabled>Supprimer mon compte</button>
			</c:when>
			<c:otherwise>
				<button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteAccount" id="btnDeleteAccount">Supprimer mon compte</button>
			</c:otherwise>
		</c:choose>
		
		<!-- Modal DeleteEvent -->
		<div class="modal fade" id="deleteAccount" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Clotûre de l'événement</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						Etes vous sûr de vouloir supprimer votre compte ?
					</div>
					<div class="modal-footer">
						<a class="btn btn-danger" href='<c:url value="deleteAccount"/>' role="button">Valider</a>
					</div>
				</div>
			</div>
		</div>

		<!-- footer -->
				
		<!-- Lien Jquery et js bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	    <script type='text/javascript' src='../resources/js/template/jquery.collapsible.min.js'></script>
	    <script type='text/javascript' src='../resources/js/template/swiper.min.js'></script>
	    <script type='text/javascript' src='../resources/js/template/jquery.countdown.min.js'></script>
	    <script type='text/javascript' src='../resources/js/template/circle-progress.min.js'></script>
	    <script type='text/javascript' src='../resources/js/template/jquery.countTo.min.js'></script>
	    <script type='text/javascript' src='../resources/js/template/jquery.barfiller.js'></script>
	    <script type='text/javascript' src='../resources/js/template/custom.js'></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<%-- 	    <script type="text/javascript" src="<c:out value="../resources/js/scriptRegex.js"/>"></script> --%>
		<script type="text/javascript" src="<c:url value="/resources/js/scriptFiles.js"/>"></script>
	    <script type="text/javascript" src="<c:url value="/resources/js/scriptRegex.js"/>"></script>
	
	</body>
</html>
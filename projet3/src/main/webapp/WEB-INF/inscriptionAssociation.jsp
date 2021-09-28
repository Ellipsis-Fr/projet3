<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Inscription Association</title>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    
	    <!-- Registration css -->
	    <link rel="stylesheet" href="<c:url value="resources/css/template/styleRegistrationAssociation.css"/>">
	   
	</head>
	<body>
		<!-- body -->
		<div class="main">
	        <div class="container">
	            <div class="signup-content">
	                <div class="signup-img">
	                    <img src="<c:out value="resources/images/template/signup-img.jpg"/>" alt="">
	                </div>
	                <div class="signup-form">
	                    <form:form modelAttribute="association" method="POST" class="register-form" id="formAssociationRegistration" action="registrationAssociation" enctype="multipart/form-data">
	                        <h2>Inscription</h2>
	                        
	                        <div class="form-row">
	                            <div class="form-group">
	                                <form:label path="name">Nom Association *</form:label>
	                                <form:input path="name" required="required"/>
	                                <span class="error"></span>
	                            </div>
	                            <div class="form-group">
	                                <form:label path="rna">Numéro RNA *</form:label>
	                                <form:input path="rna" id="rna" pattern="^W\d{9}$" placeholder="W123456789" required="required"/>
	                                <span  class="error"></span>
	                            </div>
	                        </div>
	                        
	                        <div class="form-row">
	                        	<div class="form-group">
	                                <form:label path="url">Site internet :</form:label>
	                                <form:input path="url" type="url" placeholder="https://www.exemple.fr"/>
	                            </div>
	                        
		                        <div class="form-group">
	                                <label for="logo">Logo *</label>
	                                <input type="file" name="logo" id="logo" accept=".jpg,.jpeg,.png" required/>
	                                <span class="error" id="errorFile"></span>
	                            </div>
                            </div>
	                       
	                        <div class="form-group">
	                            <form:label path="address">Adresse *</form:label>
	                            <form:input path="address" id="address" pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc -]+" placeholder="123 rue de la fleur 45678 Paris" required="required"/>
	                            <span  class="error"></span>
	                        </div>
	                        
	                        <div class="form-row">
	                        	<div class="form-group">
	                                <form:label path="email">Mail *</form:label>
	                                <form:input path="email" type="email" required="required"/>
	                            </div>
	                        
		                        <div class="form-group">
	                                <form:label path="password">Mot de Passe *</form:label>
	                                <form:input type="password" path="password" id="password" pattern=".{8,}" required="required" />
	                                <span class="error"></span>
	                            </div>
                            </div>
	                        
	                        <div class="form-submit">
	                            <input type="reset" value="Réinitialiser" class="submit" name="Reset All" id="resetRegistrationAssociation" />
	                            <input type="submit" value="Valider" class="submit" name="submit" id="submit" />
	                        </div>
	                    </form:form>
	                </div>
	            </div>
	        </div>
    	</div>
		
		
		

	</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${sessionScope.sessionAssociationVisited.name}</title>
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
	    <link rel="stylesheet" href="<c:url value="/resources/css/template/style.css"/>">
	    
	    <!-- bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
		
		<!-- Include Plugin CSS file -->
        <link href="<c:url value="/resources/css/alert-nice-toast/dist/css/nice-toast-js.min.css"/>" rel="stylesheet" />  
        
	</head>
	<body>
			
		<!-- header -->
		<c:import url="/WEB-INF/shared/header.jsp"></c:import>
		
		<div class="container">
			<div class="row mt-3">
				<div class="col mx-auto">
					<img src="<c:out value="/${sessionScope.sessionAssociationVisited.pathLogo}"/>" alt="logo">
				</div>
				<div class="col mx-auto">
					<h1>${sessionScope.sessionAssociationVisited.name}</h1>	
				</div>
			</div>
			<div class="row">
				<div class="col mx-auto">
					<h3>Somme des dons : <span id="sumDonations">${sessionScope.sessionEventVisited.sumDonations}</span> €</h3>
				</div>
			</div>
		</div>
		
			

		<hr />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<p>
			Description
		</p>
		
		<p>
			Carroussel Photos
		</p>
		
		<div class="event-page-icon-boxes">
			<div class="container">
				<div class="row">
					<div class="col-12 col-md-6 col-lg-4 mt-4 mt-lg-0">
						<div class="card">
							<div class="card-body">
								<figure class="d-flex justify-content-center">
									<img src="<c:url value="/resources/images/template/hands-gray.png"/>" alt="">
									<img src="<c:url value="/resources/images/template/hands-white.png"/>" alt="">
								</figure>
							
								<h3 class="card-title">Bénévolat</h3>
								
								<div class="card-text">
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tempus vestib ulum mauris quis aliquam. </p>
									<a class="stretched-link" id="btnVolunteer" data-bs-toggle="collapse"  href="#formVolunteer" role="button" aria-expanded="false" aria-controls="formVolunteer"></a>
								</div>
								
							</div>
						</div>
					</div>
				
					<div class="col-12 col-md-6 col-lg-4 mt-4 mt-lg-0">
						<div class="card">
							<div class="card-body">
								<figure class="d-flex justify-content-center">
									<img src="<c:url value="/resources/images/template/donation-gray.png"/>" alt="">
									<img src="<c:url value="/resources/images/template/donation-white.png"/>" alt="">
								</figure>
							
								<h3 class="card-title">Don</h3>
								
								<div class="card-text">
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tempus vestib ulum mauris quis aliquam. </p>
									
								</div>
								<a class="stretched-link" id="btnDonation" data-bs-toggle="collapse" href="#formDonation" aria-expanded="false" aria-controls="formDonation"></a>
							</div>
						</div>
					</div>
					
					<div class="col-12 col-md-6 col-lg-4 mt-4 mt-lg-0">
							<div class="card">
								<div class="card-body">
									<figure class="d-flex justify-content-center">
										<img src="<c:url value="/resources/images/template/charity-gray.png"/>" alt="">
										<img src="<c:url value="/resources/images/template/charity-white.png"/>" alt="">
									</figure>
								
									<h3 class="card-title">Partenariat</h3>
									
									<div class="card-text">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tempus vestib ulum mauris quis aliquam. </p>
										
									</div>
									<a class="stretched-link"  id="btnPartner" data-bs-toggle="collapse" href="#formPartner" role="button" aria-expanded="false" aria-controls="formPartner"></a>
								</div>
							</div>
					</div>
				</div><!-- .row -->
			</div><!-- .container -->
		</div><!-- .home-page-icon-boxes -->

		
		<div class="container">
			<div id="formz">
				<div id="formVolunteer" class="collapse">
					<div class="col-12">
						<label for="loginVolunteer">Je me suis déjà inscrit</label>
						<input type="checkbox" id="loginVolunteer" class="switch"/><br />
					</div>
					
					<div id="formVolunteerRegistration">
						<form class="row g-3" method="post" name ="formVolunteerRegistration">
						
						
							<c:import url="/WEB-INF/shared/fieldsUser.jsp"></c:import>
				<!-- 			<select	name="activites" id="activites" required>  -->
				<!-- 				<option value="">Choissisez une activité...</option> -->
				<%-- 				<c:forEach items="${requestScope.association}" var="activite" varStatus="status"> --%>
				<%-- 					<option value="${activite}">${activite.nom} ${activite.prenom}</option> --%>
				<%-- 				</c:forEach> --%>
				<!-- 			</select> -->
					
							<div class="col-md-6">
								<label for="password" class="form-label">Mot de passe </label>
								<input type="password" class="form-control" id="password" name="password" pattern=".{8,}" maxlength="30" required/>
								<span class="error"></span>
							</div>
							
							<div class="col-12">	
								<input type="checkbox" name="contact" id="contact"/>
								<label for="contact">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>
							</div>
							
							<div class="col-12">	
								<input type="submit" class="btn btn-primary" id="submitFormVolunteerRegistration" value="Valider" />
							</div>
						</form>
					</div>
					<div id="formVolunteerToLogin" class="collapse">
						<form class="row g-3" method="post" name="formVolunteerToLogin">
							
							<div class="col-md-6">
								<label for="email" class="form-label">Email </label>
								<input type="email" class="form-control" id="email" name="email" pattern="^(([a-z0-9!\#$%&\\\'*+/=?^_`{|}~-]+\.?)*[a-z0-9!\#$%&\\\'*+/=?^_`{|}~-]+)@(([a-z0-9-_]+\.?)*[a-z0-9-_]+)\.[a-z]{2,}$" size="20" maxlength="60" required/>
								<span class="error"></span>
							</div>
							
							<div class="col-md-6">
								<label for="password" class="form-label">Mot de passe </label>
								<input type="password" class="form-control" id="password" name="password" pattern=".{8,}" maxlength="30" required/>
								<span class="error"></span>
							</div>
							
							<div class="col-12">
								<input type="submit" class="btn btn-primary" id="submitFormVolunteerToLogin" />
							</div>
						</form>
					</div>
			
				</div>
				<div id="formDonation" class="collapse">
					<form class="row g-3" method="POST" name="formDonation">
						<div class="col-12">
							<input type="checkbox" name="userType" id="userType" class="switch"/>
							<label for="userType">Je représente une société.</label><br /><br />
						</div>
						
						<div id="fieldsSociety"><c:import url="/WEB-INF/shared/fieldsUserSociety.jsp"></c:import></div>
						
						<c:import url="/WEB-INF/shared/fieldsUser.jsp"></c:import>
						
						<div class="col-md-6">
								<label for="amount" class="form-label">Montant</label>
								<input type="text" class="form-control" id="amount" name="amount" pattern="^\d+$" required/>
								<span class="error"></span>
						</div>
						
						<div class="col-md-4">
							<div class="form-check form-check-inline" hidden>
								<input class="form-check-input" type="radio" name="paymentMethod" id="paymentMethodPaypal" value="0" required>
								<label class="form-check-label" for="paymentMethodPaypal">Paypal</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="paymentMethod" id="paymentMethodCheck" value="1" required>
								<label class="form-check-label" for="paymentMethodCheck">Chèque</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="paymentMethod" id="paymentMethodCb" value="0" required>
								<label class="form-check-label" for="paymentMethodCb">Carte Bancaire</label>
							</div>
						</div>
						
						<div id="paymentCb" class="collapse">
							<div class="col-md-3">
								<label for="idCb" class="form-label">Numéro Carte bancaire</label>
								<input type="text" class="form-control" id="idCb" name="idCb" pattern="^\d{16}$" maxlength="16" required/>
								<span class="error"></span>
							</div>
							<div class="col-md-3">
								<label for="month" class="form-label">Mois</label>
								<input type="text" class="form-control" id="month" name="month" maxlength="30" required/>
								<span class="error"></span>
							</div>
							<div class="col-md-3">
								<label for="year" class="form-label">Année</label>
								<input type="text" class="form-control" id="year" name="year" maxlength="30" required/>
								<span class="error"></span>
							</div>
							<div class="col-md-3">
								<label for="idCheck" class="form-label">Code de Vérification</label>
								<input type="text" class="form-control" id="idCheck" name="idCheck" pattern="^\d{3}$" maxlength="30" required/>
								<span class="error"></span>
							</div>
						</div>

						
						<div class="col-12">
							<input type="checkbox" name="contact" id="contact"/>
							<label for="contact">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>
						</div>
						<div class="col-12">
							<input type="submit" class="btn btn-primary" id="submitFormDonation" value="Valider" />
						</div>
					</form>
				</div>
				<div id="formPartner" class="collapse">
					<div class="col-12">
						<label for="loginPartner">Je me suis déjà inscrit</label>
						<input type="checkbox" id="loginPartner" class="switch"/>
					</div>
					
					<div id="formPartnerRegistration">
						<form class="row g-3" method="post" name="formPartnerRegistration">
							<c:import url="/WEB-INF/shared/fieldsUserSociety.jsp"></c:import>
						
							<c:import url="/WEB-INF/shared/fieldsUser.jsp"></c:import>
						
							<div class="col-md-6">
								<label for="password" class="form-label">Mot de passe </label>
								<input type="password" class="form-control" id="password" name="password" pattern=".{8,}" maxlength="30" required/>
								<span class="error"></span>
							</div>
							
							<div class="col-12">
								<label for="description" class="form-label">Présentez votre Société et ses valeurs</label>
								<textarea id="description" class="form-control" name="description" rows="5" required></textarea>
							</div>
							
							<div class="col-12">
								<input type="checkbox" name="contact" id="contact"/>
								<label for="contact">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>						
							</div>	
							
							<div class="col-12">
								<input type="submit" class="btn btn-primary" id="submitPartnerRegistration" value="Valider"/>
							</div>
						</form>
					</div>
					<div id="formPartnerToLogin" class="collapse">
						<form class="row g-3" method="post" name="formPartnerToLogin">
							
							<div class="col-md-6">
								<label for="email" class="form-label">Email </label>
								<input type="email" class="form-control" id="email" name="email" pattern="^(([a-z0-9!\#$%&\\\'*+/=?^_`{|}~-]+\.?)*[a-z0-9!\#$%&\\\'*+/=?^_`{|}~-]+)@(([a-z0-9-_]+\.?)*[a-z0-9-_]+)\.[a-z]{2,}$" size="20" maxlength="60" required/>
								<span class="error"></span>
							</div>
							
							<div class="col-md-6">
								<label for="password" class="form-label">Mot de passe </label>
								<input type="password" class="form-control" id="password" name="password" pattern=".{8,}" maxlength="30" required/>
								<span class="error"></span>
							</div>
							
							<div class="col-12">
								<input type="submit" class="btn btn-primary" id="submitFormPartnerToLogin" value="Valider" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		<p>
			Carroussel Activités validées
		</p>
		
		<p>
			Carroussel Activités en besoin de bénévoles et/ou financement
		</p>
		
		
	
		<!-- footer -->
		<c:import url="/WEB-INF/shared/footer.jsp"></c:import>
		
		<!-- Lien Jquery et js bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/jquery.collapsible.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/swiper.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/jquery.countdown.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/circle-progress.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/jquery.countTo.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/jquery.barfiller.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/custom.js"/>'></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	    <script type="text/javascript" src="<c:url value="/resources/js/scriptAjax.js"/>"></script>
	    <script type="text/javascript" src="<c:url value="/resources/js/scriptForms.js"/>"></script>
	    <script type="text/javascript" src="<c:url value="/resources/js/scriptRegex.js"/>"></script>
	    <script src="<c:url value="/resources/css/alert-nice-toast/dist/js/nice-toast-js.min.js"/>"></script>
	    
	</body>
</html>


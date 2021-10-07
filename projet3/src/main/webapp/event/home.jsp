<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${sessionScope.sessionAssociationVisited.name}</title>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
<%-- 	    <link rel="stylesheet" href='<c:url value="/resources/css/template/bootstrap.min.css"/>'> --%>
	
	    <!-- FontAwesome CSS -->
<%-- 	    <link rel="stylesheet" href='<c:url value="/resources/css/template/font-awesome.min.css"/>'> --%>
	
	    <!-- ElegantFonts CSS -->
<%-- 	    <link rel="stylesheet" href='<c:url value="/resources/css/template/elegant-fonts.css"/>'> --%>
	
	    <!-- themify-icons CSS -->
<%-- 	    <link rel="stylesheet" href='<c:url value="/resources/css/template/themify-icons.css"/>'> --%>
	
	    <!-- Swiper CSS -->
	    <link rel="stylesheet" href='<c:url value="/resources/css/template/swiper.min.css"/>'>
	
	    <!-- Styles -->
	    <link rel="stylesheet" href="<c:url value="/resources/css/template/style.css"/>">
	    
	    <!-- bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
		
		<!-- Include Plugin CSS file -->
        <link href="<c:url value="/resources/css/alert-nice-toast/dist/css/nice-toast-js.min.css"/>" rel="stylesheet" />

        
<!--         Bootstrap CSS -->
<%-- 	    <link rel="stylesheet" href='<c:url value="resources/css/template/bootstrap.min.css"/>'> --%>
	
		<!-- FontAwesome CSS --> 
<%-- 	    <link rel="stylesheet" href='<c:url value="resources/css/template/font-awesome.min.css"/>'> --%>
	
		<!-- ElegantFonts CSS --> 
<%-- 	    <link rel="stylesheet" href='<c:url value="resources/css/template/elegant-fonts.css"/>'> --%>
	
		<!-- themify-icons CSS--> 
<%-- 	    <link rel="stylesheet" href='<c:url value="resources/css/template/themify-icons.css"/>'> --%>
	
 		<!-- Swiper CSS --> 
<%-- 	    <link rel="stylesheet" href='<c:url value="resources/css/template/swiper.min.css"/>'> --%>
	
		<!-- Styles -->
		<link rel="stylesheet"
		href="<c:url value="resources/css/template/style.css"/>">   
        
        
	</head>
	<body id="event">
			
		<!-- header -->
		<c:import url="/WEB-INF/shared/header.jsp"></c:import>
		
		<span id="userLogged" hidden='hidden'>${sessionScope.sessionUserLogged}</span> <!-- Use to know in scriptForms if an user is logged or not ...-->
		<span id="roleUserLogged" hidden='hidden'>${sessionScope.sessionRoleLogged.role}</span> <!-- ... and what's his role -->
		<span id="msg" hidden='hidden'>${requestScope.msg}</span> <!-- Use to lauch msg -->

		<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
			<div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<button type="button" class="btn-close" data-bs-dismiss="toast"	aria-label="Close"></button>
				</div>
				<div class="toast-body">
					${requestScope.msg}
				</div>
			</div>
		</div>

		<!-- header Asso -->
		<div class="page-header-asso" style="background: url(${sessionScope.sessionAssociationVisited.document.pathHeader}) no-repeat center;">
			<div class="container">
				<div class="row">
					<div class="col-6" align="left">
						<img src="<c:out value="${sessionScope.sessionAssociationVisited.pathLogo}"/>" alt="logo" style="max_height: 150px; max-width: 150px;">
					</div><!-- .col -->
					<div class="col-6" align="left">
						<h1>${sessionScope.sessionAssociationVisited.name}</h1>
					</div><!-- .col -->
				</div><!-- .row -->
			</div><!-- .container -->
		</div><!-- .page-header -->
		
		<div class="container">
			<div class="row my-4">
				<div class="col-12" align="center">
					<h3>Somme des dons : <span id="sumDonations">${sessionScope.sessionEventVisited.sumDonations}</span> €</h3>
				</div><!-- .col -->
			</div><!-- .row -->
		</div><!-- .container -->
	
	
<!-- 	<div class="container"> -->
<!-- 			<div class="row mt-3"> -->
<!-- 				<div class="col mx-auto"> -->
<%-- 					<img src="<c:out value="/${sessionScope.sessionAssociationVisited.pathLogo}"/>" alt="logo"> --%>
<!-- 				</div> -->
<!-- 				<div class="col mx-auto"> -->
<%-- 					<h1>${sessionScope.sessionAssociationVisited.name}</h1>	 --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col mx-auto"> -->
<%-- 					<h3>Somme des dons : <span id="sumDonations">${sessionScope.sessionEventVisited.sumDonations}</span> €</h3> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="content">
			<div class="container">
				<div class="row mb-3">
					<div class="col-12">
						<p>
							${sessionScope.sessionAssociationVisited.document.paragraph_1}
						</p>
					</div><!-- .col -->
				</div><!-- .row -->
			</div><!-- .container -->
			
			<div class="container">
				<div class="row mb-3" align="center">
					<div class="col-2">
					</div>
					<div class="col-8">
						<c:choose>
							<c:when test="${sessionScope.sessionAssociationVisited.document.photos.size() == 1}">
							
								<img src="<c:out value="${sessionScope.sessionAssociationVisited.document.photos.get(0).pathPhoto}"/>" class="d-block w-100" alt="${sessionScope.sessionAssociationVisited.document.photos.get(0).name}">
							
							</c:when>
							<c:when test="${sessionScope.sessionAssociationVisited.document.photos.size() > 1}">
							
								<div id="photosAsso" class="carousel slide" data-bs-ride="carousel">
									<div class="carousel-inner">
										<c:forEach items="${sessionScope.sessionAssociationVisited.document.photos}" var="photo" varStatus="status">
											<c:choose>
												<c:when test="${status.index == 0}">
													<div class="carousel-item active">
														<img src="<c:out value="${photo.pathPhoto}"/>" class="d-block w-100" alt="${photo.name}">
													</div>
												</c:when>
												<c:otherwise>
													<div class="carousel-item">
														<img src="<c:out value="${photo.pathPhoto}"/>" class="d-block w-100" alt="${photo.name}">
													</div>
												</c:otherwise>
											</c:choose>											
										</c:forEach>
									</div>
								</div>
							
							
							</c:when>
						</c:choose>
					</div><!-- .col -->
				</div><!-- .row -->
			</div><!-- .container -->
	
	
			
			<div class="container">
				<div class="row mb-3">
					<div class="col-12">
						<p>
							${sessionScope.sessionAssociationVisited.document.paragraph_2}
						</p>
					</div><!-- .col -->
				</div><!-- .row -->
			</div><!-- .container -->
			
			<div class="event-page-icon-boxes">
				<div class="container">
					<div class="row mb-3">
						<c:choose>
							<c:when test="${empty sessionScope.sessionUserLogged}">
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
							</c:when>
							<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'partner'}">
								<div class="col-2 mt-4 mt-lg-0">
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-12 col-md-6 col-lg-4 mt-4 mt-lg-0">
								</div>
							</c:otherwise>
						</c:choose>
						
					
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
						
						<c:choose>
							<c:when test="${empty sessionScope.sessionUserLogged}">
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
							</c:when>
							<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'partner'}">
								<div class="col-12 col-md-6 col-lg-4 mt-4 mt-lg-0">
									<div class="card">
										<div class="card-body">
											<figure class="d-flex justify-content-center">
												<img src="<c:url value="/resources/images/template/charity-gray.png"/>" alt="">
												<img src="<c:url value="/resources/images/template/charity-white.png"/>" alt="">
											</figure>
										
											<h3 class="card-title">Activité</h3>
											
											<div class="card-text">
												<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tempus vestib ulum mauris quis aliquam. </p>
												<a class="stretched-link" id="btnActivity" data-bs-toggle="collapse"  href="#formActivity" role="button" aria-expanded="false" aria-controls="formActivity"></a>
											</div>
											
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-12 col-md-6 col-lg-4 mt-4 mt-lg-0">
								</div>
							</c:otherwise>
						</c:choose>
					</div><!-- .row -->
				</div><!-- .container -->
			</div><!-- .home-page-icon-boxes -->
	
			
			<div class="container">
				<div id="formz">						
				
					</div>
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
									<input type="submit" class="btn gradient-bg mr-2" id="submitFormVolunteerRegistration" value="Valider" />
								</div>
							</form>
						</div>
						<div id="formVolunteerToLogin" class="collapse">
							<form class="row g-3" method="post" name="formVolunteerToLogin" action="connexion">
								
								<div class="col-md-6">
									<label for="email" class="form-label">Email </label>
									<input type="email" class="form-control" id="email" name="email" maxlength="60" required/>
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="password" class="form-label">Mot de passe </label>
									<input type="password" class="form-control" id="password" name="password" maxlength="30" required/>
									<span class="error"></span>
								</div>
								
								<input type="hidden" id="role" name="role" value="volunteer">
								
								<div class="col-12">
									<input type="submit" class="btn gradient-bg mr-2" id="submitFormVolunteerToLogin" value="Valider" />
								</div>
							</form>
						</div>
				
					</div>
					<div id="formParticipant" class="collapse">
						<div class="col-12">
							<label for="loginParticipant">Je me suis déjà inscrit</label>
							<input type="checkbox" id="loginParticipant" class="switch"/><br />
						</div>
						
						<div id="formParticipantRegistration">
							<form class="row g-3" method="post" name ="formParticipantRegistration">
							
							
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
									<input type="submit" class="btn gradient-bg mr-2" id="submitFormParticipantRegistration" value="Valider" />
								</div>
							</form>
						</div>
						<div id="formParticipantToLogin" class="collapse">
							<form class="row g-3" method="post" name="formParticipantToLogin" action="connexion">
								
								<div class="col-md-6">
									<label for="email" class="form-label">Email </label>
									<input type="email" class="form-control" id="email" name="email" maxlength="60" required/>
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="password" class="form-label">Mot de passe </label>
									<input type="password" class="form-control" id="password" name="password" maxlength="30" required/>
									<span class="error"></span>
								</div>
								
								<input type="hidden" id="role" name="role" value="participant">
								
								<div class="col-12">
									<input type="submit" class="btn gradient-bg mr-2" id="submitFormParticipantToLogin" value="Valider" />
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
							
	<%-- 						<span id="fieldsSociety"><c:import url="/WEB-INF/shared/fieldsUserSociety.jsp"></c:import></span> --%>
							<c:import url="/WEB-INF/shared/fieldsUserSociety.jsp"></c:import>
							
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
								<c:choose>
									<c:when test="${!empty sessionScope.sessionUserLogged}">
										<c:choose>
											<c:when test="${sessionScope.sessionUserLogged.recontactable == 'true'}">
												<input type="checkbox" name="contact" id="contact2" checked/>
												<label for="contact2">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="contact" id="contact2"/>
												<label for="contact2">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="contact" id="contact2"/>
										<label for="contact2">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-12">
								<input type="submit" class="btn gradient-bg mr-2" id="submitFormDonation" value="Valider" />
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
									<textarea id="description" class="form-control" name="description" rows="5" maxlength="500" required></textarea>
									<span id="counter"></span>
								</div>
								
								<div class="col-12">
									<input type="checkbox" name="contact" id="contact3"/>
									<label for="contact3">Souhaitez vous être recontactés lors de nouvelles collectes lancées par notre association ?</label>						
								</div>	
								
								<div class="col-12">
									<input type="submit" class="btn gradient-bg mr-2" id="submitPartnerRegistration" value="Valider"/>
								</div>
							</form>
						</div>
						<div id="formPartnerToLogin" class="collapse">
							<form class="row g-3" method="post" name="formPartnerToLogin" action="connexion">
								
								<div class="col-md-6">
									<label for="email" class="form-label">Email </label>
									<input type="email" class="form-control" id="email" name="email" maxlength="60" required/>
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="password" class="form-label">Mot de passe </label>
									<input type="password" class="form-control" id="password" name="password" maxlength="30" required/>
									<span class="error"></span>
								</div>
								
								<input type="hidden" id="role" name="role" value="partner">
								
								<div class="col-12">
									<input type="submit" class="btn gradient-bg mr-2" id="submitFormPartnerToLogin" value="Valider" />
								</div>
							</form>
						</div>
					</div>
					<div id="formActivity" class="collapse">						
						<div id="formActivityRegistration">
							<form class="row g-3" method="post" name="formActivityRegistration" enctype="multipart/form-data">
															
								<div class="col-md-6">
									<label for="name" class="form-label">Nom de l'Activité :<span style="color: red">*</span> </label>
									<input type="text" class="form-control" id="name" name="name" pattern="[a-zA-Z-\\s]{3,15}" maxlength="15" required />
									<span class="error"></span>
								</div>
							
								<div class="col-md-6">
									<label for="address" class="form-label" >Adresse :<span style="color: red">*</span></label>
									<input type="text" class="form-control" id="address" name="address" pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\u00e0\u00e2\u00e4\u00e7\u00e8\u00e9\u00ea\u00ee\u00ef\u00f4\u00f6\u00f9\u00fb\u00fc -]+" placeholder="123 rue de la fleur 45678 Paris" maxlength="50" required />
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="startDate" class="form-label" >Date de début :<span style="color: red">*</span> </label>
									<input name="startDate" type="date" class="form-control" placeholder="date de debut" required="required"/>
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="endDate" class="form-label">Date de fin : <span style="color: red">*</span></label>
									<input name="endDate" type="date" class="form-control" placeholder="date de fin" required="required" />
									<span class="error"></span>
								</div>
						
								<div class="col-md-6">
									<label for="description" class="form-label">Description :</label>
									<input type="text" class="form-control" id="description" name="description" placeholder="Description de l'activité" maxlength="100"/>
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="photo" class="form-label">Photo <span style="color: red">*</span></label>
									<input type="file" class="form-control" id="photo" name="photo" accept=".jpg,.jpeg,.png" required />
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
									<label for="volunteerNeeded" class="form-label">Bénévole(s) nécessaire(s) pour l'activité :<span style="color: red">*</span></label>
									<input type="text" class="form-control" id="volunteerNeeded" name="volunteerNeeded" value="0" pattern="^\d+$" required />
									<span class="error"></span>
								</div>
								
								<div class="col-md-6">
								</div>
								
								<div class="col-md-6">
									<div class="form-check form-check-inline">
										<label for="withParticipant">Avec participant</label>
										<input class="form-check-input" type="radio" name="category" id="withParticipant" value="0" required> 
									</div>
								
									<div class="form-check form-check-inline">
										<label	for="withoutParticipant">Sans participant</label>
										<input class="form-check-input" type="radio" name="category" id="withoutParticipant" value="1" required>
									</div>
								</div>
								
								<input id="necessaryFunding" name="necessaryFunding" type="hidden" value="0"/>	
								
								<div class="col-12">
									<input type="submit" class="btn gradient-bg mr-2" id="submitPartnerRegistration" value="Valider"/>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	
				
				
			<div class="our-causes">
				<div class="container">					
					<c:choose>
						<c:when test="${empty requestScope.requestValidActivities}">
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Aucune Activité Disponible</h2>
									</div>
									<!-- .section-heading -->
								</div>	<!-- .col -->
							</div> <!-- .row -->
						</c:when>
						<c:when test="${requestScope.requestValidActivities.size() == 1}">	
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Activité Disponible</h2>
									</div>
									<!-- .section-heading -->
								</div>	<!-- .col -->
							</div> <!-- .row -->
							
							<div class="row">
								<div class="col-4"></div>
								<div class="col-4">
									<div class="cause-wrap">
										<c:set var="activity" scope="page" value="${requestScope.requestValidActivities.get(0)}"/>
										<figure class="m-0">
											<img src='<c:url value="${activity.pathPhoto}"/>' style="height: 147.6px;">
											<div class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
												<c:choose>
													<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'participant'}">
														
														<c:set var="isRegistered" scope="page" value="false"/>
																		
														<c:forEach items="${sessionScope.sessionRoleLogged.activities}" var="participantActivity">
															<c:choose>
																<c:when test="${participantActivity.id == activity.id}">
																	<c:set var="isRegistered" value="true"/>
																	<a class="btn gradient-bg mr-2" href='<c:url value="unsubscribeActivity?id=${activity.id}"/>' role="button"> Se Désinscrire </a>
																</c:when>
															</c:choose>
														</c:forEach>
														
														<c:if test="${!isRegistered}">
															<a class="btn gradient-bg mr-2" href='<c:url value="subscribeActivity?id=${activity.id}"/>' role="button"> Participer </a>
														</c:if>

													</c:when>
													<c:when test="${empty sessionScope.sessionUserLogged}">
														<a class="btn gradient-bg mr-2" data-bs-toggle="collapse"  href="#formParticipant" role="button" aria-expanded="false" aria-controls="formParticipant" onclick="apparitionForm(event);selectForm(event);"> Participer </a>
													</c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>
												
											</div>
											
											<!-- .figure-overlay -->
										</figure>

										<div class="cause-content-wrap">
											<header	class="entry-header d-flex flex-wrap align-items-center">
												<h3 class="entry-title w-100 m-0"> <a>${activity.name}</a></h3>
											</header>
											<!-- .entry-header -->

											<div class="entry-content"> <p class="m-0">${activity.description}</p></div>
											<!-- .entry-content -->

											

												<div
													class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
												<div class="fund-raised-total mt-4"> ${activity.startDate}</div>
												<!-- .fund-raised-total -->

												<div class="fund-raised-goal mt-4"> ${activity.endDate}</div>
												<!-- .fund-raised-goal -->
												</div>
												<!-- .fund-raised-details -->
											<!-- .fund-raised -->
										</div>
										<!-- .cause-content-wrap -->
									</div>
									<!-- .cause-wrap -->
								</div>
							
							</div>
						</c:when>
						
						<c:when test="${requestScope.requestValidActivities.size() == 2}">	
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Activités Disponibles</h2>
									</div> <!-- .section-heading -->
								</div> <!-- .col -->
							</div> <!-- .row -->
							
							<div class="row">
								<div class="col-2"></div>
								<c:forEach items="${requestScope.requestValidActivities}" var="activity">
									<div class="col-4">
										<div class="cause-wrap">
											<figure class="m-0">
												<img src='<c:url value="${activity.pathPhoto}"/>' style="height: 147.6px;">
												<div class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
													<c:choose>
														<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'participant'}">
															<c:set var="isRegistered" scope="page" value="false"/>
																		
															<c:forEach items="${sessionScope.sessionRoleLogged.activities}" var="participantActivity">
																<c:choose>
																	<c:when test="${participantActivity.id == activity.id}">
																		<c:set var="isRegistered" value="true"/>
																		<a class="btn gradient-bg mr-2" href='<c:url value="unsubscribeActivity?id=${activity.id}"/>' role="button"> Se Désinscrire </a>
																	</c:when>
																</c:choose>
															</c:forEach>
															
															<c:if test="${!isRegistered}">
																<a class="btn gradient-bg mr-2" href='<c:url value="subscribeActivity?id=${activity.id}"/>' role="button"> Participer </a>
															</c:if>
														</c:when>
														<c:when test="${empty sessionScope.sessionUserLogged}">
															<a class="btn gradient-bg mr-2" data-bs-toggle="collapse"  href="#formParticipant" role="button" aria-expanded="false" aria-controls="formParticipant" onclick="apparitionForm(event);selectForm(event);"> Participer </a>
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
													
												</div>
												
												<!-- .figure-overlay -->
											</figure>

											<div class="cause-content-wrap">
												<header	class="entry-header d-flex flex-wrap align-items-center">
													<h3 class="entry-title w-100 m-0"> <a>${activity.name}</a></h3>
												</header>
												<!-- .entry-header -->

												<div class="entry-content"> <p class="m-0">${activity.description}</p></div>
												<!-- .entry-content -->

												

													<div class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
													<div class="fund-raised-total mt-4"> ${activity.startDate}</div>
													<!-- .fund-raised-total -->

													<div class="fund-raised-goal mt-4"> ${activity.endDate}</div>
													<!-- .fund-raised-goal -->
													</div>
													<!-- .fund-raised-details -->
												<!-- .fund-raised -->
											</div> <!-- .cause-content-wrap -->
										</div> <!-- .cause-wrap -->
									</div>
								</c:forEach>
							</div>
						</c:when>
						
						<c:otherwise>
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Activités Disponibles</h2>
									</div> <!-- .section-heading -->
								</div> <!-- .col -->
							</div> <!-- .row -->
							
							<div class="row">
								<div class="col-12">
									<div class="swiper-container causes-slider">
										<div class="swiper-wrapper">
											<c:forEach items="${requestScope.requestValidActivities}" var="activity">
												<div class="swiper-slide">
													<div class="cause-wrap">
														<figure class="m-0">
															<img src='<c:url value="${activity.pathPhoto}"/>' style="height: 147.6px;">
															<div class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
																<c:choose>
																	<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'participant'}">
																		
																		<c:set var="isRegistered" scope="page" value="false"/>
																		
																		<c:forEach items="${sessionScope.sessionRoleLogged.activities}" var="participantActivity">
																			<c:choose>
																				<c:when test="${participantActivity.id == activity.id}">
																					<c:set var="isRegistered" value="true"/>
																					<a class="btn gradient-bg mr-2" href='<c:url value="unsubscribeActivity?id=${activity.id}"/>' role="button"> Se Désinscrire </a>
																				</c:when>
																			</c:choose>
																		</c:forEach>
																		
																		<c:if test="${!isRegistered}">
																			<a class="btn gradient-bg mr-2" href='<c:url value="subscribeActivity?id=${activity.id}"/>' role="button"> Participer </a>
																		</c:if>
																	
																	
																	
																	</c:when>
																	<c:when test="${empty sessionScope.sessionUserLogged}">
																		<a class="btn gradient-bg mr-2" data-bs-toggle="collapse"  href="#formParticipant" role="button" aria-expanded="false" aria-controls="formParticipant" onclick="apparitionForm(event);selectForm(event);"> Participer </a>
																	</c:when>
																	<c:otherwise>
																	</c:otherwise>
																</c:choose>
																
															</div>
															
															<!-- .figure-overlay -->
														</figure>
			
														<div class="cause-content-wrap">
															<header	class="entry-header d-flex flex-wrap align-items-center">
																<h3 class="entry-title w-100 m-0"> <a>${activity.name}</a></h3>
															</header>
															<!-- .entry-header -->
			
															<div class="entry-content"> <p class="m-0">${activity.description}</p></div>
															<!-- .entry-content -->
			
															
			
																<div class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
																<div class="fund-raised-total mt-4"> ${activity.startDate}</div>
																<!-- .fund-raised-total -->
			
																<div class="fund-raised-goal mt-4"> ${activity.endDate}</div>
																<!-- .fund-raised-goal -->
																</div>
																<!-- .fund-raised-details -->
															<!-- .fund-raised -->
														</div> <!-- .cause-content-wrap -->
													</div> <!-- .cause-wrap -->
												</div>
											
											</c:forEach>
										
										
										</div> <!-- .swiper-wrapper -->
									</div> <!-- .swiper-container -->
							
									<!-- Add Arrows -->
									<div class="swiper-button-next flex justify-content-center align-items-center swiper-button" id="idswiper">
										<span>
											<svg viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
												<path d="M1171 960q0 13-10 23l-466 466q-10 10-23 10t-23-10l-50-50q-10-10-10-23t10-23l393-393-393-393q-10-10-10-23t10-23l50-50q10-10 23-10t23 10l466 466q10 10 10 23z" />
											</svg>
										</span>
									</div>
									<div class="swiper-button-prev flex justify-content-center align-items-center swiper-button" id="idswiper">
										<span>
											<svg viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
												<path d="M1203 544q0 13-10 23l-393 393 393 393q10 10 10 23t-10 23l-50 50q-10 10-23 10t-23-10l-466-466q-10-10-10-23t10-23l466-466q10-10 23-10t23 10l50 50q10 10 10 23z" />
											</svg>
										</span>
									</div>				
							</div> <!-- .col -->
						</div> <!-- .row -->
							
						
						</c:otherwise>

					</c:choose>
 				</div>	<!-- .container -->
			</div> <!-- .our-causes -->

			
		<br />
		<br />
		<br />
			
			

		<br />
		<br />
		<br />
		<br />
		<br />
		
		<div class="our-causes">
				<div class="container">					
					<c:choose>
						<c:when test="${empty requestScope.requestInprogressActivities}">
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Ici sont présentées les activités en attente de soutien</h2>
									</div>
									<!-- .section-heading -->
								</div>	<!-- .col -->
							</div> <!-- .row -->
						</c:when>
						<c:when test="${requestScope.requestInprogressActivities.size() == 1}">	
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Activité en Attente de soutien</h2>
									</div>
									<!-- .section-heading -->
								</div>	<!-- .col -->
							</div> <!-- .row -->
							
							<div class="row">
								<div class="col-4"></div>
								<div class="col-4">
									<div class="cause-wrap">
										<figure class="m-0">
											<img src='<c:url value="${requestScope.requestInprogressActivities.get(0).pathPhoto}"/>' style="height: 147.6px;">
											<div class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
												<c:choose>
													<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'volunteer'}">
														
														<c:set var="isRegistered" scope="page" value="false"/>
																		
														<c:forEach items="${sessionScope.sessionRoleLogged.activities}" var="volunteerActivity">
															<c:choose>
																<c:when test="${volunteerActivity.id == requestScope.requestInprogressActivities.get(0).id}">
																	<c:set var="isRegistered" value="true"/>
																	<a class="btn gradient-bg mr-2" href='<c:url value="unsubscribeActivity?id=${requestScope.requestInprogressActivities.get(0).id}"/>' role="button"> Se Désinscrire </a>
																</c:when>
															</c:choose>
														</c:forEach>
														
														<c:if test="${!isRegistered}">
															<a class="btn gradient-bg mr-2" href='<c:url value="subscribeActivity?id=${requestScope.requestInprogressActivities.get(0).id}"/>' role="button"> Aider </a>
														</c:if>

													</c:when>
													
													<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'partner'}">
														<a class="btn gradient-bg mr-2" href='#' role="button"> Financer </a>
													</c:when>
													<c:otherwise>
														<p style="text-align:center">
															<span style="color:rgba(255,54,0,1)">Dévenez Bénévole ou Partenaire et aidez-nous à concrétiser ce projet !</span>
														</p>
													</c:otherwise>
												</c:choose>
												
											</div>
											
											<!-- .figure-overlay -->
										</figure>

										<div class="cause-content-wrap">
											<header	class="entry-header d-flex flex-wrap align-items-center">
												<h3 class="entry-title w-100 m-0"> <a>${requestScope.requestInprogressActivities.get(0).name}</a></h3>
											</header>
											<!-- .entry-header -->

											<div class="entry-content"> <p class="m-0">${requestScope.requestInprogressActivities.get(0).description}</p></div>
											<!-- .entry-content -->

											

												<div
													class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
												<div class="fund-raised-total mt-4"> ${requestScope.requestInprogressActivities.get(0).startDate}</div>
												<!-- .fund-raised-total -->

												<div class="fund-raised-goal mt-4"> ${requestScope.requestInprogressActivities.get(0).endDate}</div>
												<!-- .fund-raised-goal -->
												</div>
												<!-- .fund-raised-details -->
											<!-- .fund-raised -->
										</div>
										<!-- .cause-content-wrap -->
									</div>
									<!-- .cause-wrap -->
								</div>
							
							</div>
						</c:when>
						
						<c:when test="${requestScope.requestInprogressActivities.size() == 2}">	
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Activités en Attente de soutien</h2>
									</div> <!-- .section-heading -->
								</div> <!-- .col -->
							</div> <!-- .row -->
							
							<div class="row">
								<div class="col-2"></div>
								<c:forEach items="${requestScope.requestInprogressActivities}" var="activity">
									<div class="col-4">
										<div class="cause-wrap">
											<figure class="m-0">
												<img src='<c:url value="${activity.pathPhoto}"/>' style="height: 147.6px;">
												<div class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
													<c:choose>
														<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'volunteer'}">
														
														<c:set var="isRegistered" scope="page" value="false"/>
																		
														<c:forEach items="${sessionScope.sessionRoleLogged.activities}" var="volunteerActivity">
															<c:choose>
																<c:when test="${volunteerActivity.id == activity.id}">
																	<c:set var="isRegistered" value="true"/>
																	<a class="btn gradient-bg mr-2" href='<c:url value="unsubscribeActivity?id=${activity.id}"/>' role="button"> Se Désinscrire </a>
																</c:when>
															</c:choose>
														</c:forEach>
														
														<c:if test="${!isRegistered}">
															<a class="btn gradient-bg mr-2" href='<c:url value="subscribeActivity?id=${activity.id}"/>' role="button"> Aider </a>
														</c:if>

														</c:when>
														
														<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'partner'}">
															<a class="btn gradient-bg mr-2" href='#' role="button"> Financer </a>
														</c:when>
														<c:otherwise>
															<p style="text-align:center">
																<span style="color:rgba(255,54,0,1)">Dévenez Bénévole ou Partenaire et aidez-nous à concrétiser ce projet !</span>
															</p>
														</c:otherwise>
													</c:choose>
													
												</div>
												
												<!-- .figure-overlay -->
											</figure>

											<div class="cause-content-wrap">
												<header	class="entry-header d-flex flex-wrap align-items-center">
													<h3 class="entry-title w-100 m-0"> <a>${activity.name}</a></h3>
												</header>
												<!-- .entry-header -->

												<div class="entry-content"> <p class="m-0">${activity.description}</p></div>
												<!-- .entry-content -->

												

													<div class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
													<div class="fund-raised-total mt-4"> ${activity.startDate}</div>
													<!-- .fund-raised-total -->

													<div class="fund-raised-goal mt-4"> ${activity.endDate}</div>
													<!-- .fund-raised-goal -->
													</div>
													<!-- .fund-raised-details -->
												<!-- .fund-raised -->
											</div> <!-- .cause-content-wrap -->
										</div> <!-- .cause-wrap -->
									</div>
								</c:forEach>
							</div>
						</c:when>
						
						<c:otherwise>
							<div class="row">							
								<div class="coL-12">
									<div class="section-heading">
										<h2 class="entry-title">Activité en Attente de soutien</h2>
									</div> <!-- .section-heading -->
								</div> <!-- .col -->
							</div> <!-- .row -->
							
							<div class="row">
								<div class="col-12">
									<div class="swiper-container causes-slider">
										<div class="swiper-wrapper">
											<c:forEach items="${requestScope.requestInprogressActivities}" var="activity">
												<div class="swiper-slide">
													<div class="cause-wrap">
														<figure class="m-0">
															<img src='<c:url value="${activity.pathPhoto}"/>' style="height: 147.6px;">
															<div class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
																<c:choose>
																	
																	<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'volunteer'}">
														
																		<c:set var="isRegistered" scope="page" value="false"/>
																						
																		<c:forEach items="${sessionScope.sessionRoleLogged.activities}" var="volunteerActivity">
																			<c:choose>
																				<c:when test="${volunteerActivity.id == activity.id}">
																					<c:set var="isRegistered" value="true"/>
																					<a class="btn gradient-bg mr-2" href='<c:url value="unsubscribeActivity?id=${activity.id}"/>' role="button"> Se Désinscrire </a>
																				</c:when>
																			</c:choose>
																		</c:forEach>
																		
																		<c:if test="${!isRegistered}">
																			<a class="btn gradient-bg mr-2" href='<c:url value="subscribeActivity?id=${activity.id}"/>' role="button"> Aider </a>
																		</c:if>
			
																	</c:when>
																	
																	<c:when test="${!empty sessionScope.sessionUserLogged && sessionScope.sessionRoleLogged.role == 'partner'}">
																		<a class="btn gradient-bg mr-2" href='#' role="button"> Financer </a>
																	</c:when>
																	<c:otherwise>
																		<p style="text-align:center">
																			<span style="color:rgba(255,54,0,1)">Dévenez Bénévole ou Partenaire et aidez-nous à concrétiser ce projet !</span>
																		</p>
																	</c:otherwise>
																	
																</c:choose>
																
															</div>
															
															<!-- .figure-overlay -->
														</figure>
			
														<div class="cause-content-wrap">
															<header	class="entry-header d-flex flex-wrap align-items-center">
																<h3 class="entry-title w-100 m-0"> <a>${activity.name}</a></h3>
															</header>
															<!-- .entry-header -->
			
															<div class="entry-content"> <p class="m-0">${activity.description}</p></div>
															<!-- .entry-content -->
			
															
			
																<div class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
																<div class="fund-raised-total mt-4"> ${activity.startDate}</div>
																<!-- .fund-raised-total -->
			
																<div class="fund-raised-goal mt-4"> ${activity.endDate}</div>
																<!-- .fund-raised-goal -->
																</div>
																<!-- .fund-raised-details -->
															<!-- .fund-raised -->
														</div> <!-- .cause-content-wrap -->
													</div> <!-- .cause-wrap -->
												</div>
											
											</c:forEach>
										
										
										</div> <!-- .swiper-wrapper -->
									</div> <!-- .swiper-container -->
							
									<!-- Add Arrows -->
									<div class="swiper-button-next flex justify-content-center align-items-center swiper-button" id="idswiper">
										<span>
											<svg viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
												<path d="M1171 960q0 13-10 23l-466 466q-10 10-23 10t-23-10l-50-50q-10-10-10-23t10-23l393-393-393-393q-10-10-10-23t10-23l50-50q10-10 23-10t23 10l466 466q10 10 10 23z" />
											</svg>
										</span>
									</div>
									<div class="swiper-button-prev flex justify-content-center align-items-center swiper-button" id="idswiper">
										<span>
											<svg viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
												<path d="M1203 544q0 13-10 23l-393 393 393 393q10 10 10 23t-10 23l-50 50q-10 10-23 10t-23-10l-466-466q-10-10-10-23t10-23l466-466q10-10 23-10t23 10l50 50q10 10 10 23z" />
											</svg>
										</span>
									</div>				
							</div> <!-- .col -->
						</div> <!-- .row -->
							
						
						</c:otherwise>

					</c:choose>
 				</div>	<!-- .container -->
			</div> <!-- .our-causes -->

		
	
	

	
	<br />
	<br />
	
	
		<div class="contact-page-wrap">
		        <div class="container">
		            <div class="row">
		                <div class="col-12 col-lg-5">
		                    <div class="entry-content">
		                        <h2>Contactez-nous</h2>
		
		                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tempus vestib ulum mauris. Lorem ipsum dolor sit amet, conse ctetur. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tempus vestib ulum mauris quis aliquam. Integer accu msan sodales odio, id tempus velit ullamc.</p>
		
		                        <ul class="contact-info p-0">
		                            <li><i class="fa fa-phone"></i><span>+45 677 8993000 223</span></li>
		                            <li><i class="fa fa-envelope"></i><span>office@template.com</span></li>
		                            <li><i class="fa fa-map-marker"></i><span>Main Str. no 45-46, b3, 56832, Los Angeles, CA</span></li>
		                        </ul>
		                    </div>
		                </div><!-- .col -->
		
		                <div class="col-12 col-lg-7">
		                    <form class="contact-form" name="formContact">
		                        <input type="email" placeholder="Email" name="email">
		                        <textarea rows="15" cols="6" placeholder="Message" name="content" maxlength="500"></textarea>
								<input type="hidden" name="typeMessage" value="received">
		                        <span>
		                            <input class="btn gradient-bg" type="submit" value="Envoyez">
		                        </span>
		                    </form><!-- .contact-form -->
		
		                </div><!-- .col -->
		            </div><!-- .row -->
		        </div><!-- .container -->
		    </div>
	
 
	    
	    
   		<!-- footer -->
		<c:import url="/WEB-INF/shared/footer.jsp"></c:import>
		
		<!-- Lien Jquery et js bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type='text/javascript' src='<c:url value="/resources/js/jquery.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/jquery.collapsible.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/swiper.min.js"/>'></script>
	    <script type='text/javascript' src='<c:url value="/resources/js/template/swiper2.js"/>'></script>
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

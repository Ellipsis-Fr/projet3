<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Messagerie</title>
		
		<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
		<link rel="stylesheet"  href="<c:url value="/resources/css/dashboard2/css/styles.css" />">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
		<!-- Include Plugin CSS file -->
        <link href="<c:url value="/resources/css/alert-nice-toast/dist/css/nice-toast-js.min.css"/>" rel="stylesheet" />  
			<!-- bootstrap CSS -->
<!-- 			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"> -->
	</head>
	<body>
	
			 <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="home">${sessionScope.sessionAssociation.name} Admin </a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">   
            </div>
            	<a class="nav-link" href="messaging" role="button" style="text-decoration: none; color: rgba(255, 255, 255, 0.6);"><i class="fa fa-envelope"></i></a>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="editDocument">Paramètres</a></li>
                        <li><a class="dropdown-item" href="editAccount">Mon compte</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="logout">Se déconnecter</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="home">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <div class="sb-sidenav-menu-heading">Interface</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Activités
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href='<c:url value="createActivity"/>'>Créer une activité</a>
                                    <a class="nav-link" href='<c:url value="listActivities"/>'>Liste des activités</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Liste
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Don
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href='<c:url value="listDonations"/>'>Liste des dons</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                            <a class="nav-link" href="password.html">Forgot Password</a>
                                        </nav>
                                    </div>
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">Addons</div>
                            <a class="nav-link" href="charts.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                Charts
                            </a>
                            <a class="nav-link" href="tables.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Tables
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Connecté en tant que :</div>
                        ${sessionScope.sessionAssociation.name} Admin
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Messagerie</h1>
	                    <div class="container mt-5">
	                    	<div class="row mb-5" align="center">
	                    		<div class="col-2 px-2">
	                    			<ul id="buttons">
	                    				<li>
	                    					<div class="btn-group">
												<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="new" style="width:160px;">Nouveau Message</button>
												<button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false"></button>
												<ul class="dropdown-menu">
													<li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="Donnateurs">Donnateurs</a></li>
													<li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="Bénévoles">Bénévoles</a></li>
													<li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="Participants">Participants</a></li>
													<li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="Partenaires">Partenaires</a></li>
													<li><hr class="dropdown-divider"></li>
													<li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="Utilisateurs">Utilisateurs</a></li>
												</ul>
											</div>
	                    				</li>
	                    				<li>
	                    					<button type="button" class="btn btn-primary" style="width:189px;" onclick="switchBox(event)" id="btnMailBox" >Boite de réception</button>
	                    				</li>
	                    				<li>
	                    					<button type="button" class="btn btn-primary" style="width:189px;" onclick="switchBox(event)" id="btnOutBox">Boite d'envoi</button>
	                    				</li>
	                    			</ul>
	                    		
	                    		</div>	                    		
	                    		
	                    		<div class="col-10 px-2">
	                    		
	                    			<table class="table" id="mailBox">
										<thead>
											<tr>
												<th scope="col">Boite de réception</th>
												<th scope="col"></th>
												<th scope="col"></th>
												<th scope="col"></th>
												<th scope="col"></th>
											</tr>
										</thead>
										<tbody>
										
											<c:forEach var="mail" items="${requestScope.requestMails}">

												<c:if test="${mail.messageType == 'received'}">
													<tr>
														<td><a role="button" data-bs-toggle="modal" data-bs-target="#readMessage" data-message="${mail.id}">${mail.sender}</a></td>
														<td><a role="button" data-bs-toggle="modal" data-bs-target="#readMessage" data-message="${mail.id}">${mail.previewContent}</a></td>
														<td><a role="button" data-bs-toggle="modal" data-bs-target="#readMessage" data-message="${mail.id}">${mail.date}</a></td>
														<td><a role="button" data-bs-toggle="modal" data-bs-target="#newMessage" data-bs-whatever="${mail.sender}"><i class="fa fa-reply" aria-hidden="true"></i></a></td>
														<td><a role="button" class="deleteMail" id="${mail.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
														
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
									
									<table class="table" id="outBox" style="display: none;">
										<thead>
											<tr>
												<th scope="col">Boite d'envoi</th>
												<th scope="col"></th>
												<th scope="col"></th>
												<th scope="col"></th>
											</tr>
										</thead>
										<tbody>
										
											<c:forEach var="mail" items="${requestScope.requestMails}">

												<c:if test="${mail.messageType == 'sent'}">
													<tr>
														<td><a role="button" data-bs-toggle="modal" data-bs-target="#readMessage" data-message="${mail.id}">${mail.recipient}</a></td>
														<td><a role="button" data-bs-toggle="modal" data-bs-target="#readMessage" data-message="${mail.id}">${mail.previewContent}</a></td>
														<td>${mail.date}</td>
														<td><a role="button" class="deleteMail" id="${mail.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
														
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
	                    		</div>
	                    		
	                    		
								
							</div>
							
							<!-- Modal newMessage-->
							<div class="modal fade" id="newMessage" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="staticBackdropLabel">Nouveau Message</h5>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="resetMail(event)"></button>
										</div>
										<div class="modal-body">
											<form name="sendMessage" enctype="multipart/form-data">
												<div class="mb-3">
													<label for="recipient" class="col-form-label">Destinataire :</label>
													<input type="email" class="form-control" id="recipient" name="recipient" required>
												</div>
												
												<div class="mb-3">
													<label for="subject" class="col-form-label">Objet :</label>
													<input type="text" class="form-control" id="subject" name="subject" required>
												</div>
												
												<div class="mb-3">
													<label for="content" class="col-form-label">Message:</label>
													<textarea class="form-control" id="messageToSend" name="content" rows="10" maxlength="500" required></textarea>
												</div>
												<input type="hidden" name="typeMessage" value="sent">
                           						<input type="file" name="attachment" class="form-control" id="attachment" accept="image/*,.pdf, .doc, .docx, .xml, .html, .txt">
												<div class="modal-footer">
													<label class="control-label" for="attachment" role="button"><i class="fa fa-paperclip"></i></label>
													<button type="submit" class="btn btn-primary">Envoyer</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							
							
							<!-- Modal readMessage -->
							<div class="modal fade" id="readMessage" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="staticBackdropLabel"></h5>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">																				
											<div class="mb-3">
												<span>Objet : </span>
												<span id="subject"></span>
											</div>
											
											<div class="mb-3">
												<textarea class="form-control" name="content" rows="10" maxlength="500" readonly></textarea>
											</div>
											<div class="modal-footer">
												<button type="button" name="reply" class="btn btn-primary" data-bs-dismiss="modal" data-bs-toggle="modal" data-bs-target="#newMessage">Répondre</button>
												<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="deleteMail(event)">Supprimer</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							
						</div>
					</div>					
				</main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2021</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

		<!-- footer -->
				
		<!-- Lien Jquery et js bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/scriptFiles.js"/>"></script>
	    <script type="text/javascript" src="<c:url value="/resources/js/scriptRegex.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/scriptAjax.js"/>"></script>
<!--         <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script> -->
		<script type="text/javascript" src="<c:url value="/resources/js/scriptForms.js"/>"></script>
		<script src="<c:url value="/resources/js/dashboard2/js/scripts.js"/>"></script>
        <script src="<c:url value="/resources/js/dashboard2/js/datatables-simple-demo.js"/>"></script>
        <script src="<c:url value="/resources/css/alert-nice-toast/dist/js/nice-toast-js.min.js"/>"></script>
	</body>
</html>
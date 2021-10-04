<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifier ma page</title>
		
		<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
		<link rel="stylesheet"  href="<c:url value="/resources/css/dashboard2/css/styles.css" />">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
	
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
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
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
                        <h1 class="mt-4">Page de l'évenement</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Edition de l'Evenement</li>
                        </ol>
	                    <div class="container">
	                    	<div class="row mb-5" align="center">
								<form:form modelAttribute="requestDocument" class="row g-3" method="post" action="editDocument" enctype="multipart/form-data">
									<div class="col-md-12 my-3">
										<form:label path="paragraph_1" class="form-label">Paragraphe 1 - Présentation Association :<span style="color: red">*</span></form:label>
										<form:textarea path="paragraph_1" id="paragraph_1" class="form-control" maxlength="2000" rows="12" required="required" />
										<span></span>
									</div>
								
									<div class="col-md-12 my-3">
										<form:label path="paragraph_2" class="form-label">Paragraphe 2 :</form:label>
										<form:textarea path="paragraph_2" id="paragraph_2" class="form-control" maxlength="2000" rows="12" />
										<span></span>
									</div>
									
									<div class="col-md-12 my-3">
										<label for="header">Image d'en-tête : </label>
                              			<input type="file" name="header" id="header" accept=".jpg,.jpeg,.png"/>
                              			<span class="error"></span>
									</div>
									
									<div class="col-md-12 my-3">
										<label for="photo1"> Photo Caroussel : </label>
                              			<input type="file" name="files" id="photo1" accept=".jpg,.jpeg,.png"/>
                              			<span class="error"></span>
									</div>
									
									<div class="col-md-12 my-3">
										<label for="photo2"> Photo Caroussel : </label>
                              			<input type="file" name="files" id="photo2" accept=".jpg,.jpeg,.png"/>
                              			<span class="error"></span>
									</div>

									
									<div class="col-md-12 my-3">
										<button class="btn btn-primary" id="addPhoto"><i class="fa fa-plus" aria-hidden="true"></i></button>
									</div>
																
									<input id="id" name="id" type="hidden" value="${requestDocument.id}"/>
									
									<div class="col-12" align="right">
										<input type="reset" value="Réinitialiser" class="btn btn-primary" name="Reset All" id="reset" style="margin-right: 20px"/>	
										<input type="submit" class="btn btn-primary" id="submit" value="Valider" />
									</div>
								</form:form>
							</div>

							
							<div class="row mb-4" align="center">
								
								<c:choose>
									<c:when test="${requestScope.requestDocument.photos.size() > 0}">
									
										<table style="border-collapse: collapse;">
											
											<thead>
		                                        <tr>
		                                            <th style="border: 1px solid black; padding: 10px;">Nom de la photo</th>
													<th style="border: 1px solid black; padding: 10px; width: 50px;"></th>
													<th style="border: 1px solid black; padding: 10px; width: 50px;"></th>
		                                        </tr>
		                                    </thead>
											
											<tbody>
												<c:forEach items="${requestScope.requestDocument.photos}" var="photo">
													<tr>
														<td style="border: 1px solid black; padding: 10px;">${photo.name}</td>
														<td style="border: 1px solid black; padding: 10px; text-align:center;"><a href="<c:url value="/${photo.pathPhoto}"/>" target="_blank"><i class="fa fa-camera" aria-hidden="true"></i></a></td>
														<td style="border: 1px solid black; padding: 10px; text-align:center;"><a href="#" class="delete" id="${photo.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
													</tr>									
												</c:forEach>
											</tbody>
										
										</table>
									</c:when>
								</c:choose>

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
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard ${sessionScope.sessionAssociation.name}</title>
		<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
		<link rel="stylesheet"  href="<c:url value="/resources/css/dashboard2/css/styles.css" />">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body onload="inputsToCheck(formEditAssociation.id)">
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
                        <li><a class="dropdown-item" href="#!">Paramètre</a></li>
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
                                        Dons
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
                        <div class="small">Logged in as:</div>
                        ${sessionScope.sessionAssociation.name} Admin
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
           	<main>
           		<div class="container-fluid px-4">
                        <h1 class="mt-4">Compte Admin</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Gestion du compte administrateur</li>
                        </ol>
	                    <div class="container">
	                    <div align="right">
							<!-- Modal -->
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
						</div>
							<div id="formz">
								<div id="formAssociation">	
									<div id="formEditAssociation">
										<form:form modelAttribute="association" class="row g-3" method="post" name ="formEditAssociation" action= "editAccount" enctype="multipart/form-data">
											<div class="col-md-6">
												<form:label for="name" path="name" class="form-label">Nom de l'Association :</form:label>
												<form:input type="text" path="name" class="form-control" id="name" name="name" pattern="[a-zA-Z-\\s]{3,15}" maxlength="25"/>
												<span class="error"></span>
											</div>
											
											<div class="col-md-6">
												<label for="logo" class="form-label">Logo de l'Association :</label>
												<input type="file" class="form-control" id="logo" name="logo" accept=".jpg,.jpeg,.png"/>
												<span class="error"></span>
											</div>
											
											<div class="col-md-6">
												<form:label for="url" path="url" class="form-label">Site internet :</form:label>
												<form:input type="url" path="url" class="form-control" id="url" name="url" placeholder="https://www.exemple.fr"/>
												<span class="error"></span>
											</div>
											
											<div class="col-md-6">
												<form:label for="address" path="address" class="form-label" >Adresse :</form:label>
												<form:input type="text" path="address" class="form-control" id="address" name="address" pattern="\d+\s+(?:bis?\s|ter?\s)?(?:rue?\s|avenue\s|boulevard?\s)([\sa-zA-Z\\u00e0\\u00e2\\u00e4\\u00e7\\u00e8\\u00e9\\u00ea\\u00ee\\u00ef\\u00f4\\u00f6\\u00f9\\u00fb\\u00fc])+\s[0-9]{5}\s[\sa-zA-Z\\u00e0\\u00e2\\u00e4\\u00e7\\u00e8\\u00e9\\u00ea\\u00ee\\u00ef\\u00f4\\u00f6\\u00f9\\u00fb\\u00fc -]+" placeholder="123 rue de la fleur 45678 Paris"/>
												<span class="error"></span>
											</div>
										
											<div class="col-md-6">
												<form:label for="email" path="email" class="form-label" >Mail :</form:label>
												<form:input type="email" path="email" class="form-control" id="email" name="email"/>
												<span class="error"></span>
											</div>
											
											<div class="col-md-6">
												<form:label for="password" path="password" class="form-label" >Mot de Passe :</form:label>
												<form:input name="password" type="password" path="password" id="password" class="form-control" pattern=".{8,}"/>
												<span class="error"></span>
											</div>
											
											<div class="col-12" align="right">
												<input type="reset" value="Réinitialiser" class="btn btn-primary" name="Reset All" id="reset" style="margin-right: 20px"/>	
												<input type="submit" class="btn btn-primary" id="submit" value="Valider" />
											</div>
																					
										</form:form>
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

<!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script> -->
        <script src="<c:url value="/resources/js/dashboard2/js/scripts.js"/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/dashboard2/assets/demo/chart-area-demo.js"/>"></script>
        <script src="<c:url value="/resources/js/dashboard2/assets/demo/chart-bar-demo.js"/>"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/dashboard2/js/datatables-simple-demo.js"/>"></script>
<!-- Lien Jquery et js bootstrap -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/scriptFiles.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/scriptRegex.js"/>"></script>
</body>

</html>
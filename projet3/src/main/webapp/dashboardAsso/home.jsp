<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8"%> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard ${sessionScope.sessionAssociation.name}</title>
 <!-- bootstrap CSS -->
<!-- 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"> -->
		
		 <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
		<link rel="stylesheet"  href="<c:url value="/resources/css/dashboard2/css/styles.css" />">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
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
                        <li><a class="dropdown-item" href="editDocument">Paramètre</a></li>
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
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">Évènement</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                    <c:choose>
										<c:when test="${sessionScope.sessionAssociation.eventInProgress}">
											<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#closeEvent" id="btnCloseEvent" >Clotûrer l'Evenement</button>
                                        	<div class="small text-white"><i class="fas fa-angle-right"></i></div>
										</c:when>
										<c:otherwise>
											<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createEvent" id="btnCreateEvent" >Créer un nouvel Evenement</button>
											<div class="small text-white"><i class="fas fa-angle-right"></i></div>
										</c:otherwise>
									</c:choose>
							
									<!-- Modal CreateEvent -->
									<div class="modal fade" id="createEvent" tabindex="-1" aria-labelledby="eventHandlerLabel" aria-hidden="false">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header" style="color: black;">
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
												<div class="modal-header" style="color: black;">
													<h5 class="modal-title" id="exampleModalLabel" >Clotûre de l'événement</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body" style="color:black">
													Etes vous sûr de vouloir arrêter cet événement ?
												</div>
												<div class="modal-footer">
													<a class="btn btn-primary" href='<c:url value="closeEvent"/>' role="button">Oui</a>
													<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Non</button>
												</div>
											</div>
										</div>
									</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-warning text-white mb-4">
                                    <div class="card-body">Warning Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">Success Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-danger text-white mb-4">
                                    <div class="card-body">Danger Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Area Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Bar Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                DataTable Example
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>LastName</th>
                                            <th>FirstName</th>
                                            <th>Email</th>
                                            <th>Telephone</th>
                                            <th>Recontactable</th>
                                            <th>Rôle</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>LastName</th>
                                            <th>FirstName</th>
                                            <th>Email</th>
                                            <th>Telephone</th>
                                            <th>Recontactable</th>
                                            <th>Rôle</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<c:forEach var="user" items="${sessionAssociation.users}"> <!-- récupère les users de l'association dc il ne fallait pas mettre listUsers -->
											<tr>
												<td>${user.lastname}</td>
												<td>${user.firstname}</td>
												<td>${user.email}</td>
												<td>${user.telephone}</td>
												<td>
													<c:set var="val" value="${user.recontactable}"/>
													<c:choose> 
													  <c:when test="${val == '1'}">
													    Non
													  </c:when>
													  <c:otherwise>
													    Oui
													  </c:otherwise>
													</c:choose>									
												</td>
												<td>
													<c:choose> 
													  <c:when test="${user.volunteer.id != null}">
													    Bénévole
													  </c:when>
													  <c:when test="${user.participant.id != null}">
													    Participant
													  </c:when>
													  <c:when test="${user.donations.size() > 0}">
													  	Donateur
													  </c:when>
														<c:when test="${user['class'].simpleName == 'UserSociety'}">
															<c:choose>
																<c:when test="${userSociety.partner.id != null}"> <!-- A modifier bien pour présentation mais pas au point -->
																	Aucun
																</c:when>
																<c:otherwise>
																	Partenaire
																</c:otherwise>
															</c:choose>
														</c:when>
													  <c:otherwise>
													    Aucun
													  </c:otherwise>
													</c:choose>										
												</td>
											</tr>
										</c:forEach>
									</tbody>
                                </table>
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
<!--         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script> -->
        <script src="<c:url value="/resources/js/dashboard2/js/scripts.js"/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/dashboard2/assets/demo/chart-area-demo.js"/>"></script>
        <script src="<c:url value="/resources/js/dashboard2/assets/demo/chart-bar-demo.js"/>"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/dashboard2/js/datatables-simple-demo.js"/>"></script>
<!-- Lien Jquery et js bootstrap -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/scriptRegex.js"/>"></script>
</body>
</html>
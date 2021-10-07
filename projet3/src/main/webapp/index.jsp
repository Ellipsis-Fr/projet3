<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Accueil</title>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
		<!-- Bootstrap CSS -->
	    <link rel="stylesheet" href='<c:url value="/resources/css/template/bootstrap.min.css"/>'>
	
		<!-- FontAwesome CSS -->
	    <link rel="stylesheet" href='<c:url value="/resources/css/template/font-awesome.min.css"/>'>
	
		<!-- ElegantFonts CSS -->
	    <link rel="stylesheet" href='<c:url value="/resources/css/template/elegant-fonts.css"/>'>
	
		<!-- themify-icons CSS -->
	    <link rel="stylesheet" href='<c:url value="/resources/css/template/themify-icons.css"/>'>
	
		<!-- Swiper CSS -->
	    <link rel="stylesheet" href='<c:url value="/resources/css/template/swiper.min.css"/>'>
	
		<!-- Styles -->
		<link rel="stylesheet" href="<c:url value="/resources/css/template/style.css"/>">
	
		<!-- bootstrap CSS -->
		<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
		crossorigin="anonymous">
	
		<link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
	        integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
	        crossorigin="" />
	    <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.css" />

	</head>
<body id="index">

	<!-- header -->
	<c:import url="/WEB-INF/shared/header.jsp"></c:import>

	<div>
		<a class="btn btn-primary" href='<c:url value="/dashboardAsso/home"/>' role="button" hidden="hidden">Test fonctionnement filtre</a>
		<a class="btn btn-primary" href='<c:url value="/mockData"/>' role="button" hidden="hidden">création de fausses données</a>
	</div>
	<div class="home-page-welcome">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-6 order-2 order-lg-1">
                    <div class="welcome-content">
                        <header class="entry-header">
                            <h2 class="entry-title">Bienvenue sur notre site</h2>
                        </header><!-- .entry-header -->

                        <div class="entry-content mt-5">
                            <p>The Charity a à coeur d'aider le mieux possible tous type d'association en recherche d'aide pour mettre en avant leurs actions et permettre à ces associations de se faire connaître à un plus grand public. Depuis maintenant 10 ans, nous permettons à des associations diverses de créer leur site Web, afin de récolter des fonds nécessaires à l'accomplissement des buts qu'elles se sont fixées. Grâce à notre site de type générique, les associatons peuvent créer leur site très rapidement; ainsi que modifier leur page d'accueil et tout ça très facilement, d'où l'accroisssement du nombre d'associations qui utilisent notre site chaque année. Nous sommes heureux de pouvoir les aider et nous faisons tout pour répondre à leurs besoins.</p>
                        </div><!-- .entry-content -->

                        <div class="entry-footer mt-5">
                            <a href="#" class="btn gradient-bg mr-2">En savoir plus</a>
                        </div><!-- .entry-footer -->
                    </div><!-- .welcome-content -->
                </div><!-- .col -->

                <div class="col-12 col-lg-6 mt-4 order-1 order-lg-2">
                    <img src="resources/images/template/welcome.jpg" alt="welcome">
                </div><!-- .col -->
            </div><!-- .row -->
        </div><!-- .container -->
    </div><!-- .home-page-icon-boxes -->
	
	
	<c:choose>
		<c:when test="${empty requestScope.requestAssociations}">
		</c:when>
		<c:otherwise>
			<div class="our-causes">
				<div class="container">
					<div class="row">
						<div class="coL-12">
							<div class="section-heading">
								<h2 class="entry-title">Nos Causes</h2>
							</div>
							<!-- .section-heading -->
						</div>
						<!-- .col -->
					</div>
					<!-- .row -->
					<div class="row">
						<div class="col-12">
							<div class="swiper-container causes-slider">
								<div class="swiper-wrapper">

									<c:forEach items="${requestScope.requestAssociations}"
										var="association">

										<div class="swiper-slide" id="${association.address}%${association.name}%${association.id}">
											<div class="cause-wrap" style="border: 1px solid black">
												<figure class="m-0">
													<img src='<c:url value="${association.pathLogo}"/>' style="height: 221px;">

													<!-- .figure-overlay -->
												</figure>

												<div class="cause-content-wrap">
													<header
														class="entry-header d-flex flex-wrap align-items-center">
														<h3 class="entry-title w-100 m-0">
															<a href='<c:url value="event?id=${association.id}"/>'>${association.name}</a></h3>
													</header>
													<!-- .entry-header -->

<!-- 													<div class="entry-content"> -->
<%-- 														<p class="m-0">${association.description}</p> --%>
<!-- 													</div> -->
													<!-- .entry-content -->

													<div class="fund-raised w-100">
														<div class="fund-raised-bar-1 barfiller">
															<div class="tipWrap">
																<span class="tip"></span>
															</div>
															<!-- .tipWrap -->

															<span class="fill"
																data-percentage="${association.event.completionPercentage}"></span>
														</div>
														<!-- .fund-raised-bar -->

														<div
															class="fund-raised-details d-flex flex-wrap justify-content-between align-items-center">
															<div class="fund-raised-total mt-4">
																${association.event.startDate}</div>
															<!-- .fund-raised-total -->

															<div class="fund-raised-goal mt-4">
																${association.event.endDate}</div>
															<!-- .fund-raised-goal -->
														</div>
														<!-- .fund-raised-details -->
													</div>
													<!-- .fund-raised -->
												</div>
												<!-- .cause-content-wrap -->
											</div>
											<br/>
											<!-- .cause-wrap -->
										</div>
										<!-- .swiper-slide -->
									</c:forEach>

								</div>
								<!-- .swiper-wrapper -->
							</div>
							<!-- .swiper-container -->
							<!-- Add Arrows -->
							<div
								class="swiper-button-next flex justify-content-center align-items-center">
								<span><svg viewBox="0 0 1792 1792"
										xmlns="http://www.w3.org/2000/svg">
										<path
											d="M1171 960q0 13-10 23l-466 466q-10 10-23 10t-23-10l-50-50q-10-10-10-23t10-23l393-393-393-393q-10-10-10-23t10-23l50-50q10-10 23-10t23 10l466 466q10 10 10 23z" /></svg></span>
							</div>
							<div
								class="swiper-button-prev flex justify-content-center align-items-center">
								<span><svg viewBox="0 0 1792 1792"
										xmlns="http://www.w3.org/2000/svg">
										<path
											d="M1203 544q0 13-10 23l-393 393 393 393q10 10 10 23t-10 23l-50 50q-10 10-23 10t-23-10l-466-466q-10-10-10-23t10-23l466-466q10-10 23-10t23 10l50 50q10 10 10 23z" /></svg></span>
							</div>
							
							
							
													
						</div>
						<!-- .col -->
					</div>
					<!-- .row -->
				</div>
				<!-- .container -->
			</div>
			<!-- .our-causes -->
		</c:otherwise>
	</c:choose>
	
   <div class="home-page-welcome">
		<div class="container">
	       	<div class="col-12 col-lg-6 mt-4 order-1 order-lg-2" id="mapLeafset" style="float: right">
			    <div id="mapid"></div>
			</div>
			<div class="col-12 col-lg-6 order-2 order-lg-1">
			 	<div class="welcome-content">
		 			<header class="entry-header">
	                	<h2 class="entry-title">Votre position :</h2>
	                </header><!-- .entry-header -->
	                <input type="text" id="address" placeholder="Entrez votre adresse" required/>
				    <button class="btn btn-primary" id="entry" style="margin-left: 50px;">Valider</button>
	                <header class="entry-header">
	                	<h2 class="entry-title">Voici les distances entre votre position et les associations :</h2>
	                </header><!-- .entry-header -->
	                <ul id="distances" style="list-style-type: none;"></ul>
	        	</div>
	       	</div>
		</div>
	</div>

	<div class="home-page-limestone">
        <div class="container">
            <div class="row align-items-end">
                <div class="coL-12 col-lg-6">
                    <div class="section-heading">
                        <h2 class="entry-title">Nous aimons aider les associations à se faire connaître et à accomplir leurs buts. Après 10 ans, nous avons atteints de nombreux objectifs.</h2>

                        <p class="mt-5">Voici quelques chiffres qui expriment ce que nous avons accompli pendant ces 10 dernières années .</p>
                    </div><!-- .section-heading -->
                </div><!-- .col -->

                <div class="col-12 col-lg-6">
                    <div class="milestones d-flex flex-wrap justify-content-between">
                        <div class="col-12 col-sm-4 mt-5 mt-lg-0">
                            <div class="counter-box">
                                <div class="d-flex justify-content-center align-items-center">
                                    <img src="resources/images/template/teamwork.png" alt="">
                                </div>

                                <div class="d-flex justify-content-center align-items-baseline">
                                    <div class="start-counter" data-to="88" data-speed="2000"></div>
                                </div>

                                <h3 class="entry-title">Association aidées</h3><!-- entry-title -->
                            </div><!-- counter-box -->
                        </div><!-- .col -->

                        <div class="col-12 col-sm-4 mt-5 mt-lg-0">
                            <div class="counter-box">
                                <div class="d-flex justify-content-center align-items-center">
                                    <img src="resources/images/template/donate-icon.png" alt="">
                                </div>

                                <div class="d-flex justify-content-center align-items-baseline">
                                    <div class="start-counter" data-to="790" data-speed="2000"></div>
                                    <div class="counter-k">K</div>
                                </div>

                                <h3 class="entry-title">Fonds récoltés</h3><!-- entry-title -->
                            </div><!-- counter-box -->
                        </div><!-- .col -->

                        <div class="col-12 col-sm-4 mt-5 mt-lg-0">
                            <div class="counter-box">
                                <div class="d-flex justify-content-center align-items-center">
                                    <img src="resources/images/template/dove.png" alt="">
                                </div>

                                <div class="d-flex justify-content-center align-items-baseline">
                                    <div class="start-counter" data-to="8500" data-speed="2000"></div>
                                </div>

                                <h3 class="entry-title">Bénévoles</h3><!-- entry-title -->
                            </div><!-- counter-box -->
                        </div><!-- .col -->
                    </div><!-- .milestones -->
                </div><!-- .col -->
            </div><!-- .row -->
        </div><!-- .container -->
    </div><!-- .our-causes -->
	
	
		
	
	

	<!-- footer -->
	<c:import url="/WEB-INF/shared/footer.jsp"></c:import>

	<!-- Lien Jquery et js bootstrap -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/jquery.collapsible.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/swiper.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/jquery.countdown.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/circle-progress.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/jquery.countTo.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/jquery.barfiller.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="/resources/js/template/custom.js"/>'></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
		<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
    	integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
    	crossorigin=""></script>
    	<script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>
    	<script type="text/javascript" src="<c:url value="resources/js/scriptAjax.js"/>"></script>
</body>
</html>


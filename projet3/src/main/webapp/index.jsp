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
	    <link rel="stylesheet" href='<c:url value="resources/css/template/bootstrap.min.css"/>'>
	
		<!-- FontAwesome CSS -->
	    <link rel="stylesheet" href='<c:url value="resources/css/template/font-awesome.min.css"/>'>
	
		<!-- ElegantFonts CSS -->
	    <link rel="stylesheet" href='<c:url value="resources/css/template/elegant-fonts.css"/>'>
	
		<!-- themify-icons CSS -->
	    <link rel="stylesheet" href='<c:url value="resources/css/template/themify-icons.css"/>'>
	
		<!-- Swiper CSS -->
	    <link rel="stylesheet" href='<c:url value="resources/css/template/swiper.min.css"/>'>
	
		<!-- Styles -->
		<link rel="stylesheet"
		href="<c:url value="resources/css/template/style.css"/>">
	
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

	<div class="content">
		<h1>Bienvenue sur notre plateforme de collectes carritatives</h1>
	
		<hr />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
	
		<a class="btn btn-primary" href='<c:url value="/dashboardAsso/home"/>'
			role="button">Test fonctionnement filtre</a>
	
	
		<br />
		<a class="btn btn-primary" href='<c:url value="/mockData"/>'
			role="button">création de fausses données</a>
	
	
		<c:choose>
			<c:when test="${empty requestScope.requestAssociations}">
			</c:when>
			<c:otherwise>
				<div class="our-causes">
					<div class="container">
						<div class="row">
							<div class="coL-12">
								<div class="section-heading">
									<h2 class="entry-title">Our Causes</h2>
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
												<div class="cause-wrap">
													<figure class="m-0">
														<img src="resources/images/template/cause-1.jpg" alt="">
	
														<div
															class="figure-overlay d-flex justify-content-center align-items-center position-absolute w-100 h-100">
															<a href="#" class="btn gradient-bg mr-2">Donate Now</a>
														</div>
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
		
		<div class="container">
			<div class="row my-5">
				<div class="col-8" id="mapLeafset" align="center">
				    <div id="mapid"></div>
				</div>
				<div class="col-4">
					<input type="text" id="address" placeholder="Entrez votre adresse" required/>
				    <button class="btn btn-primary" id="entry">Valider</button>
				    
				    <br />
				    <ul id="distances"></ul>
				</div>
			</div>
		</div>
		
		<div id="test0">
		</div>
		
	</div>
	

	<!-- footer -->
	<c:import url="/WEB-INF/shared/footer.jsp"></c:import>

	<!-- Lien Jquery et js bootstrap -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/jquery.collapsible.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/swiper.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/jquery.countdown.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/circle-progress.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/jquery.countTo.min.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/jquery.barfiller.js"/>'></script>
	<script type='text/javascript'
		src='<c:url value="resources/js/template/custom.js"/>'></script>
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


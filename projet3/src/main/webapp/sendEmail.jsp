<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Styles -->
	<link rel="stylesheet"
		href="<c:url value="resources/css/template/style.css"/>">
	
	<!-- bootstrap CSS -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
		crossorigin="anonymous">
		
		<!-- Styles -->
	<link rel="stylesheet"
		href="<c:url value="resources/css/cssEmail/libs/style.css"/>">
		<link rel="stylesheet"
		href="<c:url value="resources/css/cssEmail/std/style.css"/>">
		<link rel="stylesheet"
		href="<c:url value="resources/css/cssEmail/fontawesome-all.css"/>">
		<link rel="stylesheet"
		href="<c:url value="resources/css/cssEmail/materialdesignicons.min.css"/>">
		<link rel="stylesheet"
		href="<c:url value="resources/css/cssEmail/select2.css"/>">
		<link rel="stylesheet"
		href="<c:url value="resources/css/cssEmail/summernote-bs4.css"/>">

</head>
<body>

	<!-- header -->
	<c:import url="/WEB-INF/shared/header.jsp"></c:import>
	
		

	<!-- ici -->

	<div class="container-fluid">
	<form method="post" action="sendEmailAPI"  enctype="multipart/form-data">
	<div class="main-content container-fluid p-0">
                    <div class="email-head">
                        <div class="email-head-title">Envoyer un nouveau message<span class="icon mdi mdi-edit"></span></div>
                    </div>
                    <span class="error">${result}</span><br />
                    <div class="email-compose-fields">
                        <div class="to">
                            <div class="form-group row pt-0">
                                <label class="col-md-1 control-label">To:</label>
                                <div class="col-md-11">
                                     <input class="form-control" type="email" name="recipient" id="email" required>
                                </div>
                            </div>
                        </div>
                      
                        <div class="subject">
                            <div class="form-group row pt-2">
                                <label class="col-md-1 control-label">Subject</label>
                                <div class="col-md-11">
                                    <input class="form-control" type="text" name="subject" id="subject" required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="email editor">
                        <div class="form-group row pt-2">
                            <div class="form-group">
                                <label class="control-label sr-only" for="messageToSend">Descriptions </label>
                                <textarea class="form-control" id="message" name="messageToSend" rows="10" placeholder="Ecrivez votre Description" required></textarea>
                            </div>
                        </div>
                        
                        <div class="form-group row pt-2">
                            <div class="form-group">
                                <label class="control-label sr-only" for="summernote">Descriptions </label>
                                <input type="file" name=attachment class="form-control" id="attachment" accept="image/*,.pdf, .doc, .docx, .xml, .html, .txt" multiple >
                            </div>
                        </div>
                        
                        
                        <div class="email action-send">
                            <div class="col-md-12 ">
                                    <button class="btn btn-primary btn-space" type="submit"><i class="icon s7-mail"></i> Envoyer</button>
                                    <button class="btn btn-secondary btn-space" type="button"><i class="icon s7-close"></i><a href='<c:out value="index"/>'>Cancel</a> </button>
                                </div>
                            </div>
                        </div>
                        
                    </div>
           </form>
           </div>
          
	<!-- fin ici -->
	
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
		
		<script type='text/javascript'
		src='<c:url value="resources/js/jsEmail/jquery.slimscroll.js"/>'></script>
		
		<script type='text/javascript'
		src='<c:url value="resources/js/jsEmail/main-js.js"/>'></script>
		
		<script type='text/javascript'
		src='<c:url value="resources/js/jsEmail/select2.js"/>'></script>
		
		<script type='text/javascript'
		src='<c:url value="resources/js/jsEmail/summernote-bs42.js"/>'></script>

</body>
</html>
// ======== ASSOCIATION REGISTRATION FORM ========
// Check if Exists
$("#rna").change((e) => {
	
	if (new RegExp(e.target.pattern).test(e.target.value)) checkDoubleAssociation(e);
	else return;
});

function checkDoubleAssociation(e) {
	
	$.ajax({
		url: "checkAssociationNotExists",
		type: "POST",
		data: {"rna": e.target.value},
		success: resultCheckDoubleAssociation,
		error: () => {
			console.log("raté");
		}
	})
}

function resultCheckDoubleAssociation(result) {
	
	if (result == "not exists") $("#formAssociationRegistration #submit").prop("disabled", false);
	else {
		let indication = "RNA reconnu. Compte Existant";
		$("#rna").next("span").text(indication);
	
		$("#formAssociationRegistration #submit").prop("disabled", true);
	}
	
	
}


// Reset Form
$("#resetRegistrationAssociation").click(() => {
    $(".error").empty();
    $("#formAssociationRegistration #submit").prop("disabled", false);
});


// ----------------------------------------------------------------------------------

// ======== CONNEXION FORM ========
$(".connexion-form").one('submit', function(e) {
	e.preventDefault();
	
	let email = $("input[name='email']").val();
	let password = $("input[name='password']").val();
	
	console.log("email" + email);
	console.log("password" + password);
	
	if (email == "" || password == "") return;
	
	if($(this).attr("action") == "connexionAssociation") {
		if (checkEmailPassword(email, password, "Association")) $(this).submit();
		else {
			$.niceToast.setup({
	            timeout: 5000,
	            progressBar: false
		        });
	        $.niceToast.error('Email et/ou Mot de passe incorrect(s)');
		}
	}
})

function checkEmailPassword(email, password, typeAccount) {
	
	console.log("email2" + email);
	console.log("password2" + password);
	console.log(typeAccount);

	$.ajax({
		url: "checkEmailPassword" + typeAccount,
		type: "POST",
		data: {
			"email": email,
			"password": password 
			},
		success: function(result) {
			console.log("result " + result)
			if (result == "not Match") return false;
			return true;
		},
		error: () => {
			console.log("raté");
		}
	})		
}

//$(".connexion-form").on('submit', function(e) {
//	e.preventDefault();
//	
//	let email = $("input[name='email']").val();
//	let password = $("input[name='password']").val();
//	
//	console.log("email" + email);
//	console.log("password" + password);
//	
//	if (email == "" || password == "") return;
//	
//	if($(this).attr("action") == "connexionAssociation") {
//		new Promise(function(resolve, reject) {
//			
//		})
//		
//		
//		
//		
//		Promise.all(checkEmailPassword(email, password, "Association"))
//		.then(function(result) {
//			if (result) $(this).submit();
//			else {
//				$.niceToast.setup({
//		            timeout: 5000,
//		            progressBar: false
//			        });
//		        $.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//			}
//		}) 
//	}
//})
//
//function checkEmailPassword(email, password, typeAccount) {
//	
//	console.log("email2" + email);
//	console.log("password2" + password);
//	console.log(typeAccount);
//
//	$.ajax({
//		url: "checkEmailPassword" + typeAccount,
//		type: "POST",
//		data: {
//			"email": email,
//			"password": password 
//			},
//		success: function(result) {
//			console.log("result " + result)
//			if (result == "not Match") return false;
//			return true;
//		},
//		error: () => {
//			console.log("raté");
//		}
//	})		
//}


//$(".connexion-form").on('submit', function(e) {
//	e.preventDefault();
//	
//	let email = $("input[name='email']").val();
//	let password = $("input[name='password']").val();
//	let typeAccount = "";
//	
//	console.log("email" + email);
//	console.log("password" + password);
//	
//	if (email == "" || password == "") return;
//	
//	if($(this).attr("action") == "connexionAssociation") typeAccount = "Association";
//	if($(this).attr("action") == "connexionVolunteer") typeAccount = "Volunteer";
//	if($(this).attr("action") == "connexionPartner") typeAccount = "Partner";
//	
//	$.when(checkEmailPassword(email, password, typeAccount))
//	.done(function(result) {
//		if (result) $(this).submit();
//		else {
//			$.niceToast.setup({
//	            timeout: 5000,
//	            progressBar: false
//		        });
//	        $.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//		}
//	});
//
//})
//
//function checkEmailPassword(email, password, typeAccount) {
//	
//	console.log("email2" + email);
//	console.log("password2" + password);
//	console.log(typeAccount);
//
//	$.ajax({
//		url: "checkEmailPassword" + typeAccount,
//		type: "POST",
//		data: {
//			"email": email,
//			"password": password 
//			},
//		success: function(result) {
//			console.log("result " + result)
//			if (result == "not Match") return false;
//			return true;
//		},
//		error: () => {
//			console.log("raté");
//		}
//	})		
//}

// ======== DONATION FORM ========

$("form[name='formDonation']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newDonation");
});


// ======== GENERIC METHOD TO FORM : DONATION AND REGISTRATION  ========

function sendForm(formData, typeForm) {
	console.log("methode sendForm" + typeForm);
	
	$.ajax({
		url: typeForm,
		type: "POST",
		data: formData,
		processData: false,  // indique à jQuery de ne pas traiter les données
  		contentType: false,   // indique à jQuery de ne pas configurer le contentType
		success: returnForm,
		error: () => {
			console.log("raté");
		}
	})
	
		
}

function returnForm(result) {	
	$('.collapse').collapse('hide');
	
	for (let i = 0; i < document.forms.length; i++) {
		document.forms[i].reset();
	}
	
	let resultNumber = "";
	
	try {
		resultNumber = parseInt(result)
	} catch (error) {
		console.log(error)
	}
	
	if (!isNaN(resultNumber)) {
		console.log(resultNumber)
		$("#sumDonations").html(resultNumber);

	}
	
	$.niceToast.setup({
	            timeout: 5000,
	            progressBar: false
		        });
    $.niceToast.success('Don enregistré. Merci pour Votre générosité !');
}

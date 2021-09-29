// ==== apparition formulaire sans connexion ====

$("a").click(apparitionForm);

function apparitionForm(e) {
	/* TODO
	 * gérer les formulaires d'inscription aux activités en tant que participant
	 * généraliser la remise à zéro des formulaires de connexions pour les autres types de comptes
	 * supprimer le contenu des remarques de non respect des patterns
	*/
	
	
	initForms();
	$('.collapse').collapse('hide');
	
	if ($(this).attr("id") == "btnVolunteer") {
//		$("#formVolunteer").toggle();
		$("#formVolunteerToLogin").hide();
		
//		$("#formDonation").hide();
//		$("#formPartner").hide();

//		var bsCollapse = new bootstrap.Collapse($("#formDonation"), {
//		  hide: true
//		})
//		bsCollapse.hide

		
		$("#loginVolunteer").prop("disabled", false);
		$("#formVolunteerRegistration input").prop("disabled", false);
	}
	else if ($(this).attr("id") == "btnDonation") {
//		$("#formDonation").toggle();
		
//		$("#formVolunteer").hide();
//		$("#formPartner").hide();

		$("#paymentCb").hide();
		$("#paymentCb input").prop("disabled", true);
		
		$("#formDonation input").prop("disabled", false);
		$("#fieldsSociety input").prop("disabled", true);
	}
	else if ($(this).attr("id") == "btnPartner") {
		console.log("click sur btnPartner")
//		$("#formPartner").toggle();
		$("#formPartnerToLogin").hide();
		
//		$("#formVolunteer").hide();
//		$("#formDonation").hide();
		
		$("#loginPartner").prop("disabled", false);
		$("#formPartnerRegistration input").prop("disabled", false);
	}
}

function initForms() {
	console.log("je suis dans init forms")
	
	$("#loginVolunteer").prop("checked", false);
	$("#formVolunteerRegistration").show();
	
	$("#loginPartner").prop("checked", false);
	$("#formPartnerRegistration").show();
	
	$("#userType").prop("checked", false);
	$("#fieldsSociety").hide();
	$("#fieldsSociety input").prop("disabled", true);
	$("#paymentCb").hide();
	$("#paymentCb input").prop("disabled", true);
	$(".formDonation input[type='radio']").prop("checked", false);
	
	$("#formVolunteer .error").text("");
	$("#formDonation .error").text("");
	$("#formPartner .error").text("");
	
	$("#formz input").prop("disabled", true);
	
	for (let i = 0; i < document.forms.length; i++) {
		document.forms[i].reset();
	}
}



// ==== apparition formulaire connexion ====

$(".switch").change((e) => {

	/* TODO
	 * A généraliser pour les autres types de compte (utiliser une classe pour le chexbox et l'id pour définir le type de compte)
	*/

	if (e.target.id == "loginVolunteer") {
		if (e.target.checked) {
			$("#formVolunteerRegistration").hide();
			$("#formVolunteerToLogin").show();
			
			$("#formVolunteerRegistration input").prop("disabled", true);
			$("#formVolunteerToLogin input").prop("disabled", false);
		}
		else {
			$("#formVolunteerToLogin").hide();
			$("#formVolunteerRegistration").show();
			
			$("#formVolunteerRegistration input").prop("disabled", false);
			$("#formVolunteerToLogin input").prop("disabled", true);
		}
	} else if (e.target.id == "loginPartner") {
		if (e.target.checked) {
			$("#formPartnerRegistration").hide();
			$("#formPartnerToLogin").show();
			
			$("#formPartnerRegistration input").prop("disabled", true);
			$("#formPartnerToLogin input").prop("disabled", false);
		}
		else {
			$("#formPartnerToLogin").hide();
			$("#formPartnerRegistration").show();
			
			$("#formPartnerRegistration input").prop("disabled", false);
			$("#formPartnerToLogin input").prop("disabled", true);
		}
	} else if (e.target.id == "userType") {
		if (e.target.checked) {
			$("#fieldsSociety").show();
			$("#fieldsSociety input").prop("disabled", false);
		}
		else {
			$("#fieldsSociety").hide();
			$("#fieldsSociety input").prop("disabled", true);
		}
	}
});


// ==== apparition formulaire de paiement ====

$("#formDonation input[type='radio']").change(e => {
	console.log("là")
	if (e.target.id == "paymentMethodCb") {
		$("#paymentCb").show();
		$("#paymentCb input").prop("disabled", false);
	} else {
		$("#paymentCb").hide();
		$("#paymentCb input").prop("disabled", true);
	}
})




// ==== Validation Connexion ====

$(document).ready(start);

function start() {
	console.log($("#event").length);
	console.log($("#event").length == 0);
	if ($("#event").length == 0) return;
	
	let connexion = $("#stateConnexionUser").text();
	console.log(connexion);

	if (connexion.length > 1) {	
	    var toast = new bootstrap.Toast($('#liveToast'));
	    toast.show();
	}
		
}



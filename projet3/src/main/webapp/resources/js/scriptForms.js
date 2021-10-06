// ==== apparition formulaire sans connexion ====

$(".event-page-icon-boxes a").click(apparitionForm);


function apparitionForm(e) {
	let x = e.currentTarget;
	
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
	else if (x.className == "btnParticipant") {
		console.log("là")
		$("#formParticipantToLogin").hide();

		$("#loginParticipant").prop("disabled", false);
		$("#formParticipantRegistration input").prop("disabled", false);
	}
	else if ($(this).attr("id") == "btnDonation") {
//		$("#formDonation").toggle();
		
//		$("#formVolunteer").hide();
//		$("#formPartner").hide();

		$("#paymentCb").hide();
		$("#paymentCb input").prop("disabled", true);
		
		$("#formDonation input").prop("disabled", false);
//		$("#fieldsSociety input").prop("disabled", true);

		$("form[name='formDonation'] input[name='companyName']").prop("disabled", true);
		$("form[name='formDonation'] input[name='siret']").prop("disabled", true);
		
		if ($("#userLogged").text().length != 0) {
			console.log("je suis connecté")
			if($("#roleUserLogged").text() == 'partner') {
				$("#userType").prop("checked", true);
				
				$("form[name='formDonation'] input[name='companyName']").prop("disabled", false);
				$("form[name='formDonation'] input[name='companyName']").prop("readonly", true);
				$("form[name='formDonation'] input[name='siret']").prop("disabled", false);
				$("form[name='formDonation'] input[name='siret']").prop("readonly", true);
				$("form[name='formDonation'] .fieldsSociety").hide();
				
//				$("#fieldsSociety").show();
//				$("#fieldsSociety input").prop("disabled", false);
//				$("#fieldsSociety input").prop("readonly", true);
			}
			
			$("form[name='formDonation'] input[name='lastname']").prop("readonly", true);
			$("form[name='formDonation'] input[name='firstname']").prop("readonly", true);
			$("form[name='formDonation'] input[name='address']").prop("readonly", true);
			$("form[name='formDonation'] input[name='telephone']").prop("readonly", true);
			$("form[name='formDonation'] input[name='email']").prop("readonly", true);
			$("form[name='formDonation'] .fieldsUsers").hide();

			$("#userType").prop("readonly", true);
			$("#userType").hide();
			$("label[for='userType']").hide();
			
			$("#contact2").prop("readonly", true);
			$("#contact2").hide();
			$("label[for='contact2']").hide();
		}

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
	else if ($(this).attr("id") == "btnActivity") {

		$("#formActivityRegistration input").prop("disabled", false);
	}
}

function initForms() {	
	$("#loginVolunteer").prop("checked", false);
	$("#formVolunteerRegistration").show();
	
	$("#loginParticipant").prop("checked", false);
	$("#formParticipantRegistration").show();
	
	$("#loginPartner").prop("checked", false);
	$("#formPartnerRegistration").show();
	
	$("#userType").prop("checked", false);
	
	
//	$("#fieldsSociety").hide();
//	$("#fieldsSociety input").prop("disabled", true);
	
	$("form[name='formDonation'] .fieldsSociety").hide()
	$("form[name='formDonation'] input[name='companyName']").prop("disabled", true);
	$("form[name='formDonation'] input[name='siret']").prop("disabled", true);
	
	$("#paymentCb").hide();
	$("#paymentCb input").prop("disabled", true);
	$(".formDonation input[type='radio']").prop("checked", false);
	
	$("#formVolunteer .error").text("");
	$("#formParticipant .error").text("");
	$("#formDonation .error").text("");
	$("#formPartner .error").text("");
	$("#formActivity .error").text("");	
	
	$("textarea").next("span").text("0 / " + $("textarea").attr("maxlength"));
	$("textarea").next("span").css("color", "black");
	
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
	} else if (e.target.id == "loginParticipant") {
		if (e.target.checked) {
			$("#formParticipantRegistration").hide();
			$("#formParticipantToLogin").show();
			
			$("#formParticipantRegistration input").prop("disabled", true);
			$("#formParticipantToLogin input").prop("disabled", false);
		}
		else {
			$("#formParticipantToLogin").hide();
			$("#formParticipantRegistration").show();
			
			$("#formParticipantRegistration input").prop("disabled", false);
			$("#formParticipantToLogin input").prop("disabled", true);
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
//			$("#fieldsSociety").show();
//			$("#fieldsSociety input").prop("disabled", false);

			$("form[name='formDonation'] .fieldsSociety").show()
			$("form[name='formDonation'] input[name='companyName']").prop("disabled", false);
			$("form[name='formDonation'] input[name='siret']").prop("disabled", false);
		}
		else {
//			$("#fieldsSociety").hide();
//			$("#fieldsSociety input").prop("disabled", true);
			
			$("form[name='formDonation'] .fieldsSociety").hide()
			$("form[name='formDonation'] input[name='companyName']").prop("disabled", true);
			$("form[name='formDonation'] input[name='siret']").prop("disabled", true);
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




// ==== Validation Connexion In Event ====

$(document).ready(start);

function start() {
	console.log($("#roleUserLogged").text());
	console.log($("#userLogged").text());
	if ($("#event").length == 0) return;

	let connexion = $("#stateConnexionUser").text();
	console.log(connexion);

	if (connexion.length > 1) {	
	    var toast = new bootstrap.Toast($('#liveToast'));
	    toast.show();
	}
		
}

// ===== Count Char ====
$("textarea").on("input", countChar);

function countChar(e) {
	let maxLength = $(this).attr("maxlength")
	let count = e.target.value.length;
	$(this).next("span").text(count + " / " + maxLength);
	
	let isOverLimit = count > maxLength ? true : false;
	
	if (isOverLimit) $(this).next("span").css("color", "red");
	else  $(this).next("span").css("color", "black");	
}


// ===== Add photo ====

var inputFile = 3;

$("#addPhoto").click(addPhoto);

function addPhoto(e) {
	e.preventDefault();
	if(inputFile === 4) $(this).prop("disabled", true);
	console.log($(this).parent())
	$(this).parent().before($("<div class='col-md-12 my-3'><label for='photo" + 
	inputFile + 
	"' > Photo Caroussel : </label><input type='file' name='files' id='photo" + 
	 inputFile + 
	 "' accept='.jpg,.jpeg,.png'/><span class='error'></span></div>"));
	 
	inputFile++;
}

// ===== Modal to Send Message =====

let newMessageModal = document.getElementById('newMessage')
newMessageModal.addEventListener('show.bs.modal', function (event) {
	// Button that triggered the modal
	let button = event.relatedTarget
	// Extract info from data-bs-* attributes
	let recipient = button.getAttribute('data-bs-whatever')
	
	let modalTitle = newMessageModal.querySelector('.modal-title')
	let modalSubject = newMessageModal.querySelector(".modal-body #subject")
	let modalRecipientInput = newMessageModal.querySelector('.modal-body #recipient')
	
//	if (recipient == "new") {
//		modalRecipientInput.setAttribute("type", "email");
//		modalRecipientInput.removeAttribute("readonly");
//		modalRecipientInput.value = "";
//		return;
//	}
//	
//	modalRecipientInput.setAttribute("type", "text");
//	modalRecipientInput.setAttribute("readonly", true);
	
	
	switch (recipient) {
		case "new":
			modalRecipientInput.setAttribute("type", "email");
			modalRecipientInput.removeAttribute("readonly");
			
			modalTitle.textContent = "Nouveau Message";
			modalRecipientInput.value = "";
			break;
		case "Donnateurs":
		case "Bénévoles":
		case "Participants":
		case "Partenaires":
		case "Utilisateurs":
			modalRecipientInput.setAttribute("type", "text");
			modalRecipientInput.setAttribute("readonly", true);
			
			modalTitle.textContent = 'Nouveau Message à tous les ' + recipient
			modalRecipientInput.value = recipient
			break;
		default:
			modalRecipientInput.setAttribute("type", "email");
			modalRecipientInput.removeAttribute("readonly");
			
			modalTitle.textContent = "Nouveau Message";
			modalRecipientInput.value = recipient;	
	}
	
		
//	modalTitle.textContent = 'Nouveau Message à tous les ' + recipient
//	modalRecipientInput.value = recipient
})

function resetMail(event) {
	let newMessageModal = document.getElementById('newMessage')
	
	let modalTitle = newMessageModal.querySelector('.modal-title')
	let modalForm = newMessageModal.querySelector('form[name="sendMessage"]')
	
	modalTitle.textContent = "Nouveau Message";
	modalForm.reset();
	
}



//========== SWITCH MAILBOX AND OUTBOX =======

function switchBox(e) {
	if (e.target.id == "btnMailBox" && $("#mailBox").is(":hidden")) {
		$("#mailBox").toggle();
		$("#outBox").toggle();
	} else if (e.target.id == "btnOutBox" && $("#outBox").is(":hidden")) {
		$("#outBox").toggle();
		$("#mailBox").toggle();
	}
}

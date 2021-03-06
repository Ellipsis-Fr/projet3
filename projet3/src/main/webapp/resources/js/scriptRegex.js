$("button").click(selectForm);
$(".event-page-icon-boxes a").click(selectForm);
$("#btnParticipant").click(selectForm);

function selectForm(e) {
	console.log(e.currentTarget);
	console.log(e.currentTarget.getAttribute("aria-controls"))
	if ($(this).attr("id") == "btnVolunteer") inputsToCheck("#formVolunteerRegistration");
	else if ($(this).attr("id") == "btnDonation") inputsToCheck("#formDonation"); 
	else if ($(this).attr("id") == "btnPartner") inputsToCheck("#formPartnerRegistration"); 
	else if (e.currentTarget.getAttribute("aria-controls") == "formParticipant") inputsToCheck("#formParticipantRegistration");
	else if ($(this).attr("id") == "btnAssociationRegistration") inputsToCheck("#formAssociationRegistration");
}

function inputsToCheck(formToCheck) {
	
	if (formToCheck.indexOf("#") != 0) formToCheck = "#" + formToCheck;
	
	let inputToCheck = Array.from($(formToCheck + " input")).filter(e => e.pattern);
	
	for (input of inputToCheck) {
			
	    $(this).change((e) => {
			
			if (e.target.value.length === 0) return;
			
	        let indication;
	
			if (e.target.id == "lastname" || e.target.id == "firstname") indication = "Ce champ ne peut contenir que des lettres - min : 3 - max  15";
			else if (e.target.id == "address") indication = "Ce champs doit correspondre au modèle suivant : 123 (bis/ter) rue (ou avenue, boulevard) nom rue 01000 ville";
			else if (e.target.id == "telephone") indication = "Ce champs doit correspondre au modèle suivant : 0111111111";
			else if (e.target.id == "email") indication = "Adresse mail non valide";
			else if (e.target.id == "siret") indication = "Le SIRET doit être composé de 14 chiffres";
			else if (e.target.id == "password") indication = "Le Mot de Passe doit contenir au moins 8 caractères"
			else if (e.target.id == "rna") {
				$(formToCheck + " #" + e.target.id).next("span").text("");
				indication = "Le RNA doit comporter un W et 9 chiffres"
			} else return;
	
			let warningExists = $(formToCheck + " #" + e.target.id).next("span").text().length > 1 ? true : false;
			
	        let regex = new RegExp(e.target.pattern);
	        if (!regex.test(e.target.value)) {
	            if (warningExists) return;
				$(formToCheck + " #" + e.target.id).next("span").text(indication);
	
	        } else {
	            if (!warningExists) return;
	            $(formToCheck + " #" + e.target.id).next("span").text("");
	        }
	    });
	}
}



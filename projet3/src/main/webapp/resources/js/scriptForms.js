// ==== apparition formulaire sans connexion ====

$("button").click(apparitionForm);

function apparitionForm(e) {
	/* TODO
	 * gérer les formulaires d'inscription aux activités en tant que participant
	 * généraliser la remise à zéro des formulaires de connexions pour les autres types de comptes
	 * supprimer le contenu des remarques de non respect des patterns
	*/
	
	initForms();
	
	if ($(this).attr("id") == "btnBenevole") {
		$("#formBenevole").toggle();
		$("#formBenevoleConnexion").hide();
		
		$("#formDon").hide();
		$("#formPartenaire").hide();
		
		$("#seConnecterBenevole").prop("disabled", false);
		$("#formBenevoleInscription input").prop("disabled", false);
	}
	else if ($(this).attr("id") == "btnDon") {
		$("#formDon").toggle();
		
		$("#formBenevole").hide();
		$("#formPartenaire").hide();
		
		$("#formDon input").prop("disabled", false);
		$("#champsSociete input").prop("disabled", true);
	}
	else if ($(this).attr("id") == "btnPartenaire") {
		$("#formPartenaire").toggle();
		$("#formPartenaireConnexion").hide();
		
		$("#formBenevole").hide();
		$("#formDon").hide();
		
		$("#seConnecterPartenaire").prop("disabled", false);
		$("#formPartenaireInscription input").prop("disabled", false);
	}
}

function initForms() {
	$("#seConnecterBenevole").prop("checked", false);
	$("#formBenevoleInscription").show();
	
	$("#seConnecterPartenaire").prop("checked", false);
	$("#formPartenaireInscription").show();
	
	$("#typeContact").prop("checked", false);
	$("#champsSociete").hide();
	$("#champsSociete input").prop("disabled", true);
	
	$("#formBenevole .erreur").text("");
	$("#formDon .erreur").text("");
	$("#formPartenaire .erreur").text("");
	
	$("#formulaires input").prop("disabled", true);
	
	for (let i = 0; i < document.forms.length; i++) {
		document.forms[i].reset();
	}
}



// ==== apparition formulaire connexion ====

$(".switch").change((e) => {

	/* TODO
	 * A généraliser pour les autres types de compte (utiliser une classe pour le chexbox et l'id pour définir le type de compte)
	*/

	if (e.target.id == "seConnecterBenevole") {
		if (e.target.checked) {
			$("#formBenevoleInscription").hide();
			$("#formBenevoleConnexion").show();
			
			$("#formBenevoleInscription input").prop("disabled", true);
			$("#formBenevoleConnexion input").prop("disabled", false);
		}
		else {
			$("#formBenevoleConnexion").hide();
			$("#formBenevoleInscription").show();
			
			$("#formBenevoleInscription input").prop("disabled", false);
			$("#formBenevoleConnexion input").prop("disabled", true);
		}
	} else if (e.target.id == "seConnecterPartenaire") {
		if (e.target.checked) {
			$("#formPartenaireInscription").hide();
			$("#formPartenaireConnexion").show();
			
			$("#formPartenaireInscription input").prop("disabled", true);
			$("#formPartenaireConnexion input").prop("disabled", false);
		}
		else {
			$("#formPartenaireConnexion").hide();
			$("#formPartenaireInscription").show();
			
			$("#formPartenaireInscription input").prop("disabled", false);
			$("#formPartenaireConnexion input").prop("disabled", true);
		}
	} else if (e.target.id == "typeContact") {
		if (e.target.checked) {
			$("#champsSociete").show();
			$("#champsSociete input").prop("disabled", false);
		}
		else {
			$("#champsSociete").hide();
			$("#champsSociete input").prop("disabled", true);
		}
	}
});




















//$("input[type='radio']").change((e) => {
//	if (e.target.value == "non") {
//		$("#clientForm").hide();
//		$("#clientForm input").prop("disabled", true);
//		$("#clientSelect").show();
//		$("#clientSelect select").prop("disabled", false);
//	} else {
//		$("#clientSelect").hide();
//		$("#clientSelect select").prop("disabled", true);
//		$("#clientForm").show();
//		$("#clientForm input").prop("disabled", false);
//	}
//})

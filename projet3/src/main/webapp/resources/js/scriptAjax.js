
//Inscription Association
$("#rna").change((e) => {
	
	if (new RegExp(e.target.pattern).test(e.target.value)) checkDoubleAssociation(e);
	else return;
});

function checkDoubleAssociation(e) {
	console.log("lancement d'ajax");
	console.log(e.target.value);
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

$("#reset").click(() => {
    $(".erreur").empty();
    $("#formAssociationRegistration #submit").prop("disabled", false);
});
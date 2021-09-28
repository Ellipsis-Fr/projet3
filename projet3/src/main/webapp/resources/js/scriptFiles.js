$("input[type='file']").change(checkFile);

function checkFile(e) {
	let warningExists = $("#errorFile").text().length > 1 ? true : false;

	if ($(this)[0].files[0].size > 100000) {
		$("#submit").prop("disabled", true);
		if (warningExists) return;
		$("#errorFile").text("La taille du fichier sélectionné ne doit pas dépasser 100 Ko");
	
	} else {
		$("#submit").prop("disabled", false);
		if (!warningExists) return;
		$("#errorFile").text("");	
	}
}



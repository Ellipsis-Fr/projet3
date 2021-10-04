$("input[type='file']").change(checkFile);

function checkFile(e) {
	let sizeMax = 3000000;
	
	if ($(this).attr("name") == "logo") sizeMax = 1000000;
	
	let warningExists = $("#errorFile").text().length > 1 ? true : false;

	if ($(this)[0].files[0].size > sizeMax) {
		$("#submit").prop("disabled", true);
		if (warningExists) return;
		$("#errorFile").text("La taille du fichier sélectionné ne doit pas dépasser " + sizeMax/1000000 + " Mo");
	
	} else {
		$("#submit").prop("disabled", false);
		if (!warningExists) return;
		$("#errorFile").text("");	
	}
}



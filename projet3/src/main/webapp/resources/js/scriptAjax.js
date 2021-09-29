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
//$(".connexion-form").one('submit', function(e) {
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
//		if (checkEmailPassword(email, password, "Association")) $(this).submit();
//		else {
//			$.niceToast.setup({
//	            timeout: 5000,
//	            progressBar: false
//		        });
//	        $.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//		}
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


$("form[name='formAssociationToLogin']").one('submit', function(e) {
	
	e.preventDefault();
	
	let formToCheck = "";
	
	if ($(this).attr('name') == 'formAssociationToLogin') formToCheck = "form[name='formAssociationToLogin']";
	if ($(this).attr('name') == 'formVolunteerToLogin') formToCheck = "form[name='formVolunteerToLogin']";
	if ($(this).attr('name') == 'formPartnerToLogin') formToCheck = "form[name='formPartnerToLogin']";
	if ($(this).attr('name') == 'formParticipantToLogin') formToCheck = "form[name='formParticipantToLogin']";
	
	console.log(formToCheck);
	
	let email = $(formToCheck + " input[name='email']").val();
	let password = $(formToCheck + " input[name='password']").val();
	let role = $(formToCheck + " input[name='role']").val();
	
	console.log("email " + email);
	console.log("password " + password);
	console.log("role " + role);
	console.log($(this).attr('name'));

	if (email == "" || password == "") return;
	
	if($(this).attr("action") == "connexionAssociation") {
		$.ajax({
			url: "checkEmailPasswordAssociation",
			type: "POST",
			data: {
				"email": email,
				"password": password 
				},
			success: function(result) {
				console.log("result " + result)
				if (result == "not Match") {
					$.niceToast.setup({
			            timeout: 5000,
			            progressBar: false
				        });
		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
				}
				else $(this).submit();
			},
			error: () => {
				console.log("raté");
			}
		})	
	} else {
		$.ajax({
			url: "checkEmailPassword",
			type: "POST",
			data: {
				"email": email,
				"password": password,
				 "role": role
				},
			success: function(result) {
				console.log("result " + result)
				if (result == "not Match") {
					$.niceToast.setup({
			            timeout: 5000,
			            progressBar: false
				        });
		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
				}
				else $(this).submit();
			},
			error: () => {
				console.log("raté");
			}
		})	
	}
	
});


//
//$("form[name='formVolunteerToLogin']").one('submit', function(e) {
//	
//	e.preventDefault();
//	
//	let formToCheck = "";
//	
//	if ($(this).attr('name') == 'formAssociationToLogin') formToCheck = "form[name='formAssociationToLogin']";
//	if ($(this).attr('name') == 'formVolunteerToLogin') formToCheck = "form[name='formVolunteerToLogin']";
//	if ($(this).attr('name') == 'formPartnerToLogin') formToCheck = "form[name='formPartnerToLogin']";
//	if ($(this).attr('name') == 'formParticipantToLogin') formToCheck = "form[name='formParticipantToLogin']";
//	
//	console.log(formToCheck);
//	
//	let email = $(formToCheck + " input[name='email']").val();
//	let password = $(formToCheck + " input[name='password']").val();
//	let role = $(formToCheck + " input[name='role']").val();
//	
//	console.log("email " + email);
//	console.log("password " + password);
//	console.log("role " + role);
//	console.log($(this).attr('name'));
//
//	if (email == "" || password == "") return;
//	
//	if($(this).attr("action") == "connexionAssociation") {
//		$.ajax({
//			url: "checkEmailPasswordAssociation",
//			type: "POST",
//			data: {
//				"email": email,
//				"password": password 
//				},
//			success: function(result) {
//				console.log("result " + result)
//				if (result == "not Match") {
//					$.niceToast.setup({
//			            timeout: 5000,
//			            progressBar: false
//				        });
//		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//				}
//				else $(this).submit();
//			},
//			error: () => {
//				console.log("raté");
//			}
//		})	
//	} else {
//		$.ajax({
//			url: "checkEmailPassword",
//			type: "POST",
//			data: {
//				"email": email,
//				"password": password,
//				 "role": role
//				},
//			success: function(result) {
//				console.log("result " + result)
//				if (result == "not Match") {
//					$.niceToast.setup({
//			            timeout: 5000,
//			            progressBar: false
//				        });
//		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//				}
//				else $(this).submit();
//			},
//			error: () => {
//				console.log("raté");
//			}
//		})	
//	}
//});

//$("form[name='formPartnerToLogin']").one('submit', function(e) {
//	e.preventDefault();
//	
//	let formToCheck = "";
//	
//	if ($(this).attr('name') == 'formAssociationToLogin') formToCheck = "form[name='formAssociationToLogin']";
//	if ($(this).attr('name') == 'formVolunteerToLogin') formToCheck = "form[name='formVolunteerToLogin']";
//	if ($(this).attr('name') == 'formPartnerToLogin') formToCheck = "form[name='formPartnerToLogin']";
//	if ($(this).attr('name') == 'formParticipantToLogin') formToCheck = "form[name='formParticipantToLogin']";
//	
//	console.log(formToCheck);
//	
//	let email = $(formToCheck + " input[name='email']").val();
//	let password = $(formToCheck + " input[name='password']").val();
//	let role = $(formToCheck + " input[name='role']").val();
//	
//	console.log("email " + email);
//	console.log("password " + password);
//	console.log("role " + role);
//	console.log($(this).attr('name'));
//
//	if (email == "" || password == "") return;
//	
//	if($(this).attr("action") == "connexionAssociation") {
//		$.ajax({
//			url: "checkEmailPasswordAssociation",
//			type: "POST",
//			data: {
//				"email": email,
//				"password": password 
//				},
//			success: function(result) {
//				console.log("result " + result)
//				if (result == "not Match") {
//					$.niceToast.setup({
//			            timeout: 5000,
//			            progressBar: false
//				        });
//		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//				}
//				else $(this).submit();
//			},
//			error: () => {
//				console.log("raté");
//			}
//		})	
//	} else {
//		$.ajax({
//			url: "checkEmailPassword",
//			type: "POST",
//			data: {
//				"email": email,
//				"password": password,
//				 "role": role
//				},
//			success: function(result) {
//				console.log("result " + result)
//				if (result == "not Match") {
//					$.niceToast.setup({
//			            timeout: 5000,
//			            progressBar: false
//				        });
//		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//				}
//				else $(this).submit();
//			},
//			error: () => {
//				console.log("raté");
//			}
//		})	
//	}
//});
//
//$("form[name='formParticipantToLogin']").one('submit', function(e) {
//	e.preventDefault();
//	
//	let formToCheck = "";
//	
//	if ($(this).attr('name') == 'formAssociationToLogin') formToCheck = "form[name='formAssociationToLogin']";
//	if ($(this).attr('name') == 'formVolunteerToLogin') formToCheck = "form[name='formVolunteerToLogin']";
//	if ($(this).attr('name') == 'formPartnerToLogin') formToCheck = "form[name='formPartnerToLogin']";
//	if ($(this).attr('name') == 'formParticipantToLogin') formToCheck = "form[name='formParticipantToLogin']";
//	
//	console.log(formToCheck);
//	
//	let email = $(formToCheck + " input[name='email']").val();
//	let password = $(formToCheck + " input[name='password']").val();
//	let role = $(formToCheck + " input[name='role']").val();
//	
//	console.log("email " + email);
//	console.log("password " + password);
//	console.log("role " + role);
//	console.log($(this).attr('name'));
//
//	if (email == "" || password == "") return;
//	
//	if($(this).attr("action") == "connexionAssociation") {
//		$.ajax({
//			url: "checkEmailPasswordAssociation",
//			type: "POST",
//			data: {
//				"email": email,
//				"password": password 
//				},
//			success: function(result) {
//				console.log("result " + result)
//				if (result == "not Match") {
//					$.niceToast.setup({
//			            timeout: 5000,
//			            progressBar: false
//				        });
//		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//				}
//				else $(this).submit();
//			},
//			error: () => {
//				console.log("raté");
//			}
//		})	
//	} else {
//		$.ajax({
//			url: "checkEmailPassword",
//			type: "POST",
//			data: {
//				"email": email,
//				"password": password,
//				 "role": role
//				},
//			success: function(result) {
//				console.log("result " + result)
//				if (result == "not Match") {
//					$.niceToast.setup({
//			            timeout: 5000,
//			            progressBar: false
//				        });
//		        	$.niceToast.error('Email et/ou Mot de passe incorrect(s)');
//				}
//				else $(this).submit();
//			},
//			error: () => {
//				console.log("raté");
//			}
//		})	
//	}
//});
//




// ======== DONATION FORM ========

$("form[name='formDonation']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newDonation");
});

// ======== VOLUNTEER FORM ========

$("form[name='formVolunteerRegistration']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newVolunteer");
});


// ======== PARTNER FORM ========

$("form[name='formPartnerRegistration']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newPartner");
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
	let success = false;
	let text = "";

	let resultNumber = "";
	
	try {
		resultNumber = parseInt(result)
	} catch (error) {
	}
	
	if (!isNaN(resultNumber)) {
		success = true;
		$("#sumDonations").html(resultNumber);
		text = "Don enregistré. Merci pour Votre générosité !";
	}
	
	if (result == "Inscription Bénévole confirmé") {
		success = true;
		text = result + ", Merci pour votre aide.";
	}
	
	if (result == "Inscription Participant confirmé") {
		success = true;
		text = result + ".";
	}
	
	if (result == "Inscription Partenaire confirmé") {
		success = true;
		text = result + ", Merci pour votre aide.";
	}
	
	
	if (success) {
		$.niceToast.setup({
	            timeout: 5000,
	            progressBar: false
		        });
	    $.niceToast.success(text);
	    
	    
	    $('.collapse').collapse('hide');
		
		for (let i = 0; i < document.forms.length; i++) {
			document.forms[i].reset();
		}
	} else {
		$.niceToast.setup({
	            timeout: 5000,
	            progressBar: false
		        });
	    $.niceToast.error(result);
	}
}

// ======== MAP ASSOCIATIONS ========
var map;
var markers = [];

$(document).ready(start);

function start() {
	if ($("#index").length == 0) return;
	initMapLeafset(46.50, 2.20); //lat et lng pour centrer sur la france
	
	$("#entry").click(localisation);
}

function initMapLeafset(latitude, longitude) {
    map = L.map('mapid').setView([latitude, longitude], 5);

    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 15,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(map);

	//Ajoute la possibilité de sélectionner une zone et obtenir des informations sur celle ci
    var geocoder = L.Control.Geocoder.nominatim();
	
	let marker;
    map.on('click', function (e) {
        geocoder.reverse(e.latlng, map.options.crs.scale(map.getZoom()), function (results) {
            var r = results[0];
            if (r) {
                marker = L.marker(r.center)
                    .bindPopup(r.name)
                    .addTo(map)
                    .openPopup();
				console.log(marker);
                markers.push(marker);
                if (markers.length > 1) clearMarker();
            }
        });
    });
}

function localisation() {
    let address = $("#address").val();

    var geocoder = L.Control.Geocoder.nominatim();
    let marker;    

    geocoder.geocode(address, function (result) {
        var r = result[0]; 
        if (r) {
            marker = L.marker(r.center)
                .bindPopup(r.name)
                .addTo(map)
                .openPopup();
            
	        if (marker) markers.push(marker);
	        console.log(marker);
		    if (markers.length > 1) clearMarker();
        }

    });
}

function clearMarker() {
	map.removeLayer(markers[0]);
	markers.shift()
}

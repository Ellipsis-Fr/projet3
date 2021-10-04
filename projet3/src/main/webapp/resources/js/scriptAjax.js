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


// ======== ASSOCIAITON FORGOT PASSWORD ========
$("form[name='forgotPassword']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "forgotPassword");
});

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

// ======== PARTICIPANT FORM ========

$("form[name='formParticipantRegistration']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newParticipant");
});

// ======== PARTNER FORM ========

$("form[name='formPartnerRegistration']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newPartner");
});

// ======== ACTIVITY FORM ========

$("form[name='formActivityRegistration']").on('submit', (e) => {
	e.preventDefault();
	let targetedForm = e.target;
	let formData = new FormData(targetedForm);
	sendForm(formData, "newActivity");
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
	
	if (result == "Proposition d'Activité envoyée.") {
		success = true;
		text = result;
	}
	
	if (result == "Mot de passe du compte envoyé.") {
		success = true;
		text = result;
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
var mySwiper;

var markersUser = [];
var latUser;
var lonUser;
var searchDistance;

var markersAsso = [];
var addressAsso = [];
var namesAsso = [];
var idsAsso = [];
var latsAsso = [];
var lonsAsso = [];

var distUserAsso = [];

$(document).ready(start);

function start() {
	if ($("#index").length == 0) return;
	initMapLeafset(46.50, 2.20); //lat et lng pour centrer sur la france
	
	$("#entry").click(localisation);
	
	searchDistance = false;
	
	mySwiper = new Swiper ('.swiper-container', {
	  // Optional parameters
	  direction: 'horizontal',
	  autoplay: true,
	  speed: 4000,
	  longSwipesMs: 500,
	  slidesPerView: 3,
	  width: 1300,
	  spaceBetween: 10,
	  
	  on: {
		init: init,
		reachBeginning : init,
	    slideChange: slideChange,
	  }
	})
	

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
		if (markersUser.length > 0) clearMarker(markersUser.shift());

        geocoder.reverse(e.latlng, map.options.crs.scale(map.getZoom()), function (results) {
            var r = results[0];
            if (r) {
				searchDistance = true;
				
				latUser = r.center.lat;
    			lonUser = r.center.lng;
				setTimeout(calculDistances, 0);
                marker = L.marker(r.center)
                    .bindPopup('<span> Ma Position <br/> </span>' + r.html)
                    .addTo(map)
                    .openPopup();
                    marker._icon.classList.add("huechange");
                markersUser.push(marker);
            }
        });
    });
}

function localisation() {
	if (markersUser.length > 0) clearMarker(markersUser.shift());
	
    let address = $("#address").val();

    var geocoder = L.Control.Geocoder.nominatim();
    let marker;    

    geocoder.geocode(address, function (result) {
        var r = result[0]; 
        if (r) {
			searchDistance = true;
			
			latUser = r.center.lat;
			lonUser = r.center.lng;
			setTimeout(calculDistances, 0);
            marker = L.marker(r.center)
                .bindPopup('<span> Ma Position <br/> </span>' + r.html)
                .addTo(map)
                .openPopup();
            marker._icon.classList.add("huechange");
            
	        if (marker) markersUser.push(marker);		
        }

    });
}

function localisationAssociation(address, name, push) {
	var geocoder = L.Control.Geocoder.nominatim();
    let marker;    

    geocoder.geocode(address, function (result) {
        var r = result[0]; 
        if (r) {	
            marker = L.marker(r.center)
                .bindPopup(name + " - " + address)
                .addTo(map)
                .openPopup();
            
	        if (marker) {
				if (push) {
					markersAsso.push(marker);
					latsAsso.push(r.center.lat);
					lonsAsso.push(r.center.lng);
				}
				else {
					markersAsso.unshift(marker);
					latsAsso.unshift(r.center.lat);
					lonsAsso.unshift(r.center.lng);
				}
			}
			
			if(searchDistance) setTimeout(calculDistances, 0);
        }
    });
}


function calculDistances() {
	distUserAsso = [];
    
    for (i = 0; i < markersAsso.length; i++) {
		latAsso = latsAsso[i];
		lonAsso = lonsAsso[i];
		d = getDistance([latUser, lonUser], [latAsso, lonAsso]) / 1000;
		d = parseInt(d);
		distUserAsso.push(d);
	}

	showDistances();
}

function getDistance(origin, destination) {
    // return distance in meters
    var lon1 = toRadian(origin[1]),
        lat1 = toRadian(origin[0]),
        lon2 = toRadian(destination[1]),
        lat2 = toRadian(destination[0]);

    var deltaLat = lat2 - lat1;
    var deltaLon = lon2 - lon1;

    var a = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(deltaLon/2), 2);
    var c = 2 * Math.asin(Math.sqrt(a));
    var EARTH_RADIUS = 6371;
    return c * EARTH_RADIUS * 1000;
}

function toRadian(degree) {
    return degree*Math.PI/180;
}







function showDistances() {
	let distances = $("#distances");
	
	if ($("#distances li")) $("#distances li").remove();	 
	
	for (i = 0; i < markersAsso.length; i++) {
		distances.append("<li><a href='event?id=" + idsAsso[i] + "'>" + namesAsso[i] + "</a> : " + distUserAsso[i] + " km" + "</li>")
	}
}

function clearMarker(marker) {
	map.removeLayer(marker);
}

function clearMarkers(markers) {
	for(i = 0; i < markers.length; i++) map.removeLayer(markers[i]);
	markersAsso = [];
}

function init() {

	let index = 0;
	let slidez = this.slides;
	
	
	initThreeAssoLocalisation(index, slidez);
}

function slideChange() {
	let index = this.activeIndex;
	let realIndex = this.realIndex;
	let slidez = this.slides;
	let countSlide = slidez.length;
	let gap = 2; 


	let infosAssoActive = ((slidez[index]).id).split("%");
	let infosAssoNext = ((slidez[index + 1]).id).split("%");

	if (idsAsso[1] == infosAssoActive[2]) {
		
		addressAsso.shift();
		namesAsso.shift();
		idsAsso.shift();
		clearMarker(markersAsso.shift())
		latsAsso.shift();
		lonsAsso.shift();

		let infos = ((slidez[index + gap]).id).split("%");
		
		addressAsso.push(infos[0]);
		namesAsso.push(infos[1]);
		idsAsso.push(infos[2]);

		localisationAssociation(addressAsso[2], namesAsso[2], true);
		
	}
}

function initThreeAssoLocalisation(index, slidez) {
		addressAsso = [];
		namesAsso = [];
		idsAsso = [];
		latsAsso = [];
		lonsAsso = [];
		console.log(markersAsso)
		clearMarkers(markersAsso);
		
	
	
	for (i = index, j = 0; j < 3; i++, j++) {
		if (i == slidez.length) i = 0;
		let infos = ((slidez[i]).id).split("%");

		addressAsso.push(infos[0]);
		namesAsso.push(infos[1]);
		idsAsso.push(infos[2]);
		
		console.log(infos)
	}
	
	localisationAssociation(addressAsso[0], namesAsso[0], true);
	localisationAssociation(addressAsso[1], namesAsso[1], true);
	localisationAssociation(addressAsso[2], namesAsso[2], true);
}
	
	
// ======== EDIT DOCUMENT : DELETE PHOTO ========

$(".delete").click((e) => {
	e.preventDefault();
	console.log(e.currentTarget)
	let oElem = e.currentTarget;
	oElem.parentNode.parentNode.parentNode.removeChild(oElem.parentNode.parentNode);
	
	deletePhoto(e);

});

function deletePhoto(e) {
	
	$.ajax({
		url: "deletePhoto",
		type: "POST",
		data: {"id": e.currentTarget.id},
		error: () => {
			console.log("raté");
		}
	})
}


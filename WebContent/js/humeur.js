/**
 * Fonction d'ajout de humeur
 * 
 * @param formulaire
 */

function addHumeur(formulaire) {
	var humeur = $("#text_humeur").val();
	$.ajax({
		type : "Get",
		url : "/COQUART/AddHumeur",
		data : "login=" + environnement.actif.login + "&humeur=" + humeur,
		dataType : "json",
		success : afficheHumeur,
		error : ""
	});

};

/**
 * Traite la reponse de la servlet Addhumeur
 * 
 * @param json
 */

function afficheHumeur() {
	$.ajax({
		type : "Get",
		url : "/COQUART/ReadHumeur",
		data : "login=" + environnement.actif.login,
		dataType : "json",
		success : Humeur.traiteReponse,
		error : ""
	});
};

/**
 * recupere l'humeur de login
 * @param login
 */
function getHumeur(login) {
	$.ajax({
		type : "Get",
		url : "/COQUART/ReadHumeur",
		data : "login=" + login,
		dataType : "json",
		success : Humeur.traitePageUser,
		error : ""
	});
};

/**
 * recupere l'humeur depuis le formulaire
 * @param form
 */
function getHumeurDeux(form) {
	$.ajax({
		type : "Get",
		url : "/COQUART/ReadHumeur",
		data : "login=" + $("#txt_login").val(),
		dataType : "json",
		success : Humeur.traiteReponse,
		error : ""
	});
	cherche_par_login($("#txt_login").val());
};
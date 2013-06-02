/**
 * Gere les messages d'erreur
 * 
 * @param msg
 */
function func_erreur(msg) {
	var msg_box = "<div id=\"msg-err-connexion\">" + msg + "</div>";
	var tab = $("#msg-err-connexion");
	if (tab.length == 0) {
		$("form").prepend(msg_box);
		$("#msg-err-connexion").css({
			"color" : "red"
		});
	} else {
		tab.replaceWith(msg_box);
		$("#msg-err-connexion").css({
			"color" : "red"
		});
	}
}

/**
 * Fonction d'enregistrement
 * 
 * @param formulaire
 */
function enregistrement(formulaire) {
	var prenom = formulaire.prenom.value;
	var nom = formulaire.nom.value;
	var login = formulaire.login.value;
	var pass = formulaire.mdp.value;
	var ok = verif(prenom, nom, login, pass);
	if (ok) {
		enregistre(prenom, nom, login, pass);
	}
}

/**
 * Verifie si les parametres sont correcte
 * 
 * @param nom
 * @param prenom
 * @param login
 * @param pass
 * @returns {Boolean}
 */
function verif(nom, prenom, login, pass) {
	if (prenom.length == 0) {
		func_erreur("Prenom obligatoire");
		return false;
	} else if (nom.length == 0) {
		func_erreur("Nom obligatoire.");
		return false;
	} else if (login.length == 0) {
		func_erreur("Login obligatoire.");
		return false;
	} else if (pass.length == 0) {
		func_erreur("Mot de passe obligatoire.");
		return false;
	} else {
		return true;
	}
}

/**
 * Gere l'enregistrement
 * 
 * @param nom
 * @param prenom
 * @param login
 * @param password
 */
function enregistre(prenom, nom, login, password) {
	$.ajax({
		type : "GET",
		url : "/COQUART/CreateUser",
		data : "prenom=" + prenom + "&nom=" + nom + "&login=" + login + "&mdp="
				+ password,
		dataType : "json",
		success : traiteReponseEnregistrement,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

/**
 * S'occupe de la reponse de la servlet
 * 
 * @param rep
 */
function traiteReponseEnregistrement(rep) {
	if (rep.error != undefined)
		func_erreur(rep.error);
	else {
		window.location.href = "indexLogin.jsp";
	}
}
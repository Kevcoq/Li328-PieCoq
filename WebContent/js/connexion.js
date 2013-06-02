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
 * Fonction de connexion
 * 
 * @param formulaire
 */
function connexion(formulaire) {
	var login = formulaire.login.value;
	var pass = formulaire.mdp.value;
	var ok = verif_co(login, pass);
	if (ok)
		connecte(login, pass);
	return false;
}

/**
 * Verifie si les parametres sont correcte
 * 
 * @param login
 * @param pass
 * @returns {Boolean}
 */
function verif_co(login, pass) {
	if (login.length == 0) {
		func_erreur("Login obligatoire");
		return false;
	} else if (pass.length == 0) {
		func_erreur("Mot de passe obligatoire.");
		return false;
	} else {
		return true;
	}
}

/**
 * Gere la connexion
 * 
 * @param login
 * @param password
 */
function connecte(login, password) {
	$.ajax({
		type : "GET",
		url : "/COQUART/Login",
		data : "login=" + login + "&mdp=" + password,
		dataType : "json",
		success : traiteReponseConnexion,
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
function traiteReponseConnexion(rep) {
	if (rep.error != undefined) {
		func_erreur(rep.error);
	} else
		window.location.href = "index.jsp?id='" + rep.id + "'&login='"
				+ rep.login + "'&clef='" + rep.clef + "'";
}

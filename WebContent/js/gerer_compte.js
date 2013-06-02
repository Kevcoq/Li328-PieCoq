/**
 * Met à jour les liens
 */
function maj() {
	// $("#sup_images").click(sup_images);
	$("#sup_likes").click(sup_likes);
	$("#sup_comments").click(sup_comments);
	$("#sup_message").click(sup_message);
}

/**
 * Initialise les champs de confirmation
 */
function init() {
	$("#rep_sup_message").html("");
	$("#rep_sup_likes").html("");
	$("#rep_sup_comments").html("");
	// $("#rep_sup_images").html("");
}

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
 * Fonction de suppression de compte
 * 
 * @param formulaire
 */
function sup_compte(formulaire) {
	init();
	var login = formulaire.login.value;
	var pass = formulaire.mdp.value;
	var ok = verif(login, pass);
	if (ok)
		suppression_compte(environnement.clef, login, pass);
}

/**
 * Verifie si les parametres sont correcte
 * 
 * @param login
 * @param pass
 * @returns {Boolean}
 */
function verif(login, pass) {
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
 * Gere la suppression du compte
 * 
 * @param clef
 * @param login
 * @param password
 */
function suppression_compte(clef, login, password) {
	$.ajax({
		type : "GET",
		url : "/COQUART/DeleteUser",
		data : "clef=" + clef + "&login=" + login + "&mdp=" + password,
		dataType : "json",
		success : traiteReponseSup,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

/**
 * Fonction de suppression de ces messages
 */
function sup_message() {
	init();
	suppression_message(environnement.clef);
}

/**
 * Gere la suppression de ces messages
 * 
 * @param clef
 */
function suppression_message(clef) {
	$.ajax({
		type : "GET",
		url : "/COQUART/DeleteCesStatut",
		data : "clef=" + clef,
		dataType : "json",
		success : function() {
			$("#rep_sup_message").html("Message supprime");
		},
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

/**
 * Fonction de suppression de ces comments
 */
function sup_comments() {
	init();
	suppression_commentaires(environnement.clef);
}

/**
 * Gere la suppression de ces commentaires
 * 
 * @param clef
 */
function suppression_commentaires(clef) {
	$.ajax({
		type : "GET",
		url : "/COQUART/DeleteCesComments",
		data : "clef=" + clef,
		dataType : "json",
		success : function() {
			$("#rep_sup_comments").html("Commentaires supprime");
		},
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

/**
 * Fonction de suppression de ces likes
 */
function sup_likes() {
	init();
	suppression_likes(environnement.clef);
}

/**
 * Gere la suppression de ces likes
 * 
 * @param clef
 */
function suppression_likes(clef) {
	$.ajax({
		type : "GET",
		url : "/COQUART/DeleteCesLikes",
		data : "clef=" + clef,
		dataType : "json",
		success : function() {
			$("#rep_sup_likes").html("Likes supprime");
		},
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

// /**
// * Fonction de suppression de ces images
// */
// function sup_images() {
// init();
// suppression_images(environnement.clef);
// }
//
// /**
// * Gere la suppression de ces images
// *
// * @param clef
// */
// function suppression_images(clef) {
// $.ajax({
// type : "GET",
// url : "/COQUART/DeleteCesImage",
// data : "clef=" + clef,
// dataType : "json",
// success : function() {
// $("#rep_sup_images").html("Images supprimee");
// },
// error : function(XHR, testStatus, errorThrown) {
// alert(XHR + "" + testStatus + "" + errorThrown);
// }
// });
// }

/**
 * S'occupe de la reponse de la servlet
 * 
 * @param rep
 */
function traiteReponseSup(rep) {
	if (rep.error != undefined) {
		func_erreur(rep.error);
	} else
		window.location.href = "index.jsp";
}

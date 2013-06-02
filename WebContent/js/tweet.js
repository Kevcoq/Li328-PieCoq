/**
 * La fonction de recherche de tweet
 */
function cherche() {
	if ($("#text_recherche").val() != "")
		$.ajax({
			type : "Get",
			url : "/COQUART/Search",
			data : "recherche=" + $("#text_recherche").val(),
			dataType : "json",
			success : Message.traiteReponse,
			error : function(e) {
				$("#liste_tweet").html(e);
			}
		});
	else if (environnement != undefined && environnement.clef != undefined
			&& $("#check_ami").get(0).checked)
		$.ajax({
			type : "Get",
			url : "/COQUART/AfficherStatutAmi",
			data : "clef=" + environnement.clef,
			dataType : "json",
			success : Message.traiteReponse,
			error : function(e) {
				$("#liste_tweet").html(e);
			}
		});
	else
		$.ajax({
			type : "Get",
			url : "/COQUART/AfficherStatut",
			data : "",
			dataType : "json",
			success : Message.traiteReponse,
			error : function(e) {
				$("#liste_tweet").html(e);
			}
		});
}

/**
 * Affiche les status de login
 * 
 * @param login
 */
function cherche_par_login(login) {
	$.ajax({
		type : "Get",
		url : "/COQUART/AfficherCesStatut",
		data : "login=" + login,
		dataType : "json",
		success : Message.traiteReponse,
		error : function(e) {
			$("#liste_tweet").html(e);
		}
	});
}

/**
 * Pour poster un message
 * 
 * @param formulaire
 */
function poster(formulaire) {
	var message = formulaire.txt_message.value;
	$.ajax({
		type : "Get",
		url : "/COQUART/PosterStatut",
		data : "clef=" + environnement.clef + "&message=" + message,
		dataType : "json",
		success : cherche,
		error : function(e) {
			$("#liste_tweet").html(e);
		}
	});
	$("#add_comments")
			.html('<p>Message envoye</p><h1>Poster un message :</h1>');

}

/**
 * Supprime un message
 * 
 * @param evt
 */
function supprimer(evt) {
	$.ajax({
		type : "Get",
		url : "/COQUART/DeleteOneStatut",
		data : "clef=" + environnement.clef + "&id_message="
				+ evt.currentTarget.name,
		dataType : "json",
		success : cherche,
		error : function(e) {
			$("#liste_tweet").html(e);
		}
	});
}

/**
 * Permet de liker un tweet
 * 
 * @param msg
 */
function aime(evt) {
	$.ajax({
		type : "Get",
		url : "/COQUART/SendLike",
		data : "clef=" + environnement.clef + "&like=like&id_tweet="
				+ evt.currentTarget.name,
		dataType : "json",
		success : cherche,
		error : function(e) {
			$("#liste_tweet").html(e);
		}
	});
}

/**
 * Permet de detester un tweet
 * 
 * @param msg
 */
function aimepas(evt) {
	$.ajax({
		type : "Get",
		url : "/COQUART/SendLike",
		data : "clef=" + environnement.clef + "&like=unlike&id_tweet="
				+ evt.currentTarget.name,
		dataType : "json",
		success : cherche,
		error : function(e) {
			$("#liste_tweet").html(e);
		}
	});
}
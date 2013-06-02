/**
 * La fonction d'obtention des commentaires
 */
function getComments(id_tweet) {
	$.ajax({
		type : "Get",
		url : "/COQUART/ReadCommentsDuTWEET",
		data : "id_tweet=" + id_tweet,
		dataType : "json",
		success : Comments.traiteReponse,
		error : ""
	});
}

/**
 * Pour poster un message
 * 
 * @param formulaire
 */
function poster_comments(formulaire) {
	var message = formulaire.text_comments;
	$.ajax({
		type : "Get",
		url : "/COQUART/SendComments",
		data : "clef=" + environnement.clef + "&message=" + message.value
				+ "&id_tweet=" + message.name,
		dataType : "json",
		success : cherche,
		error : ""
	});
	$("#add_comments")
			.html('<p>Message envoye</p><h1>Poster un message :</h1>');

}

/**
 * Supprime un commentaires
 * 
 * @param evt
 */
function supprimer_comments(evt) {
	$.ajax({
		type : "Get",
		url : "/COQUART/DeleteOneComments",
		data : "clef=" + environnement.clef + "&id_message="
				+ evt.currentTarget.name,
		dataType : "json",
		success : cherche,
		error : ""
	});
}
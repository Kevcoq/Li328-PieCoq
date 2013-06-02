/**
 * Liste les utilisateur existant
 */
function liste() {
	if (environnement.clef != undefined)
		$.ajax({
			type : "GET",
			url : "/COQUART/ListeUser",
			data : "clef=" + environnement.clef,
			dataType : "json",
			success : User.traiteReponse,
			error : function(XHR, testStatus, errorThrown) {
				alert(XHR + "" + testStatus + "" + errorThrown);
			}
		});
	else {
		$.ajax({
			type : "GET",
			url : "/COQUART/ListeUser",
			data : "",
			dataType : "json",
			success : User.traiteReponse,
			error : function(XHR, testStatus, errorThrown) {
				alert(XHR + "" + testStatus + "" + errorThrown);
			}
		});
		$("#text_mp").css("visibility", "hidden");
	}
}

/**
 * Permet d'ajouter ou supprimer le contact
 * 
 * @param evt
 *            id du contact
 */
function ajout_sup_contact(evt) {
	var id = evt.currentTarget.name;
	var user = environnement.users[id];
	var url = "/COQUART/AddFriend";
	if (user.contact) {
		url = "/COQUART/DeleteFriend";
	}
	$.ajax({
		type : "GET",
		url : url,
		data : "clef=" + environnement.clef + "&id_friend=" + id,
		dataType : "json",
		success : function(rep) {
			traiteReponceAjoutSupContact(rep, id);
		},
		error : ""
	});
}

/**
 * Traite la reponse d'ajout/suppresion du contact
 * 
 * @param rep
 *            reponse de la servlet
 * @param id
 *            id du contact
 */
function traiteReponceAjoutSupContact(rep, id) {
	if (rep.error != undefined) {
		alert(rep.error);
	} else {
		var user = environnement.users[id];
		user.modifStatut();
		liste();
	}
}
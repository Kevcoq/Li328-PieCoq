/**
 * Liste les mp recu
 */
function liste_mp() {
	init_mp();
	$.ajax({
		type : "GET",
		url : "/COQUART/ListeMP",
		data : "clef=" + environnement.clef,
		dataType : "json",
		success : MP.traiteReponse,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

/**
 * Initialise le champs de confirmation d'envoie d'un mp
 */
function init_mp() {
	$("#rep_mp").html("");
}

/**
 * Supprimer un mp
 * 
 * @param evt
 */
function supprimer_mp(evt) {
	$.ajax({
		type : "Get",
		url : "/COQUART/DeleteOneMP",
		data : "clef=" + environnement.clef + "&id_message="
				+ evt.currentTarget.name,
		dataType : "json",
		success : liste_mp,
		error : ""
	});
}

/**
 * Permet d'envoyer un mp
 * 
 * @param evt
 */
function envoyer_mp(evt) {
	init_mp();
	if ($("#text_mp").val() != "") {
		var id = evt.currentTarget.name;
		$.ajax({
			type : "GET",
			url : "/COQUART/SendMP",
			data : "clef=" + environnement.clef + "&id_dest=" + id
					+ "&message=" + $("#text_mp").val(),
			dataType : "json",
			success : function() {
				$("#rep_mp").html("MP envoye");
			},
			error : ""
		});
	}
}

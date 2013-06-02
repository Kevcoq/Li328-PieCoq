function getStats() {
	$.ajax({
		type : "Get",
		url : "/COQUART/GetStats",
		data : "",
		dataType : "json",
		success : statsTraiteRep,
		error : function(e) {
			$("#stats").html(e);
		}
	});
}

function statsTraiteRep(json) {
	var s = "";
	s += "<ul><li>Nombre d'utilisateur : " + json.nb_users + "</li>";
	s += "<li>Nombre de tweet : " + json.nb_tweet + "</li>";
	s += "<li>Nombre de comments : " + json.nb_comments + "</li>";
	s += "<li>Nombre de like|unlike : " + json.nb_like + "</li></ul>";

	$("#stats").html(s);
}
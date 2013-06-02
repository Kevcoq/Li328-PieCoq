/**
 * Permet de switcher dans la zone connexion entre login et logout et de moduler
 * la zone pour poster un message
 */
function gererDivConnexion() {
	var user = environnement.actif;
	if (user != undefined) {
		$("#logo").html(
				"<a href=\"index.jsp?id='" + user.id + "'&login='" + user.login
						+ "'&clef='" + environnement.clef
						+ "'\"><img src=\"image/baniere.png\" /></a>");
		$("#connectlog").html(
				"<SPAN id=\"log\">Welcome => " + user.login + " <=</SPAN>");

		var s = "";
		s += '<input class="button" id="logout" type="button" value="Logout" /><br/>';
		s += "<input class=\"button\" id=\"gerer_compte\" type=\"button\" "
				+ "value=\"Gerer son compte\" onclick="
				+ "\"window.location.href='indexGererCompte.jsp?id=\\'"
				+ user.id + "\\'&login=\\'" + user.login + "\\'&clef=\\'"
				+ environnement.clef + "\\''\" /><br/>";
		s += "<input class=\"button\" id=\"lien_liste_mp\" type=\"button\" "
				+ "value=\"Liste MP\" onclick="
				+ "\"window.location.href='indexMP.jsp?id=\\'" + user.id
				+ "\\'&login=\\'" + user.login + "\\'&clef=\\'"
				+ environnement.clef + "\\''\" /><br/>";
		s += "<input class=\"button\" id=\"lien_liste_user\" type=\"button\" "
				+ "value=\"Liste User\" onclick="
				+ "\"window.location.href='indexUsers.jsp?id=\\'" + user.id
				+ "\\'&login=\\'" + user.login + "\\'&clef=\\'"
				+ environnement.clef + "\\''\" /><br/>";
		s += "<input class=\"button\" id=\"lien_get_humeur\" type=\"button\" "
				+ "value=\"Get Humeur\" onclick="
				+ "\"window.location.href='indexGetHumeur.jsp?id=\\'" + user.id
				+ "\\'&login=\\'" + user.login + "\\'&clef=\\'"
				+ environnement.clef + "\\''\" />";
		$("#bouton_log").html(s);
		$("#logout").click(disconnect);

		$("#add_comments").html('<h1>Poster un message :</h1>');
		$("#bouton_add_comments").css("visibility", "visible");
		$("#zone_humeur").show();
	} else {
		$("#logo").html(
				"<a href=\"index.jsp\"><img src=\"image/baniere.png\" /></a>");
		$("#connectlog").html("");

		var s = "";
		s += '<input class="button" id="login" type="button" value="Login" onclick="window.location.href=\'indexLogin.jsp\'" /><br/>';
		s += '<input class="button" id="lien_liste_user" type="button" '
				+ 'value="Liste User" onclick='
				+ '"window.location.href=\'indexUsers.jsp\'" /><br/>';
		s += '<input class="button" id="lien_get_humeur" type="button" '
				+ 'value="Get Humeur" onclick='
				+ '"window.location.href=\'indexGetHumeur.jsp\'" />';
		$("#bouton_log").html(s);

		$("#add_comments").html(
				'<p>Pour poster un message, il faut etre connecte</p>');
		$("#bouton_add_comments").css("visibility", "hidden");
		$("#zone_humeur").hide();
	}

	$("#footer").html("<h1>PieCoq&copy;</h1>");
}

/**
 * deconnection, suppresion de l'environnement, modification de la page,
 * affichage des tweet de base
 */
function disconnect() {
	environnement.actif = undefined;
	$.ajax({
		type : "GET",
		url : "/COQUART/Logout",
		data : "clef=" + environnement.clef,
		dataType : "json",
		success : "",
		error : ""
	});
	gererDivConnexion();
	cherche();
}

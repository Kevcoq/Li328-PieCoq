<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Message Privé</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/mp.js"></script>
<script type="text/javascript" src="js/stats.js"></script>

<script type="text/javascript">
	function go() {
<%String id = request.getParameter("id");
			String login = request.getParameter("login");
			String clef = request.getParameter("clef");
			if ((id != null) && (login != null) && (clef != null)) {
				out.println("main(" + id + "," + login + "," + clef + ");");
			} else {
				out.println("main();");
			}%>
	};
	$(go);
	$(liste_mp);
	$(getStats);
	$(afficheHumeur);
</script>
</head>
<body>
	<div id="page">

		<div id="header">
			<div id=logo></div>
			<div id="divconnect">
				<div id="connectlog"></div>
				<div id="bouton_log"></div>
			</div>
		</div>

		<div id="gauche">
			<div id="zone_humeur">
				<h1>Humeur</h1>
				<div id="humeur"></div>
				<form id="form_humeur" method="get"
					action="javascript:(function(){return;})()"
					OnSubmit="javascript:addHumeur(this)">
					<textarea name="humeur" id="text_humeur" placeholder="Votre Statut"></textarea>
					<input class="button" id="button_humeur" type=submit
						value="Ajouter Humeur" />
				</form>
			</div>

			<div id="zone_stats">
				<h1>Zone de stats</h1>
				<div id="stats"></div>
			</div>
		</div>

		<div id="main">
			<div id="zone_mp">
				<textarea id="text_mp" placeholder="Entrez le texte de MP ici."></textarea>
				<span id="rep_mp"></span>
			</div>
			<h1>Liste des MP reçus :</h1>
			<div id="liste_mp"></div>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/gerer_compte.js"></script>
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
	$(maj);
	$(getStats);
	$(afficheHumeur);
</script>

<title>Gerer son compte</title>
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
			<h1>Supprimer son compte</h1>
			<form action="javascript:(function(){return;})()" method="get"
				OnSubmit="javascript:sup_compte(this)">
				<div class="ligne">
					<span>Login :</span> <input type="text" name="login"
						placeholder="login" />
				</div>
				<div class="ligne">
					<span>Mot de Passe :</span><input type="password" name="mdp"
						placeholder="password" />
				</div>
				<div>
					<input type="submit" class="button" value="Valider" /> <input
						type="button" class="button" value="Annuler"
						onclick="window.location.href='index.jsp'" />
				</div>
			</form>

			<h1>Supprimer ces messages</h1>
			<span id="rep_sup_message"></span> <input id="sup_message"
				class="button" type="button" value="Supprimer ces messages" />

			<h1>Supprimer ces commentaires</h1>
			<span id="rep_sup_comments"></span> <input id="sup_comments"
				class="button" type="button" value="Supprimer ces commentaires" />

			<h1>Supprimer ces likes</h1>
			<span id="rep_sup_likes"></span> <input id="sup_likes" class="button"
				type="button" value="Supprimer ces likes" />

			<!-- <h1>Supprimer ces images</h1>
			<span id="rep_sup_images"></span>
			<input id="sup_images" class="button" type="button"
				value="Supprimer ces images" /> -->
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>
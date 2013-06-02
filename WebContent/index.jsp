<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>PieCoq</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/tweet.js"></script>
<script type="text/javascript" src="js/comments.js"></script>
<script type="text/javascript" src="js/humeur.js"></script>
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
	$(cherche);
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

		<div id="recherche">
			<form id="form_recherche" method="get"
				action="javascript:(function(){return;})()"
				OnSubmit="javascript:cherche(this)">
				<textarea name="recherche" id="text_recherche"
					placeholder="Votre recherche"></textarea>
				<input class="button" id="button_recherche" type=submit
					value="Rechercher" /> <input id="check_ami" type="checkbox"
					value="SelectFriends" /> <span id="txt_check_ami">Dans mes
					amis</span>
			</form>
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
			<div id="add_comments"></div>
			<div id="bouton_add_comments">
				<form name="form_poster" action="javascript:(function(){return;})()"
					method="get" OnSubmit="javascript:poster(this)">
					<textarea id="txt_message" name="message"
						placeholder="Votre message"></textarea>
					<br /> <input class="button" type="submit" value="Poster" />
				</form>
			</div>
			<h1>Liste des tweets</h1>
			<div id="liste_tweet"></div>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>
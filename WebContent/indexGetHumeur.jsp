<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form name="form_getHumeur"
				action="javascript:(function(){return;})()" method="get"
				OnSubmit="javascript:getHumeurDeux(this)">
				<textarea id="txt_login" name="login"
					placeholder="Login de l'utilisateur voulu"></textarea>
				<br /> <input class="button" type="submit" value="GO !!!" />
			</form>
			<div id="humeur"></div>


			<h1>Liste des tweets de l'utilisateur</h1>
			<div id="liste_tweet"></div>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>
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
<script type="text/javascript" src="js/connexion.js"></script>
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
</script>
<title>PieCoq</title>
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
			<div id="zone_stats">
				<h1>Zone de stats</h1>
				<div id="stats"></div>
			</div>
		</div>

		<div id="main">
			<h1>Ouvrir une session</h1>
			<form name="form_login" action="javascript:(function(){return;})()"
				method="get" OnSubmit="javascript:connexion(this)">
				<div class="ligne">
					<span>Login :</span> <input type="text" name="login"
						placeholder="login" />
				</div>
				<div class="ligne">
					<span>Mot de Passe :</span><input type="password" name="mdp"
						placeholder="password" />
				</div>
				<div>
					<input class="button" type="submit" value="Valider" /> <input
						class="button" type="button" value="Annuler"
						onclick="window.location.href='index.jsp'" />
				</div>
			</form>

			<input class="button" id="enregistrer" type="button"
				value="Enregistrer"
				onclick="window.location.href='indexEnregistrement.jsp'" />
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>
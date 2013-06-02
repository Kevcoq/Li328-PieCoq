/**
 * Creation de l'environnement
 */
function main(id, login, clef) {
	environnement = {};
	environnement.users = new Array();

	environnement.switchContext = function() {
		if (this.context == "connected") {
			this.context = "disconnected";
		} else if (this.context == "disconnected") {
			this.context = "connected";
		}
		gererDivConnexion();
	};

	if ((id != undefined) && (login != undefined) && (clef != undefined)) {
		environnement.clef = clef;
		environnement.actif = new User(id, login, true);
	}
	gererDivConnexion();
}

/**
 * Creer un objet user
 * 
 * @param id
 * @param login
 * @param contact
 * @returns un objet user
 */
function User(id, login, contact) {
	this.id = id;
	this.login = login;
	this.contact = contact;
	if (environnement.users != undefined)
		environnement.users[id] = this;
}

/**
 * Fonction pour modifier le statut d'un user
 */
User.prototype.modifStatut = function() {
	this.contact = !this.contact;
};

/**
 * Obtient le code html d'un utilisateur
 * 
 * @param user
 * @returns {String}
 */
User.prototype.getHTML = function(user) {
	var plus = 'user_plus_' + this.id;
	var moins = 'user_moins_' + this.id;
	var mp = 'user_mp_' + this.id;
	var s = '<div class="user">';
	s += this.id + ' | ' + this.login;
	s += '-><span id="humeur_' + this.login + '"></span>';
	s += ' <input id="' + plus + '" name="' + this.id
			+ '" class="button" type="button" value="+" />  <input id="'
			+ moins + '" name="' + this.id
			+ '" class="button" type="button" value="-" /> <input id="' + mp
			+ '" name="' + this.id
			+ '" class="button" type="button" value="MP" />';
	s += '</div>';
	return s;
};

/**
 * Traite la réponse de la servlet liste_user
 * 
 * @param json
 */
User.traiteReponse = function(json) {
	var s = "";
	for ( var i = 0; i < json.length; i++) {
		var obj = new User(json[i].id, json[i].login, json[i].contact);
		s += obj.getHTML(obj);
	}
	$("#liste_users").html(s);

	for ( var i = 0; i < json.length; i++) {
		var plus = "#user_plus_" + json[i].id;
		var moins = "#user_moins_" + json[i].id;
		var mp = "#user_mp_" + json[i].id;
		$(plus).click(ajout_sup_contact);
		$(moins).click(ajout_sup_contact);
		$(mp).click(envoyer_mp);

		getHumeur(json[i].login);

		if (environnement != undefined && environnement.clef != undefined
				&& environnement.actif.id != json[i].id) {
			if (json[i].contact)
				$(plus).css("visibility", "hidden");
			else
				$(moins).css("visibility", "hidden");
		} else {
			$(moins).css("visibility", "hidden");
			$(plus).css("visibility", "hidden");
			$(mp).css("visibility", "hidden");
		}
	}
};

/**
 * Créer un objet mp
 * 
 * @param id_msg
 * @param id
 * @param auteur
 * @param id_dest
 * @param texte
 * @param statut
 * @param date
 * @returns
 */
function MP(id_msg, id, auteur, id_dest, texte, date) {
	this.idmsg = id_msg;
	this.id = id;
	this.auteur = auteur;
	this.iddest = id_dest;
	this.texte = texte;
	this.date = new Date(date);
}

/**
 * Le code html d'un utilisateur
 * 
 * @param mp
 * @returns {String}
 */
MP.prototype.getHTML = function(msg) {
	var s = '<hr/><div class="mp"><p><u>' + this.auteur + ' </u>: <b>';
	s += this.texte;
	s += '</b><span style="float : right;"><input id="suppr_' + this.idmsg
			+ '" name="' + this.idmsg
			+ '" class="button" type="button" value="Supprimer"/>';
	s += '<input id="mp_'
			+ this.idmsg
			+ '" name="'
			+ this.id
			+ '" class="button" type="button" value="Repondre" /></span><br/><i>';
	s += this.date.toDateString() + " at " + this.date.toLocaleTimeString();
	s += '</i></p></div><hr/>';
	return s;
};

/**
 * Traite la réponse de la servlet liste_mp
 * 
 * @param json
 */
MP.traiteReponse = function(json) {
	var s = "";
	var tmp = new Array();
	for ( var i = 0; i < json.length; i++) {
		var mp = new MP(json[i]._id.$oid, json[i].author_id,
				json[i].author_login, json[i].id_dest, json[i].text,
				json[i].date.$date);
		tmp[i] = mp;
		s += mp.getHTML(mp);
	}
	$("#liste_mp").html(s);

	for ( var i = 0; i < tmp.length; i++) {
		var suppr = "#suppr_" + tmp[i].idmsg;
		var mp = "#mp_" + tmp[i].idmsg;
		$(suppr).click(supprimer_mp);
		$(mp).click(envoyer_mp);
	}
};

/**
 * Creer un message
 * 
 * @param id
 * @param auteur
 * @param texteBouh
 * @param date
 * @param score
 * @returns un objet message
 */
function Message(id_msg, id, auteur, texte, date, like, unlike) {
	this.idmsg = id_msg;
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = new Date(date);
	this.like = like;
	this.unlike = unlike;
}

/**
 * Renvoie le html d'un message
 * 
 * @param message
 * @returns {String}
 */
Message.prototype.getHTML = function(message) {
	var s = '<hr/><div class="tweet"><p><u>';
	s += this.auteur;
	s += '</u> : <b>';
	s += this.texte;
	if (environnement != undefined && environnement.clef != undefined)
		s += '<input id="suppr_' + this.idmsg + '" name="' + this.idmsg
				+ '" class="button" type="button" value="Supprimer"/>';
	s += '</b><br/><br/><i>';
	s += this.date.toDateString() + " at " + this.date.toLocaleTimeString();
	s += '</i><br/>=> Like : ';
	s += this.like;
	s += ' | Unlike : ';
	s += this.unlike;
	s += ' <=';
	if (environnement != undefined && environnement.clef != undefined)
		s += '<br/><input id="like_'
				+ this.idmsg
				+ '" name="'
				+ this.idmsg
				+ '" class="button" type="button" value="Aime" /><input id="unlike_'
				+ this.idmsg + '" name="' + this.idmsg
				+ '" class="button" type="button" value="Aime Pas" />';
	s += '</p><div class="comments"><div id="liste_mp_' + this.idmsg
			+ '"></div>';
	if (environnement != undefined && environnement.clef != undefined) {
		s += '<form action="javascript:(function(){return;})()" '
				+ 'method="get" OnSubmit="javascript:poster_comments(this)">';
		s += '<textarea id="text_comments" name="' + this.idmsg
				+ '" placeholder="Un commentaire ?"></textarea>';
		s += ' <input class="button" type="submit" value="Ajouter" /></form>';
	}
	s += '</div></div><br/>';
	return s;
};

/**
 * Traite la reponse de la servlet AfficherMessage
 * 
 * @param json
 */
Message.traiteReponse = function(json) {
	var s = "";
	var tmp = new Array();
	for ( var i = 0; i < json.length; i++) {
		var msg = new Message(json[i]._id.$oid, json[i].author_id,
				json[i].author_login, json[i].text, json[i].date.$date,
				json[i].nb_like, json[i].nb_unlike);
		tmp[i] = msg;
		s += msg.getHTML(msg);
	}
	$("#liste_tweet").html(s);

	for ( var i = 0; i < tmp.length; i++) {
		var like = "#like_" + tmp[i].idmsg;
		var unlike = "#unlike_" + tmp[i].idmsg;
		var suppr = "#suppr_" + tmp[i].idmsg;
		$(like).click(aime);
		$(unlike).click(aimepas);
		$(suppr).click(supprimer);
		$(suppr).css("float", "right");
		if (json[i].comments != undefined && json[i].comments != "")
			getComments(tmp[i].idmsg);

		if (environnement != undefined && environnement.actif != undefined
				&& json[i].author_id != environnement.actif.id)
			$(suppr).hide();
	}
};

/**
 * Cr�er un objet commentaire
 * 
 * @param id_msg
 * @param id_tweet
 * @param id
 * @param auteur
 * @param texte
 * @param date
 * @returns
 */
function Comments(id_msg, id_tweet, id, auteur, texte, date) {
	this.idmsg = id_msg;
	this.idtweet = id_tweet;
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = new Date(date);
}

/**
 * Retourne le code html d'un commentaire
 * 
 * @param message
 * @returns {String}
 */
Comments.prototype.getHTML = function(message) {
	var s = '<p><u>';
	s += this.auteur;
	s += '</u> : <b>';
	s += this.texte;
	if (environnement != undefined && environnement.clef != undefined)
		s += '<input id="suppr_' + this.idmsg + '" name="' + this.idmsg
				+ '" class="button" type="button" value="Supprimer"/>';
	s += '</b><br/><i>';
	s += this.date.toDateString() + " at " + this.date.toLocaleTimeString();
	s += '</i></p><hr/>';
	return s;
};

/**
 * Traite la r�ponse de la servlet ReadCommentsDuTWEET
 * 
 * @param json
 */
Comments.traiteReponse = function(json) {
	var s = "";
	var tmp = new Array();
	for ( var i = 0; i < json.length; i++) {
		var msg = new Comments(json[i]._id.$oid, json[i].id_tweet,
				json[i].author_id, json[i].author_login, json[i].text,
				json[i].date.$date);
		tmp[i] = msg;
		s += msg.getHTML(msg);
	}
	$("#liste_mp_" + tmp[0].idtweet).html(s);

	for ( var i = 0; i < tmp.length; i++) {
		var suppr = "#suppr_" + tmp[i].idmsg;
		$(suppr).click(supprimer_comments);
		$(suppr).css("float", "right");

		if (environnement != undefined && environnement.actif != undefined
				&& json[i].author_id != environnement.actif.id)
			$(suppr).hide();
	}
};

/**
 * Créer un objet Humeur
 * 
 * @param login
 * @param humeur
 * @returns
 */
function Humeur(login, humeur) {
	this.login = login;
	this.humeur = humeur;
}

/**
 * Code html d'une humeur
 * 
 * @param humeur
 * @returns {String}
 */
Humeur.prototype.getHTML = function(humeur) {
	var s = "";
	s += '<b><i>' + this.humeur + '</i></b>';
	return s;
};

/**
 * Traite la réponse de la servlet dans le cas générale
 * 
 * @param json
 */
Humeur.traiteReponse = function(json) {
	var s = "";
	var humeur = new Humeur(json.login, json.humeur);
	s += humeur.getHTML(humeur);

	$("#humeur").html(s);

};

/**
 * Traite la réponse sur la page spécifique : indexUser
 * 
 * @param json
 */
Humeur.traitePageUser = function(json) {
	var s = "";
	var humeur = new Humeur(json.login, json.humeur);
	s += humeur.getHTML(humeur);

	$("#humeur_" + json.login).html(s);
};
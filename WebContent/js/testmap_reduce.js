function mapdf() {
	var text = this.text;
	var mots = text.match(/\w+/g);
	if (mots == null)
		return;
	var df = [];

	for ( var i = 0; i < mots.length; i++) {
		m = "\"" + mots[i] + "\"";
		df[m] = 1;
	}

	for ( var mot in df)
		emit(mot, {
			df : 1
		});
}

function reducedf(key, values) {
	var total = 0;
	for ( var i = 0; i < values.length; i++)
		total += values[i].df;
	return {
		df : total
	};
}

function maptf() {
	var mots = this.text.match(/\w+/g);
	var tf = [];
	for ( var i = 0; i < mots.length; i++) {
		m = "\"" + mots[i] + "\"";

		if (tf[m] != null)
			tf[m]++;
		else
			tf[m] = 1;
	}
	for ( var w in tf)
		emit(w, {
			document : this._id,
			tf : tf[w]
		});
}

function reducetf(key, values) {
	return {
		tfs : values
	};
}

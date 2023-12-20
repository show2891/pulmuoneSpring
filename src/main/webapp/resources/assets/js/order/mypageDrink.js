function getUrlParams() {
	let params = {};
	window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
	return params;
}

function getDrinkingType() {
	let params = getUrlParams();
	let drinkingType = params.drinkingType;
		
	if (drinkingType == 'N') $("#searchText").text("음용중지");
	else $("#searchText").text("음용중");
}
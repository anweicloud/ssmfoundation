var createScript = function( urls ) {
	
	if (urls == null) {
		return ;
	}
	
	var RequireUrls = new Array();
	if ( typeof urls === 'string' ) {
		RequireUrls = new Array(urls);
	} else {
		RequireUrls = urls;
	}
	
	for (var idx in RequireUrls) {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = RequireUrls[idx];
		document.getElementsByTagName('head')[0].appendChild(script);
	}
	
}
    

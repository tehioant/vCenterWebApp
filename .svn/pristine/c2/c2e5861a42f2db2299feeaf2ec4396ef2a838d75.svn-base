var wmks = WMKS.createWMKS("wmksContainer",{});



function changeLanguage(wmks) {
	if (!wmks)
		return;
	var keyboardLayoutId = $(this).find(":selected").val();
	wmks.setOption('keyboardLayoutId', keyboardLayoutId);
}

function deco(wmks) {
	if (!wmks) {
		return;
	} else {
		wmks.destroy();
		return "est vous sur?";
	}
}

function show(wmks) {
	deco(wmks);
    var x = document.getElementById("alerte");
        x.style.display = "block";
}

function connexion(ipHot, ticket){
	wmks.register(WMKS.CONST.Events.CONNECTION_STATE_CHANGE, function(event,data){
		if(data.state == WMKS.CONST.ConnectionState.CONNECTED){
		console.log("connection state change : connected");
		} else if(data.state == WMKS.CONST.ConnectionState.DISCONNECTED){
			show(wmks);
			console.log("connection state change : disconnected");
		}
	});
	wmks.setOption('keyboardLayoutId',"fr-FR");
	wmks.connect("wss://" +ipHot + ":443/ticket/" +ticket);
}


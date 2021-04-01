function getAddress() {
    return (location.protocol === "https:" ? "wss://" : "ws://") + location.host;
}

function auth() {
	return new Promise(async (res, rej) => {
		let ws = new WebSocket(getAddress());
		await new Promise((resolve, reject) => {
			ws.addEventListener("open", resolve);
		});
		ws.send(JSON.stringify({type: "sessionid", data: {"sessionid": localStorage.getItem("credentials")}}));
		if(localStorage.getItem("credentials")) {
			ws.addEventListener("message", msg => {
				msg = JSON.parse(msg.data);
				if(msg.type == "sessionok") {
					res({ws: ws, result: true});
				}
				else if(msg.type == "sessionerror") {
					localStorage.removeItem("credentials");
					res({ws: ws, result: false});
				}
			});
		}
		else {
			res({ws: ws, result: false});
		}
	});
}

export {auth, getAddress};
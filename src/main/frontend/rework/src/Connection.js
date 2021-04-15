export default class Connection extends EventTarget {

	constructor(address, e) {
		super();
		e(this);
		try {
			this.ws = new WebSocket(address);
		}
		catch(ex) {
			this.broadcastEvent("error");
			return;
		}
		this.ws.addEventListener("error", e => {
			this.broadcastEvent("error", {});
		});
		this.ws.addEventListener("open", () => {
			this.ws.addEventListener("message", e => {
				let data = null;
				try {
					data = JSON.parse(e.data);
				}
				catch(err) {
					this.crash("invalid JSON signal syntax");
					return;
				}
				this.broadcastEvent("signal", data);
			});
			this.ws.addEventListener("close", e => {
				console.log(e);
				this.broadcastEvent("close");
			});
			this.broadcastEvent("connect", {});
		});
	}

	broadcastEvent(type, data) {
		this.dispatchEvent(new CustomEvent(type, {detail: data}));
	}

	crash(reason) {
		this.ws.close();
	}

	sendSignal(type, data) {
		this.ws.send(JSON.stringify({type: type, data: data}));
	}

}
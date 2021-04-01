import {auth} from "/auth.js";
import UI from "./UI.js";

export default class Game {

	constructor() {
		this.ui = new UI(this);
		(async () => {
			let res = await auth();
			this.ws = res.ws;
			res = res.result;
			if(!res) {
				location = "/login";
				return;
			}
			this.ws.addEventListener("message", e => this.handleMsg(e));
			let unimplemented = () => alert("ez a funkctió még nem elérhető.");
			document.getElementById("openpark-btn").addEventListener("click", unimplemented);
			document.getElementById("catalog-btn").addEventListener("click", unimplemented);
			this.sendPacket("init", {});
		})();
	}

	handleMsg(msg) {
		msg = JSON.parse(msg.data);
		let type = msg.type;
		let data = msg.data;
		switch(type) {
			case "balance": {
				this.setBalance(data.balance);
				break;
			}
			case "mapsize": {
				this.drawMap(data.width, data.height);
				break;
			}
			case "setcell": {
				this.setCell(data.x, data.y, data.status);
				break;
			}
		}
	}

	sendPacket(type, data) {
		this.ws.send(JSON.stringify({type: type, data: data}));
	}

	drawMap(width, height) {
		let table = document.getElementById("gamespace");
		let buffer = "";
		for (let y = 0; y < height; y++) {
			buffer += "<tr>";
			for (let x = 0; x < width; x++) {
				buffer += `<td x="${x}" y="${y}" id="cell-${x}-${y}"></td>`;
			}
			buffer += "</tr>"
		}
		table.querySelector("tbody").innerHTML = buffer;
		document.querySelectorAll("td").forEach(e => {
			e.addEventListener("click", ev => {
				this.sendPacket("placeroad", {x: ev.target.getAttribute("x"), y: ev.target.getAttribute("y")})
			})
		});
	}

}

new Game();
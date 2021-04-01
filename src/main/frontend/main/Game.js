import {auth} from "/auth.js";

export default class Game {

	constructor() {
		this.money = 5000;
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
		console.log(msg);
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
/*			case "connectioncrash": {
				alert("connection crashed");
				break;
			}*/
		}
	}

	sendPacket(type, data) {
		this.ws.send(JSON.stringify({type: type, data: data}));
	}

	setBalance(balance) {
		document.getElementById("balance").innerText = balance;
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
				ev.target.classList.add("road");
				this.money -= 200;
				this.setBalance(this.money - 200);
				//this.sendPacket("placeroad", {x: ev.target.getAttribute("x"), y: ev.target.getAttribute("y")})
			})
		});
	}

	setCell(x,y,status) {
		let e = document.getElementById(`cell-${x}-${y}`);
		if(status) {
			e.classList.add("road");
		}
		else {
			e.classList.remove("road");
		}
	}

}

new Game();
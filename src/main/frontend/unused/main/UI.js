import Game from "./Game.js";

export default class UI {

	/**
	 * @param {Game} game 
	 */
	constructor(game) {
		this.game = game;
	}

	setBalance(balance) {
		document.getElementById("balance").innerText = balance;
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

import * as Apilib from "/apilib.js"

class Authpanel {

	/**
	 * @param {HTMLElement} rootElement 
	 */
	constructor(rootElement) {
		this.ws = new WebSocket("ws://89.132.161.193:8080");
		this.ws.addEventListener("message", (msgEvent) => this.msgEvent(JSON.parse(msgEvent.data)));

		this.rootElement = rootElement;

		this.loginViewButton = rootElement.querySelector(".login-mode");
		this.registerViewButton = rootElement.querySelector(".register-mode");

		this.loginViewButton.addEventListener("click", e => this.setLoginMode());
		this.registerViewButton.addEventListener("click", e => this.setRegisterMode());

		this.select(".login-form").addEventListener("submit", e => this.login(e));
		this.select(".register-form").addEventListener("submit", e => this.register(e));

		//this.setLoginMode();
	}

	/**
	 * @returns {HTMLElement}
	 */
	select(selector) {
		return this.rootElement.querySelector(selector);
	}

	msgEvent(e) {
		let type = e.type;
		let data = e.data;
		switch(type) {
			case "loginok": {
				this.showLoginMessage("login OK");
				this.toggleLoginSpin();
				localStorage.setItem("credentials", data.sessionid);
				(async () => {
					await Apilib.sleep(1000);
					location = "/main";
				})();
				break;
			}
			case "loginerror": {
				this.showLoginMessage(data.message);
				this.toggleLoginSpin();
				break;
			}
			case "registererror": {
				this.showRegisterMessage(data.message);
				this.toggleRegisterSpin();
				break;
			}
			case "registerok": {
				this.showRegisterMessage("regisztráció OK");
				this.toggleRegisterSpin();
				localStorage.setItem("credentials", data.sessionid);
				(async () => {
					await Apilib.sleep(1000);
					location = "/main";
				})();
				break;
			}
		}
	}

	/**
	 * @returns {Array<HTMLElement>}
	 */
	selectAll(selector) {
		return this.rootElement.querySelectorAll(selector);
	}

	getFormData(formelement) {
		let builder = {};
		formelement.querySelectorAll("input").forEach(inputElement => {
			if(inputElement.name) {
				builder[inputElement.name] = inputElement.value;
			}
		});
		return builder;
	}

	login(e) {
		e.preventDefault();
		(async () => {
			this.toggleLoginSpin();
			this.showLoginMessage(null);
			this.ws.send(JSON.stringify({type: "login", data: this.getFormData(this.select(".login-form"))}));
			//let res = await Apilib.fetchRawResource("/api/register", this.select(".login-form"));
			//console.log(res);
		})();
	}

	register(e) {
		e.preventDefault();
		(async () => {
			this.showRegisterMessage(null);
			this.ws.send(JSON.stringify({type: "register", data: this.getFormData(this.select(".register-form"))}));
			this.toggleRegisterSpin();
		})();
	}

	setLoginMode() {
		this.showLoginMessage(null);
		this.loginViewButton.classList.add("border-bottom-0", "disabled");
		this.registerViewButton.classList.remove("border-bottom-0", "disabled");
		this.select(".auth-reg-content").classList.add("d-none");
		this.select(".auth-login-content").classList.remove("d-none");
	}

	setRegisterMode() {
		this.showRegisterMessage(null);
		this.registerViewButton.classList.add("border-bottom-0", "disabled");
		this.loginViewButton.classList.remove("border-bottom-0", "disabled");
		this.select(".auth-reg-content").classList.remove("d-none");
		this.select(".auth-login-content").classList.add("d-none");
	}

	showRegisterMessage(msg) {
		let msgElement = this.select(".register-message");
		if(msg === null) {
			msgElement.classList.add("invisible");
		}
		else {
			msgElement.classList.remove("invisible");
			msgElement.innerText = msg;
		}
	}

	showLoginMessage(msg) {
		let msgElement = this.select(".login-message");
		if(msg === null) {
			msgElement.classList.add("invisible");
		}
		else {
			msgElement.classList.remove("invisible");
			msgElement.innerText = msg;
		}
	}

	toggleRegisterSpin() {
		let a = this.select(".register-btn");
		a.classList.toggle("d-none");
		this.select(".register-spinner").classList.toggle("d-none");
	}

	toggleLoginSpin() {
		this.select(".login-btn").classList.toggle("d-none");
		this.select(".login-spinner").classList.toggle("d-none");
	}


}

new Authpanel(document.getElementById("authpanel"));
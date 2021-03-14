import * as Apilib from "./apilib.js"

class Authpanel {

	/**
	 * 
	 * @param {HTMLElement} rootElement 
	 */
	constructor(rootElement) {
		this.rootElement = rootElement;

		this.loginViewButton = rootElement.querySelector(".login-mode");
		this.registerViewButton = rootElement.querySelector(".register-mode");

		this.loginViewButton.addEventListener("click", e => this.setLoginMode());
		this.registerViewButton.addEventListener("click", e => this.setRegisterMode());

		this.select(".login-form").addEventListener("submit", e => this.login(e));

		this.setLoginMode();
	}

	/**
	 * @returns {HTMLElement}
	 */
	select(selector) {
		return this.rootElement.querySelector(selector);
	}

	/**
	 * @returns {Array<HTMLElement>}
	 */
	 selectAll(selector) {
		return this.rootElement.querySelectorAll(selector);
	}

	login(e) {
		e.preventDefault();
		(async () => {
			this.select(".login-btn").classList.add("d-none");
			this.select(".login-spinner").classList.remove("d-none");
			this.showLoginMessage(null);
			await Apilib.sleep(1000);
			this.showLoginMessage("Hibás felbasználónév / jelszó");

			this.select(".login-btn").classList.remove("d-none");
			this.select(".login-spinner").classList.add("d-none");
		})();
	}

	setLoginMode() {
		this.loginViewButton.classList.add("border-bottom-0", "disabled");
		this.registerViewButton.classList.remove("border-bottom-0", "disabled");
		this.select(".auth-reg-content").classList.add("d-none");
		this.select(".auth-login-content").classList.remove("d-none");
	}

	setRegisterMode() {
		this.registerViewButton.classList.add("border-bottom-0", "disabled");
		this.loginViewButton.classList.remove("border-bottom-0", "disabled");
		this.select(".auth-reg-content").classList.remove("d-none");
		this.select(".auth-login-content").classList.add("d-none");
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


}

new Authpanel(document.getElementById("authpanel"));
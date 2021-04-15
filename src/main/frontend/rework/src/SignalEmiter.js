class SignalEmiter {

	constructor() {
	}

	subscribe(handler) {
		this.handler = handler;
	}

	emit(type, data) {
		this.handler(type, data);
	}

}

export default SignalEmiter;
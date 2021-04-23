class SignalEmiter {

	subscribe(handler) {
		this.handler = handler;
	}

	subscribeSend(handler) {
		this.handeSignalSend = handler;
	}

	send(type, data) {
		this.handeSignalSend(type, data);
	}

	emit(type, data) {
		this.handler(type, data);
	}

}

export default SignalEmiter;
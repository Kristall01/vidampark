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

	emit(signal) {
		this.handler(signal.type, signal.data);
	}

}

export default SignalEmiter;
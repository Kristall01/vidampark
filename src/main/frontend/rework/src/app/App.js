import React from "react";
import Connection from "Connection";
import Loading from "pages/loading/Loading";
import SignalEmiter from "SignalEmiter"

import Authpage from "pages/auth/Authpage";
import Selectpage from "pages/select/Selectpage";
import Gamepage from "pages/game/Gamepage"
import Uitestpage from "pages/uitest/Uitestpage"

export default class App extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			screen: "loading",
			screenData: {spin: true, text: "Connecting..."},
			connection: null,
			emiter: null
		}
	}

	handleSignal(type, data) {
		switch(type) {
			case "setscene": {
				this.switchScreen(data.scene, {});
				return;
			}
			case "log": {
				window.alert(data);
				return;
			}
			case "connectioncrash": {
				window.alert("connection crashed");
				return;
			}
			case "logout": {
				this.switchScreen("auth");
				return;
			}
			default: {
				this.state.signal.emit(type, data);
			}
		}
	}

	componentDidMount() {
		let emiter = new SignalEmiter();
		this.setState(Object.assign(this.state, {signal: emiter}));
		if(this.state.screen === "loading") {
			setTimeout(() => {
				new Connection("ws://127.0.0.1:8080", c => {
					c.addEventListener("connect", () => {
						this.switchScreen("auth", {});
						//this.setLoadingPhase("loading...", true);
					});
					c.addEventListener("error", ex => {
						this.setLoadingPhase("connection failed", false);
					})
					c.addEventListener("signal", e => {
						e = e.detail;
						this.handleSignal(e.type, e.data);
					});
					c.addEventListener("close", () => {
						this.setLoadingPhase("connection lost", false);
					});
					emiter.subscribeSend((type,data) => c.sendSignal(type,data));
				});
			}, 1000);
		}
	}

	switchScreen(screenName, data) {
		this.setState(Object.assign(this.state, {
			screenData: data,
			screen: screenName
		}));
	}

	setLoadingPhase(text, spin) {
		this.setState(Object.assign(this.state, {
			screen: "loading",
			screenData: {
				spin: spin,
				text: text
			}
		}));
	}

	render() {
		switch(this.state.screen) {
			case "loading":
				return <Loading data={this.state.screenData} signal={this.state.signal}></Loading>
			case "auth":
				return <Authpage data={this.state.screenData} signal={this.state.signal}></Authpage>
			case "select":
				return <Selectpage data={this.state.screenData} signal={this.state.signal}></Selectpage>
			case "game":
				return <Gamepage data={this.state.screenData} signal={this.state.signal}></Gamepage>
			case "uitest":
				return <Uitestpage data={this.state.screenData} signal={this.state.signal}></Uitestpage>
			default:
				return <>404</>
		}
	}

}
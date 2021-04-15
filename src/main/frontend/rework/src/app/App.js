import React from "react";
import Connection from "Connection";
import Loading from "pages/loading/Loading";
import SignalEmiter from "SignalEmiter"

import Authpage from "pages/auth/Authpage";
import Selectpage from "pages/select/Selectpage";
import Gamepage from "pages/game/Gamepage"

export default class App extends React.Component{

	constructor(props) {
		super(props);

		this.state = {
			screen: "loading",
			screenData: {spin: true, text: "Connecting..."},
			connection: null,
			emiter: null
		}
	}

	componentDidMount() {
		this.updateState({signal: new SignalEmiter()});

		setTimeout(() => {

		let connection = new Connection("ws://127.0.0.1:8080", c => {
			c.addEventListener("connect", () => {
				this.updateScreenData({text: "loading..."})
			});

			c.addEventListener("error", ex => {
				this.updateScreenData({
					spin: false,
					text: "connection failed"
				})
			})
			c.addEventListener("connect", () => {
				this.updateState({
					screen: "auth"
				});
			});
			c.addEventListener("signal", e => {
				if(e.type == "setscene") {
					this.updateState();
				}
			});
			c.addEventListener("close", () => {
				this.updateState({
					screenData: {
						spin: false,
						text: "connection lost"
					}
				});
			});
		});
		}, 1000);
	}

	setScreen(screenType, data) {
		this.setState(Object.assign(this.state, {
			screen: screenType,
			screenData: data
		}))
	}

	updateScreenData(data) {
		this.setState(Object.assign(this.state, Object.assign(this.state.screenData, data)));
	}

	updateState(a) {
		this.setState(Object.assign(this.state, a));
	}

	render() {

		switch(this.state.screen) {
			case "loading":
				return <Loading data={this.state.screenData} signal={this.state.signal}></Loading>
			case "auth":
				return <Authpage data={this.state.screenData} signal={this.state.signal}></Authpage>
			case "select":
				return <Selectpage data={this.state.screenData} signal={this.state.signal}></Selectpage>
			case "game": {
				return <Gamepage data={this.state.screenData} signal={this.state.signal}></Gamepage>
			}
			default:
				return <>404</>
		}
	}

}
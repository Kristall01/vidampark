import "./Authpage.css"
import React, { Component, createRef } from "react"
import { render } from "@testing-library/react";

const phases = {
	"login": {
		comp: () =>
			<>
				<input name="username" className="section" type="text" placeholder="felhasználónév"></input>
				<input name="password" className="section" type="password" placeholder="jelszó"></input>
			</>,
		title: "bejelentkezés"
	},
	"register": {
		comp: () =>
			<>
				<input name="username" className="section" type="text" placeholder="felhasználónév"></input>
				<input name="password" className="section" type="password" placeholder="jelszó"></input>
				<input name="password2" className="section" type="password" placeholder="jelszó újra"></input>
			</>,
		title: "regisztráció"
	}
};

class Authpage extends Component {

	constructor(props) {
		super(props);

		let {signal} = props;
		this.state = {
			state: "login",
			submit: true
		}

		this.formReference = createRef();

	}

	switchPanel(panel) {
		this.formReference.current.reset();
		this.updateState("state", panel);
	}

	updateState(key, value) {
		let o = {};
		o[key] = value;
		this.setState(Object.assign(this.state, o));
	}

	handleSubmit(e) {
		e.preventDefault();
		let data = new FormData(e.target);
		let o = {};
		for (const key of data.keys()) {
			o[key] = data.get(key);
		}

		this.updateState("submit", false);

		setTimeout(() => {
			this.updateState("submit", true);
		}, 1000)
		console.log(JSON.stringify(o));
	}

	render() {
		let phaseButtonsIndex = 0;
		let phaseButtons = [];
		Object.keys(phases).forEach(phaseKey => {
			let phase = phases[phaseKey];
			phaseButtons.push(
			<div key={phaseButtonsIndex++} className={this.state.state === phaseKey ? "active" : null} onClick={() => this.switchPanel(phaseKey)}>
				{phase.title}
			</div>);
		});

		let errorlabel;
		if(this.state.errorlabel) {
			errorlabel = <div>{this.state.errorlabel}</div>;
		}

		return(
			<div className="auth-page">
				<div className="card">
				<div className="switchPart">
						{phaseButtons}
					</div>
					<div className="mainPart">
						<h1 className="section centered">{phases[this.state.state].title}</h1>
						<form ref={this.formReference} onSubmit={() => this.handleSubmit.apply(this,arguments)}>
							{phases[this.state.state].comp()}
							{errorlabel}
							<input disabled={!this.state.submit} type="submit" className="section"></input>
						</form>
					</div>
				</div>
			</div>)
	}

}

export default Authpage;
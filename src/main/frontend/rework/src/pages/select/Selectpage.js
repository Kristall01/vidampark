import "./Selectpage.css"
import Gamestate from "./Gamestate/Gamestate"
import Button from "ui/button/Button"
import { Component } from "react"

class Selectpage extends Component {

	constructor(props) {
		super(props);

		this.state = {
			states: {}
		};
	}

	updateState(key, value) {
		let o = {};
		o[key] = value;
		this.setState(Object.assign(this.state, o));
	}

	handleSignal(type, data) {
		switch(type) {
			case "add": {
				this.addState(data);
				break;
			}
			case "list": {
				this.updateState("states", []);
				data.forEach(e => this.addState(e));
				break;
			}
			case "rename": {
				this.updateState()
				let states = this.state.states;
				states[data.ID].name = data.newname;
				this.updateState('states', states);
				break;
			}
			case "delete": {
				this.deleteState(data);
				break;
			}
			default: {

			}
		}
	}

	renameGamestate(id, newName) {
		this.props.signal.send("rename", {id: id, name: newName});
	}

	selectGamestate(id) {
		this.props.signal.send("select", id);
	}

	deleteGamestate(id) {
		this.props.signal.send("delete", id);
	}

	addState(s) {
		let stateList = this.state.states;
		stateList[s.id] = s;
		this.setState(Object.assign(this.state, {}));
	}

	deleteState(obj) {
		let oldStates = this.state.states;
		let newStates = {};
		Object.keys(oldStates).forEach(id => {
			let element = oldStates[id];
			if(id != obj.ID) {
				newStates[id] = element;
			}
		});
		this.updateState('states', newStates);
	}

	componentDidMount() {
		this.props.signal.subscribe((type, data) => this.handleSignal(type, data));
		this.props.signal.send("list", {});
	}

	createState() {
		this.props.signal.send("create", {});
	}

	render() {
		let index = 0;

		let states = [];
		Object.keys(this.state.states).forEach(key => {
			let e = this.state.states[key];
			states.push(
				<Gamestate 
					remove={(id) => this.deleteGamestate(id)}
					select={this.selectGamestate.bind(this)} 
					rename={(id, name) => this.renameGamestate(id, name)}
					key={index++} 
					data={e}>
				</Gamestate>
				);
		});

		return (
		<div className="select-page">
			<div className="header">
				<Button onClick={() => this.createState()}>új játék</Button>
				<Button onClick={() => this.props.signal.send("logout", {})}>kijelentkezés</Button>
			</div>
			<div className="content">
				{states}
			</div>
		</div>
	)}

}

export default Selectpage
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

	handleSignal(type, data) {
		switch(type) {
			case "add": {
				this.addState(data);
				break;
			}
			case "list": {
				this.setState(Object.assign(this.state, {states: []}))
				data.forEach(e => this.addState(e));
				break;
			}
			case "rename": {
				this.state.states[data.ID].name = data.newname;
				this.setState(Object.assign(this.state, {}))
			}
		}
	}

	renameGamestate(id, newName) {
		this.props.signal.send("rename", {id: id, name: newName});
	}

	addState(s) {
		let stateList = this.state.states;
		stateList[s.id] = s;
		this.setState(Object.assign(this.state, {}));
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
			states.push(<Gamestate rename={(id, name) => this.renameGamestate(id, name)} key={index++} data={e}></Gamestate>);
		});

		return (
		<div className="select-page">
			<div className="header">
				<Button onClick={() => this.createState()}>új játék</Button>
				<Button>kijelentkezés</Button>
			</div>
			<div className="content">
				{states}
			</div>
		</div>
	)}

}

export default Selectpage
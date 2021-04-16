import React, { Component } from "react";
import Map from './Map/Map';
import "./Gamepage.css";
//import Window from './Window/window';

export default class Gamepage extends Component {

    constructor(props)
    {
        super(props);

		this.state = {
			money: '?',
			mapSize: null,
			started: false
		};
    }

	componentDidMount() {
		this.props.signal.subscribe((type, data) => this.handleSignal(type, data));
		this.props.signal.send("init", {});
	}

	updateState(key, value) {
		let o = {};
		o[key] = value;
		this.setState(Object.assign(this.state, o));
	}

	handleSignal(type, data) {
		switch(type) {
			case "balance": {
				this.updateState("money", data);
				break;
			}
			case "mapsize": {
				this.updateState("mapSize", {
					width: data.width,
					height: data.height
				});
				break;
			}
			case "startpark": {
				this.updateState("started", true);
			}
		}
	}

    getMoney() {
        return this.state.money;
    }

	render() {
		let map = null;
		if(this.state.mapSize) {
			let {width, height} = this.state.mapSize;
			map = <Map width={width} height={height}></Map>
		}

		let disabledButton = this.state.started ? "TRUE" : null;

        return (
            <div className="Gamepage">
                <div className="header">
                    <div className="money">Money: ${this.getMoney()} </div>
                    <div className="buttons">
                        <button disabled={disabledButton} className="openParkButton" onClick={() => this.props.signal.send("startpark", {})}>🚪Open Park</button>
                        <button className="pauseButton" onClick={ () => console.log("Building Catalog") }>🏢 Building Catalog</button>
                        <button className="pauseButton" onClick={ () => console.log("Pause") }>⏸ Pause</button>
                        <button className="pauseButton" onClick={ () => this.props.signal.send("menu", {}) }>Menu</button>
						<button className="pauseButton" onClick={ () => this.props.signal.send("leave", {}) }>🔙 vissza</button>
                    </div>
                </div>
                <div className="main">
                    {map}
                </div>
            </div>
        );
	}
}
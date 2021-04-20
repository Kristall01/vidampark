import React, { Component } from "react";
import Map from './Map/Map';
import "./Gamepage.css";
import Catalog from "ui/catalog/Catalog"

export default class Gamepage extends Component {

    constructor(props)
    {
        super(props);

		this.state = {
			initiated: false,
			money: '?',
			mapSize: null,
			started: false,
			catalogHidden: true,
			catalog: [],
			buildings: [],
			targetBuilding: null
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
				break;
			}
			case "catalog": {
				this.updateState("catalog", data);
				break;
			}
			case "initiated": {
				this.updateState('initiated', true);
			}
			case "updatecell": {
				if(data.type) {
					let b = this.state.buildings;
					b.push(data);
					this.updateState("buildings", b);
				}
				else {
					let copy = [];
					this.state.buildings.forEach(building => {
						if(building.x != data.x && building.y != data.y) {
							copy.push(building);
						}
					});
					this.updateState("buildings", copy);
				}
			}
			default: {

			}
		}
	}

    getMoney() {
        return this.state.money;
    }

	openCatalog(opened) {
		this.updateState('catalogHidden', !opened);
	}

	manualSignal() {/*
		let type = window.prompt("type");
		if(!type) {
			return;
		}
		let data = window.prompt("data");
		if(!data) {
			return;
		}
		this.props.signal.send(type, JSON.parse(data));*/
		this.props.signal.send("placebuilding", {x: 0, y: 0, type: "road"});
	}

	setTargetBuilding(type) {
		this.updateState('targetBuilding', type);
		console.log("ok?");
	}

	handleCellClick(x,y) {
		if(this.state.targetBuilding) {
			this.props.signal.send("placebuilding", {
				x: x,
				y: y,
				type: this.state.targetBuilding
			});
		}
	}

	render() {
		if(!this.state.initiated) {
			return <></>;
		}
		let map = null;
		if(this.state.mapSize) {
			let {width, height} = this.state.mapSize;
			map = <Map handleCellClick={this.handleCellClick.bind(this)} buildings={this.state.buildings} width={width} height={height}></Map>
		}

		let disabledButton = this.state.started ? "TRUE" : null;

        return (
            <div className="Gamepage">
				<Catalog setBuildTarget={this.setTargetBuilding.bind(this)} catalogData={this.state.catalog} closeWindow={() => this.openCatalog(false)} hidden={this.state.catalogHidden}></Catalog>
                <div className="header">
                    <div className="money">Money: ${this.getMoney()} </div>
                    <div className="buttons">
                        <button disabled={disabledButton} className="openParkButton" onClick={() => this.props.signal.send("startpark", {})}>🚪Open Park</button>
                        <button className="pauseButton" onClick={this.openCatalog.bind(this)}>🏢 Building Catalog</button>
                        <button className="pauseButton" onClick={ () => console.log("Pause") }>⏸ Pause</button>
                        <button className="pauseButton" onClick={ () => this.props.signal.send("menu", {}) }>Menu</button>
						<button className="pauseButton" onClick={ () => this.props.signal.send("leave", {}) }>🔙 vissza</button>
						<button onClick={this.manualSignal.bind(this)}>manual signal</button>
                    </div>
                </div>
                <div className="main">
                    {map}
                </div>
            </div>
        );
	}
}
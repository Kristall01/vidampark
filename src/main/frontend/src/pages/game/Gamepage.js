import React, { Component } from "react";
import Mapelement from './Map/Map';
import "./Gamepage.css";
import Catalog from "ui/catalog/Catalog"
import Settings from "ui/settings/Settings"

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
			settingsHidden: true,
			catalog: [],
			buildings: [],
			targetBuilding: null,
			cellSize: 35,
			guests: new Map(),
			paused: false,
			tickspeed: 1000,
			/*TEMP*/
			buildingSettings: [
				{name: "hotdog árus", usePrice: 5},
				{name: "hamburgerező", usePrice: 10},
				{name: "étterem", usePrice: 20},
				{name: "ugrálóvár", usePrice: 10},
				{name: "körhinta", usePrice: 8},
			],
			/*ENDTEMP*/
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
				break;
			}
			case "spawnguest": {
				let {id} = data;
				let guests = this.state.guests;
				let guestOb = {...data, id: id};
				guests.set(id, guestOb);
				this.updateState("guests", guests);
				break;
			}
			case "moveto": {
				let {guestID, x, y} = data;
				let guests = this.state.guests;
				let guestOb = {...this.state.guests.get(data.guestID), x: x, y: y};
				guests.set(guestID, guestOb);
				this.updateState("guests", guests);
				break;
			}
			case "guestdespawn": {
				this.state.guests.delete(data);
				break;
			}
			case "paused": {
				this.updateState("paused", data);
				break;
			}
			case "tickspeed": {
				this.updateState("tickspeed", data);
				break;
			}
			//removed
			/*case "moveguest": {
				let {path, guestID} = data;
				let guests = this.state.guests;
				guests.set(guestID, {
					path: path
				});
				this.updateState("guests", guests);
				break;
			}
			case "route": {
				console.log(data);
				break;
			}*/
			default: {

			}
		}
	}

	zoomEvent(e) {
		if(!e.shiftKey) {
			return;
		}
		this.updateState("cellSize", this.state.cellSize*(1-(e.deltaY / 1000)));
	}

    getMoney() {
        return this.state.money;
    }

	openCatalog(close) {
		this.updateState('catalogHidden', !close);
	}

	openSettings(close) {
		this.updateState('settingsHidden', !close);
	}

	setTargetBuilding(type) {
		this.updateState('targetBuilding', type);
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
			let calcWidth = this.state.cellSize*width;
			let calcHeight = this.state.cellSize*height;
			map = <Mapelement time={this.state.tickspeed} guests={this.state.guests} handleCellClick={this.handleCellClick.bind(this)} buildings={this.state.buildings} renderWidth={calcWidth} width={width} height={height} renderHeight={calcHeight}></Mapelement>
		}

		let disabledButton = this.state.started ? "TRUE" : null;

		let catalogBtn = this.state.targetBuilding ?
			<button className="pauseButton" onClick={() => this.setTargetBuilding(null)}>építés megszakítása</button>
			:
			<button className="pauseButton" onClick={this.openCatalog.bind(this)}>🏢 katalógus</button>
		;

        return (
            <div className="Gamepage" onWheel={this.zoomEvent.bind(this)}>
				<Catalog setBuildTarget={this.setTargetBuilding.bind(this)} catalogData={this.state.catalog} closeWindow={() => this.openCatalog(false)} hidden={this.state.catalogHidden}></Catalog>
				<Settings hidden={this.state.settingsHidden} buildings={this.state.buildingSettings} closeWindow={() => this.openSettings(false)} okWindow={() => this.openSettings(false)}></Settings>
                <header>
                    <div className="money">Money: ${this.getMoney()} </div>
                    <div className="buttons">
                        <button disabled={disabledButton} className="openParkButton" onClick={() => this.props.signal.send("startpark", {})}>🚪Open Park</button>
                        <button className="pauseButton" onClick={() => this.props.signal.send("pause")}>{this.state.paused ? "▶ Play" : "⏸ Pause"}</button>
						{catalogBtn}
                        {/*<button className="menuButton" onClick={ () => this.openSettings(open) }>Menu</button>*/}
                        <button className="menuButton" onClick={ () => this.openSettings(true) }>Beállítások</button>
						<button className="leaveButton" onClick={ () => this.props.signal.send("leave", {}) }>🔙 vissza</button>
                    </div>
                </header>
                <div className="main">
					{map}
                </div>
            </div>
        );
	}
}
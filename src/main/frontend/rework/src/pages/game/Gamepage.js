import React, { Component } from "react";
import Map from './Map/Map';
import "./Gamepage.css";
import Catalog from "ui/catalog/Catalog"
import Navigraph from "./Navigraph/Navigraph";

export default class Gamepage extends Component {

    constructor(props)
    {
        super(props);

		this.state = {
			initiated: false,
			money: 0,
			mapSize: null,
			started: false,
			catalogHidden: true,
			catalog: [],
			buildings: [],
			targetBuilding: null,
			cellSize: 35,
			mapmode: true,
			graph: null
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
				break;
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
			case "updategraph": {
				console.log(data);
				this.updateState("graph", data);
				break;
			}
			default: {
				console.error("unidentified signal: ",type,data);
				break;
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

	openCatalog(opened) {
		this.updateState('catalogHidden', !opened);
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
		let {width, height} = this.state.mapSize;
		let renderWidth = this.state.cellSize*width;
		let renderHeight = this.state.cellSize*height;
		let map = <Map hidden={!this.state.mapmode} handleCellClick={this.handleCellClick.bind(this)} buildings={this.state.buildings} renderWidth={renderWidth} width={width} height={height} renderHeight={renderHeight}></Map>
		let navigraph = <Navigraph graph={this.state.graph} hidden={this.state.mapmode} width={width} height={height} renderWidth={renderWidth} renderHeight={renderHeight}></Navigraph>;
		let disabledButton = this.state.started ? "TRUE" : null;

		let catalogBtn = this.state.targetBuilding ?
			<button className="pauseButton" onClick={() => this.setTargetBuilding(null)}>építés megszakítása</button>
			:
			<button className="pauseButton" onClick={this.openCatalog.bind(this)}>🏢 katalógus</button>
		;

        return (
            <div className="Gamepage" onWheel={this.zoomEvent.bind(this)}>
				<Catalog currentMoney={this.state.money} setBuildTarget={this.setTargetBuilding.bind(this)} catalogData={this.state.catalog} closeWindow={() => this.openCatalog(false)} hidden={this.state.catalogHidden}></Catalog>
                <header>
                    <div className="money">Money: ${this.getMoney()} </div>
                    <div className="buttons">
                        <button disabled={disabledButton} className="openParkButton" onClick={() => this.props.signal.send("startpark", {})}>🚪Open Park</button>
                        <button className="pauseButton" onClick={ () => console.log("Pause") }>⏸ Pause</button>
						{catalogBtn}
                        <button className="pauseButton" onClick={ () => this.props.signal.send("menu", {}) }>Menu</button>
						<button className="pauseButton" onClick={ () => this.props.signal.send("leave", {}) }>🔙 vissza</button>
						<button className="pauseButton" onClick={ () => this.updateState("mapmode", !this.state.mapmode) }> toggle mode</button>
                    </div>
                </header>
                <div className="main">
                    {map}
					{navigraph}
                </div>
            </div>
        );
	}
}
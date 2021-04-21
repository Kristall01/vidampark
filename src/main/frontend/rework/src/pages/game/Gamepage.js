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
			targetBuilding: null,
			cellSize: 35,
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
		let map = null;
		if(this.state.mapSize) {
			let {width, height} = this.state.mapSize;
			let calcWidth = this.state.cellSize*width;
			let calcHeight = this.state.cellSize*height;
			map = <Map handleCellClick={this.handleCellClick.bind(this)} buildings={this.state.buildings} renderWidth={calcWidth} width={width} height={height} renderHeight={calcHeight}></Map>
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
                <div className="header">
                    <div className="money">Money: ${this.getMoney()} </div>
                    <div className="buttons">
                        <button disabled={disabledButton} className="openParkButton" onClick={() => this.props.signal.send("startpark", {})}>🚪Open Park</button>
                        <button className="pauseButton" onClick={ () => console.log("Pause") }>⏸ Pause</button>
						{catalogBtn}
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
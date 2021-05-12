import React, { Component } from "react";
import Cell from "./Cell/Cell";
import "./Map.scss";
import Guest from "./Guest/Guest";

export default class Map extends Component {

	getWidth() 
	{
		//return this.props.sizeWidth;
		return this.props.width;
	}

	getHeight() 
	{
		//return this.props.sizeHeight;
		return this.props.height;
	}

	render() 
	{
		let fakeMap = [];
		for (let y = 0; y < this.getHeight(); y++) {
			let row = [];
			for (let x = 0; x < this.getWidth(); x++) {
				row.push(null);
			}
			fakeMap.push(row);
		}
		this.props.buildings.forEach((building) => {
			let {x,y,width,height} = building;
			fakeMap[y][x] = building;
		});
		let map = [];
		let key = 0;
		for(let y = 0; y < this.getHeight(); ++y) 
		{
			let row = [];
			for(let x = 0; x < this.getWidth(); ++x) 
			{
				if(fakeMap[y][x]) {
					row.push(<Cell onClick={() => this.props.handleCellClick(x,y)} key={++key} content={fakeMap[y][x]} x={x} y={y}/>);
				}
				else {
					row.push(<Cell onClick={() => this.props.handleCellClick(x,y)} key={++key} x={x} y={y}/>);
				}
			}	
			map.push(<div key={++key} className="row">{row}</div>);
		}
		let style = {
			height: (this.props.renderHeight)+"px",
			width: (this.props.renderWidth)+"px"
		};
		let guests = [];
		this.props.guests.forEach(g => {
			guests.push(<Guest key={g.id} cellSize={this.props.renderWidth / this.props.width} time={this.props.time} {...g}></Guest>);
		});
		//const path = [{"x":0,"y":1},{"x":0,"y":2},{"x":0,"y":3},{"x":0,"y":4},{"x":0,"y":5},{"x":0,"y":6},{"x":1,"y":6},{"x":2,"y":6},{"x":3,"y":6},{"x":4,"y":6},{"x":4,"y":7},{"x":5,"y":7},{"x":6,"y":7},{"x":6,"y":6},{"x":7,"y":6},{"x":8,"y":6},{"x":8,"y":7},{"x":9,"y":7},{"x":10,"y":7},{"x":10,"y":6},{"x":10,"y":5},{"x":10,"y":4},{"x":10,"y":3},{"x":10,"y":2},{"x":9,"y":2},{"x":8,"y":2},{"x":7,"y":2},{"x":6,"y":2},{"x":5,"y":2}]
		//const path= [{"x":1,"y":0}];
		return (
			<div style={style} className="Map">
				<div className="mapinner">
					{guests}
					{map}
				</div>
			</div>
		);
	}
	/*
		<Dot path={path} cellSize={this.props.renderWidth / this.props.width} time={250}></Dot>
	*/

}
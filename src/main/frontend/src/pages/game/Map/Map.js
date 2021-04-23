import React, { Component } from "react";
import Cell from "./Cell/Cell";
import "./Map.css";

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
		return <div style={style} className="Map">{map}</div>;
	}

}
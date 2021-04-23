import React, { Component } from "react";
import "./Cell.css";

const Cell = ({onClick, content}) => {
	let img = null;
	if(content) {
		let {width, height, mapIcon, nickName} = content;
		let style = {
			width: `${width}00%`,
			height: `${height}00%`
		};
		img = <img alt={nickName} className="cellimage" style={style} src={"/buildings/map/"+mapIcon}></img>;
	}
 	return (
		<div className="Cell">
			<div onClick={onClick} className="inner"></div>
			{img}
		</div>
	);
}

export default Cell;
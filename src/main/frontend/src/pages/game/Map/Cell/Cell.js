import React, { Component } from "react";
import "./Cell.scss";

const Cell = ({onClick, content, building}) => {
	let htmlContent = null;
	if(content) {
		let {width, height, mapIcon, nickName} = content;
		let style = {
			width: `${width}00%`,
			height: `${height}00%`
		};
		let hammer = null;
		let buildtime = null;
		if(building) {
			hammer = <img className="hammer" src="/hammer.svg"></img>
			buildtime = <div className="buildtime">hello world szar szar szar szar szar szar </div>
		}
		htmlContent =
			<div className="content" style={style}>
				<img alt={nickName} className="image" src={"/buildings/map/"+mapIcon}></img>
				{hammer}
				{buildtime}
			</div>;
	}
 	return (
		<div onClick={onClick} className="Cell">
			<div className="inner"></div>
			{htmlContent}
		</div>
	);
}

export default Cell;
import React, { Component } from "react";
import { PureComponent } from "react";
import "./Cell.scss";

class Cell extends PureComponent {

	render() {
		let {onClick, content, building} = this.props;
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
			<div className="Cell">
				<div onClick={onClick} className="inner"></div>
				{htmlContent}
			</div>
		);
	}

}

export default Cell;
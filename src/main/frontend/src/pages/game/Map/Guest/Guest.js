import { PureComponent } from "react";
import "./Guest.css"

const maxRNG = 10;

class Guest extends PureComponent {
	
	render() {
		let {x, y, cellSize, time, color} = this.props;
		let dotSize = cellSize*0.3;
		let style = {
			width: dotSize,
			height: dotSize,
			backgroundColor: color
		};
		style.left = (x+(((maxRNG - Math.floor(Math.random() * maxRNG*2))/100) + 0.5))*cellSize;
		style.top = (y+(((maxRNG - Math.floor(Math.random() * maxRNG*2))/100) + 0.5))*cellSize;
		if(time) {
			style.transitionDuration = (time/1000)+"s";
		}
		return <div className="guest-component" style={style}></div>;
	}
}

export default Guest;
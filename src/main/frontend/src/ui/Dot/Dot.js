import { Component } from "react";
import "./Dot.css"

class Dot extends Component {

	constructor(props) {
		super(props);
		let stateObject = {
			renderTask: null
		};
		if(props.path && props.path.length != 0) {
			stateObject.position = props.path[0];
		}
		else {
			stateObject.position = {x: 0, y: 0};
		}
		this.state = stateObject;
	}

	showPath(pathElement) {
		this.setState(Object.assign(this.state, {position: pathElement}));
	}

	cancelRender() {
		if(this.state.renderTask) {
			//console.log("canceled render");
			clearInterval(this.state.renderTask);
			this.setState(Object.assign(this.state, {renderTask: null}));
		}
		else {
			//console.log("canceled NON EXISTENT render");
		}
	}

	renderPath() {
		this.cancelRender();
		//console.log("called renderPath");
		let path = this.props.path;
		if(!path || path.length == 0) {
			return;
		}
		path = [...path];
		let renderFn = () => {
			let pathElement = path.shift();
			this.showPath(pathElement);
			if(path.length == 0) {
				this.cancelRender();
			}
		}
		renderFn();
		if(path.length != 0) {
			//console.log("seting rendertask ID");
			this.setState(Object.assign(this.state, {
				renderTask: setInterval(renderFn, this.props.time || 1000)
			}));
		}
	}

	componentWillUnmount() {
		this.cancelRender();
	}

	mathcPath(path0, path1) {
		if(path0.length != path1.length) {
			return false;
		}
		for(let i = 0; i < path0.length; ++i) {
			if(path0.x !== path1.x || path0.y !== path1.y)
				return false;
		}
		return true;
	}

	componentDidUpdate(prevProps, prevState) {
		if(this.props !== prevProps && !this.mathcPath(prevProps.path, this.props.path)) {
			this.renderPath();
		}
	}

	componentDidMount() {
		setTimeout(() => this.renderPath(), 1);
	}

	shouldComponentUpdate(nextProps, nextState) {
		if(this.state === nextState && this.props === nextProps) {
			return false;
		}
		return true;
	}
	
	render() {
		let position = this.state.position;
		let style = {
			width: this.props.cellSize*0.4,
			height: this.props.cellSize*0.4
		};
		if(position) {
			style.left = (position.x+0.5)*this.props.cellSize;
			style.top = (position.y+0.5)*this.props.cellSize;
		}
		if(this.props.time) {
			style.transitionDuration = (this.props.time/1000)+"s";
		}
		return <div className="dot-component" style={style}></div>;
	}
}

export default Dot;